package com.intraedge.policy.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User: Jitendra Patel
 * Date: 30/12/11
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
@NamedQueries({
@NamedQuery(name="EmpPolicyStatus.policyStatusByName",query="from EmpPolicyStatus where policy.name=:policyName and employee.email=:employeeEmail")
})
@Entity
@Table(name = "emp_policy_status")
public class EmpPolicyStatus implements Serializable {
    private Long id;
    private Employee employee;
    private Policy policy;
    private Date acceptedDate;
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(cascade=CascadeType.MERGE)
    public Policy getPolicy() {
        return policy;
    }

    @ManyToOne(cascade=CascadeType.MERGE)
    public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setPolicy(Policy policy) {
        this.policy = policy;
    }
	
	@Column(nullable=false,name="accepted_date")
	@Temporal(TemporalType.DATE)
	public Date getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}
}
