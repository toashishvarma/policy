package com.intraedge.policy.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.intraedge.policy.dao.EmployeeDao;
import com.intraedge.policy.dao.PolicyDao;
import com.intraedge.policy.dao.entity.EmpPolicyStatus;
import com.intraedge.policy.dao.entity.Employee;
import com.intraedge.policy.dto.CreatePolicyRequest;
import com.intraedge.policy.dto.Policy;
import com.intraedge.policy.exception.PolicySystemException;
import com.intraedge.policy.mail.EmailService;

/**
 * 
 * @author SujayB
 * 
 */
@Service
@TransactionConfiguration(defaultRollback = true)
public class PolicyServiceImpl implements PolicyService {
	private Logger log = Logger.getLogger(PolicyServiceImpl.class);
	@Autowired
	private PolicyDao policyDao;
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
	public List<Policy> getAllPolicies() {
		List<Policy> policyDtos = new ArrayList<Policy>();
		List<com.intraedge.policy.dao.entity.Policy> policyList = policyDao.getAllPolicies();
		for (com.intraedge.policy.dao.entity.Policy entity : policyList) {
			Policy dto = new Policy();
			dto.setName(entity.getName());
			dto.setEnabled(entity.isEnabled());
			dto.setCreatedBy(entity.getCreatedBy());
			dto.setModifiedBy(entity.getModifiedBy());
			dto.setCreatedDate(entity.getCreationTime());
			dto.setModifiedDate(entity.getModificationTime());
			policyDtos.add(dto);
		}
		return policyDtos;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<Policy> getAllPolicies(String userName) {
		List<Policy> policyDtos = new ArrayList<Policy>();
		List<com.intraedge.policy.dao.entity.Policy> policyList = policyDao.getAllPolicies();
		for (com.intraedge.policy.dao.entity.Policy entity : policyList) {
			Policy dto = new Policy();
			dto.setName(entity.getName());
			dto.setDescription(entity.getDescription());
			dto.setEnabled(entity.isEnabled());
			dto.setCreatedBy(entity.getCreatedBy());
			EmpPolicyStatus status = policyDao.getEmployeePolicyStatusByPolicyName(entity.getName(), userName);
			if (status == null) {
				dto.setAccepted(false);
			} else {
				dto.setAccepted(true);
			}
			policyDtos.add(dto);
		}
		return policyDtos;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<Policy> getAllActivePolicies(String userName) {
		List<Policy> policyDtos = new ArrayList<Policy>();
		List<com.intraedge.policy.dao.entity.Policy> policyList = policyDao.getAllActivePolicies();
		for (com.intraedge.policy.dao.entity.Policy entity : policyList) {
			Policy dto = new Policy();
			dto.setName(entity.getName());
			dto.setDescription(entity.getDescription());
			EmpPolicyStatus status = policyDao.getEmployeePolicyStatusByPolicyName(entity.getName(), userName);
			if (status == null) {
				dto.setAccepted(false);
			} else {
				dto.setAccepted(true);
			}
			policyDtos.add(dto);
		}
		return policyDtos;
	}

	@Override
	public Policy getPolicyByName(String name) {
		Policy policyDto = new Policy();
		com.intraedge.policy.dao.entity.Policy policyEntity = policyDao.getPolicyByName(name);
		policyDto.setName(policyEntity.getName());
		policyDto.setDescription(policyEntity.getDescription());
		policyDto.setPath(policyEntity.getPath());
		policyDto.setEnabled(policyEntity.isEnabled());
		policyDto.setCreatedBy(policyEntity.getCreatedBy());
		policyDto.setModifiedBy(policyEntity.getModifiedBy());
		policyDto.setCreatedDate(policyEntity.getCreationTime());
		policyDto.setModifiedDate(policyEntity.getModificationTime());
		return policyDto;
	}

	@Transactional(rollbackFor = Exception.class, noRollbackFor = NoResultException.class)
	@Override
	public boolean acceptPolicy(String policyName, String employeeEmail) {
		try {
			if (isPolicyAccepted(policyName, employeeEmail))
				return false;

			com.intraedge.policy.dao.entity.Policy policy = policyDao.getPolicyByName(policyName);
			Employee employee = employeeDao.getEmployeeByEmail(employeeEmail);
			EmpPolicyStatus status = new EmpPolicyStatus();
			status.setPolicy(policy);
			status.setEmployee(employee);
			status.setAcceptedDate(new Date());
			policyDao.createPolicyStatusForEmployee(status);
			emailService.acceptPolicyNotification(employee.getId(), policy.getId());
			return true;
		} catch (Exception e) {
			log.error("error accepting policy : " + e.getMessage());
			return false;
		}
	}

	@Transactional(rollbackFor = Exception.class, noRollbackFor = NoResultException.class)
	@Override
	public boolean approvePolicy(String policyName, String employeeEmail) {
		try {
			com.intraedge.policy.dao.entity.Policy policy = policyDao.getPolicyByName(policyName);
			if (policy.isEnabled()) {
				return true;
			}
			policy.setEnabled(true);
			policyDao.updatePolicy(policy);

			// Send notification emails
			emailService.policyAddedNotification(policy.getId());

			return true;
		} catch (Exception e) {
			log.error("error accepting policy : " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean isPolicyAccepted(String policy, String empEmail) {
		return policyDao.getEmployeePolicyStatusByPolicyName(policy, empEmail) != null;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void createPolicy(CreatePolicyRequest request, String userId) throws PolicySystemException {
		try {
		com.intraedge.policy.dao.entity.Policy policy = new com.intraedge.policy.dao.entity.Policy();
		policy.setName(request.getPolicy().getName());
		policy.setDescription(request.getPolicy().getDescription());
		policy.setPath(policyWebBasePath + request.getPolicy().getFileData().getOriginalFilename());
		policy.setFile(request.getPolicy().getFileData().getBytes());
		File file = new File(policyBasePath + request.getPolicy().getFileData().getOriginalFilename());
		policyDao.createPolicy(policy);
			request.getPolicy().getFileData().transferTo(file);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PolicySystemException(e);
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void getPolicyDocument(String policyName,OutputStream out) throws PolicySystemException{
		try {
			policyDao.getDocument(policyName, out);
		} catch (Exception e) {
			log.error("Error while getting doc : "+e.getMessage());
			throw new PolicySystemException(e);
		}
	}
	
}
