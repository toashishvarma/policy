package com.intraedge.policy.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * User: Jitendra Patel
 * Date: 4/1/12
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Employee {
	@NotEmpty
    private String first;
	@NotEmpty
    private String last;
	@Pattern(regexp=".+@.+\\.[a-z]+")
    private String email;
	@Digits(integer=15,fraction=0)
    private String phone;
    private String role;
    private boolean changePassword;
    private boolean enabled;
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getRole() {
		return role;
	}
    
    public void setRole(String role) {
		this.role = role;
	}

	public boolean isChangePassword() {
		return changePassword;
	}

	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Employee [first=" + first + ", last=" + last + ", email="
				+ email + ", phone=" + phone + ", role=" + role
				+ ", changePassword=" + changePassword + ", enabled=" + enabled
				+ "]";
	}
}
