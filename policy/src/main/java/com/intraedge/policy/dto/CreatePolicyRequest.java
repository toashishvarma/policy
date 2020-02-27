package com.intraedge.policy.dto;

import javax.validation.Valid;
/**
 * 
 * @author SujayB
 *
 */
public class CreatePolicyRequest {
	@Valid
	private Policy policy;
	private String changeType;
	
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
}
