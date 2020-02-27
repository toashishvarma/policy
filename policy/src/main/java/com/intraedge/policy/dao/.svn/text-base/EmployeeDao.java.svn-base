package com.intraedge.policy.dao;

import java.util.List;

import com.intraedge.policy.dao.entity.Employee;

public interface EmployeeDao extends IPersonDao<Employee> {
	/**
	 * 
	 * @param employeeEmail
	 * @return
	 */
	Employee getEmployeeByEmail(String employeeEmail);

	/**
	 * @return
	 */
	List<Employee> listEmployees();

	/**
	 * 
	 * @param employee
	 */
	void updateEmployee(Employee employee);
}
