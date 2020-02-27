package com.intraedge.policy.dto;

import javax.validation.constraints.Size;

/**
 * 
 * @author SujayB
 *
 */
public class ChangePassword {
	private String oldPassword;
	@Size(min=6,max=30)
	private String newPassword;
	private String retypedPassword;
	private String email;
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getRetypedPassword() {
		return retypedPassword;
	}
	public void setRetypedPassword(String retypedPassword) {
		this.retypedPassword = retypedPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
