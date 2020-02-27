package com.intraedge.policy.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.intraedge.policy.dao.EmployeeDao;
import com.intraedge.policy.dao.entity.Employee;
import com.intraedge.policy.dao.entity.EmployeeRole;
import com.intraedge.policy.dto.ChangePassword;
import com.intraedge.policy.exception.EmployeeNotFoundException;
import com.intraedge.policy.exception.PolicySystemException;
import com.intraedge.policy.mail.EmailService;

/**
 * 
 * @author SujayB
 * 
 */
@Service
@TransactionConfiguration(defaultRollback = true)
public class EmployeeServiceImpl implements EmployeeService {
	private Logger log = Logger.getLogger(EmployeeServiceImpl.class);
	@Value("#{application.policyBasePath}")
	private String policyBasePath;
	@Value("#{application.policyWebBasePath}")
	private String policyWebBasePath;
	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private EmailService emailService;

	@PostConstruct
	public void init() {
		log.info("PolicyBasePath = " + policyBasePath);
		log.info("PolicyWebBasePath = " + policyWebBasePath);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public long createEmployee(com.intraedge.policy.dto.Employee employee) {
		Employee e = new Employee();
		PasswordEncoder encoder = new Md5PasswordEncoder();
		e.setFirst(employee.getFirst());
		e.setLast(employee.getLast());
		e.setEnabled(true);
		String password = java.util.UUID.randomUUID().toString();
		e.setPassword(encoder.encodePassword(password, null));
		e.setEmail(employee.getEmail());
		e.setPhone(employee.getPhone());
		e.setPlainPassword(password);
		e.setChangePassword(true);
		EmployeeRole role = new EmployeeRole();
		role.setAutority(false);
		e.setRole(role);
		role.setEmployee(e);
		log.info("creating employee = " + e);
		Long empId = employeeDao.save(e);
		emailService.userCreatedNotification(e);
		return empId;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public com.intraedge.policy.dto.Employee getEmployeeByEmail(String email) throws PolicySystemException {
		com.intraedge.policy.dto.Employee employee = new com.intraedge.policy.dto.Employee();
		log.info("Getting employee by email : " + email);
		try {
			Employee emp = employeeDao.getEmployeeByEmail(email);
			if (emp == null) {
				return null;
			}
			employee.setFirst(emp.getFirst());
			employee.setLast(emp.getLast());
			employee.setEmail(emp.getEmail());
			employee.setEnabled(emp.isEnabled());
			employee.setChangePassword(emp.isChangePassword());
			employee.setPhone(emp.getPhone());
		} catch (Exception e) {
			throw new PolicySystemException(e);
		}
		return employee;
	}

	@Override
	public List<com.intraedge.policy.dto.Employee> listEmployees() {
		log.info("Listing Employees: ");

		List<com.intraedge.policy.dto.Employee> result = new ArrayList<com.intraedge.policy.dto.Employee>();
		List<Employee> employeeList = employeeDao.listEmployees();
		if (employeeList == null) {
			return null;
		}
		for (Employee emp : employeeList) {
			com.intraedge.policy.dto.Employee employee = new com.intraedge.policy.dto.Employee();
			employee.setFirst(emp.getFirst());
			employee.setLast(emp.getLast());
			employee.setEmail(emp.getEmail());
			employee.setPhone(emp.getPhone());
			employee.setRole(emp.getRole().getAuthority());
			employee.setEnabled(emp.isEnabled());
			result.add(employee);
		}

		return result;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void changeEmployeePassword(String email, ChangePassword changePassword, boolean resetPassword, boolean sendEmailNotification) throws PolicySystemException {
		try {
			if (email == null || changePassword == null) {
				throw new PolicySystemException("Email or password cannot be null");
			}
			PasswordEncoder encoder = new Md5PasswordEncoder();
			Employee e = employeeDao.getEmployeeByEmail(email);
			e.setChangePassword(resetPassword);
			e.setPlainPassword(changePassword.getNewPassword());
			e.setPassword(encoder.encodePassword(changePassword.getNewPassword(), null));
			employeeDao.updateEmployee(e);
			if (sendEmailNotification) {
				emailService.resetPasswordNotification(e);
			}

		} catch (Exception e) {
			log.error("Error occurred while updating employee : " + e.getMessage());
			throw new PolicySystemException(e);
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateEmployee(com.intraedge.policy.dto.Employee employee) throws EmployeeNotFoundException, PolicySystemException {
		try {
			Employee e = employeeDao.getEmployeeByEmail(employee.getEmail());
			if(e ==null){
				throw new EmployeeNotFoundException("Employee with email "+employee.getEmail()+" not found in system");
			}
			e.setEmail(employee.getEmail());
			e.setFirst(employee.getFirst());
			e.setLast(employee.getLast());
			e.setPhone(employee.getPhone());
			e.setEnabled(employee.isEnabled());
			employeeDao.updateEmployee(e);
		} catch (Exception e) {
			log.error("Error occurred while updating employee : " + e.getMessage());
			throw new PolicySystemException(e);
		}
	}
}
