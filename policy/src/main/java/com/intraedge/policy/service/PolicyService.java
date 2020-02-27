package com.intraedge.policy.service;

import java.io.OutputStream;
import java.util.List;

import com.intraedge.policy.dto.CreatePolicyRequest;
import com.intraedge.policy.dto.Policy;
import com.intraedge.policy.exception.PolicySystemException;

/**
 * 
 * @author SujayB
 * 
 */
public interface PolicyService {
	/**
	 * 
	 * @return
	 */
	public List<Policy> getAllPolicies();

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Policy getPolicyByName(String name);

	/**
	 * 
	 * @param policyName
	 * @return
	 */
	boolean acceptPolicy(String policyName, String employeeEmail);

	/**
	 * 
	 * @param policy
	 * @param empEmail
	 * @return
	 */
	boolean isPolicyAccepted(String policy, String empEmail);

	/**
	 * 
	 * @param userName
	 * @return
	 */
	List<Policy> getAllPolicies(String userName);

	/**
	 * 
	 * @param userName
	 * @return
	 */
	List<Policy> getAllActivePolicies(String userName);

	/**
	 * 
	 * @param policyName
	 * @param employeeEmail
	 * @return
	 */
	boolean approvePolicy(String policyName, String employeeEmail);

	/**
	 * 
	 * @param request
	 * @param userId
	 * @throws PolicySystemException
	 */
	void createPolicy(CreatePolicyRequest request, String userId) throws PolicySystemException;

	/**
	 * 
	 * @param policyName
	 * @param out
	 * @throws PolicySystemException 
	 */
	void getPolicyDocument(String policyName, OutputStream out) throws PolicySystemException;
}
