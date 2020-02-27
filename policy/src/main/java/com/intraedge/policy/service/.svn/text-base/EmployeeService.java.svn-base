package com.intraedge.policy.service;

import java.util.List;

import com.intraedge.policy.dto.ChangePassword;
import com.intraedge.policy.dto.Employee;
import com.intraedge.policy.exception.EmployeeNotFoundException;
import com.intraedge.policy.exception.PolicySystemException;

/**
 * 
 * @author SujayB
 * 
 */
public interface EmployeeService {
	/**
	 * 
	 * @param employee
	 * @return
	 */
	long createEmployee(Employee employee);

	/**
	 * 
	 * @param email
	 * @return
	 * @throws PolicySystemException
	 */
	Employee getEmployeeByEmail(String email) throws PolicySystemException;

	/**
	 * @return
	 */
	List<Employee> listEmployees();

	/**
	 * 
	 * @param email
	 * @param changePassword
	 * @param resetPassword
	 * @param sendEmailNotification
	 * @throws PolicySystemException
	 */
	void changeEmployeePassword(String email, ChangePassword changePassword, boolean resetPassword, boolean sendEmailNotification) throws PolicySystemException;
	/**
	 * 
	 * @param employee
	 * @return
	 * @throws PolicySystemException
	 * @throws EmployeeNotFoundException 
	 */
	void updateEmployee(Employee employee) throws PolicySystemException, EmployeeNotFoundException;
}
