package com.intraedge.policy.dao.entity;

import javax.persistence.*;

/**
 * User: Jitendra Patel
 * Date: 4/1/12
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "user_roles")
public class EmployeeRole {

    private Long id;
    private String authority;
    private Employee employee;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ROLE_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "AUTHORITY")
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setAutority(boolean admin) {
        this.authority = admin ?  ROLES.ADMIN.role: ROLES.USER.role;
    }

    @OneToOne (cascade=CascadeType.PERSIST)
    @JoinColumn(name="USER_ID")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private enum ROLES {
        USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

        private String role;
        ROLES(String role) {
            this.role = role;
        }
    }
}
