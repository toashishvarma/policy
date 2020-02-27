package com.intraedge.policy.dao;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import com.intraedge.policy.dao.entity.EmpPolicyStatus;
import com.intraedge.policy.dao.entity.Policy;

/**
 * 
 * @author SujayB
 * 
 */
public interface PolicyDao {
	/**
	 * 
	 * @return
	 */
	public List<Policy> getAllPolicies();

	/**
	 * 
	 * @param policy
	 */
	void createPolicy(Policy policy);

	/**
	 * 
	 * @param name
	 * @return
	 */
	Policy getPolicyByName(String name);

	/**
	 * 
	 * @param policyName
	 * @param employeeEmail
	 * @return
	 */
	EmpPolicyStatus getEmployeePolicyStatusByPolicyName(String policyName, String employeeEmail);

	/**
	 * 
	 * @param status
	 */
	void createPolicyStatusForEmployee(EmpPolicyStatus status);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Policy findById(Long id);

	/**
	 * 
	 * @return
	 */
	List<Policy> getAllActivePolicies();

	/**
	 * 
	 * @param policy
	 */
	public void updatePolicy(Policy policy);
	/**
	 * 
	 * @param policyName
	 * @param os
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	public void getDocument(String policyName, OutputStream os) throws Exception;
}
