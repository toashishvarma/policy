package com.intraedge.policy.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * User: Jitendra Patel
 * Date: 25/8/11
 * Time: 5:36 PM
 * To change this template use File | Settings | File Templates.
 */
@NamedQueries({
@NamedQuery(name="Employee.getEmployeeByEmail",query="from Employee where email=:employeeEmail")
})
@Entity
@Table(name = "employee")
public class Employee extends Person implements Serializable {
    private String password;
    private Set<EmpPolicyStatus> empPolicyStatuses;
    private boolean enabled;
    private EmployeeRole role;
    private String plainPassword;
    private boolean changePassword;
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany()
    public Set<EmpPolicyStatus> getEmpPolicyStatuses() {
        return empPolicyStatuses;
    }

    public void setEmpPolicyStatuses(Set<EmpPolicyStatus> empPolicyStatuses) {
        this.empPolicyStatuses = empPolicyStatuses;
    }
    
    @Column(nullable = false)
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
    @OneToOne(mappedBy = "employee",cascade = CascadeType.PERSIST)
    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }
    @Transient
    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }
    
    @Column(nullable=false)
	public boolean isChangePassword() {
		return changePassword;
	}

	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}
}
