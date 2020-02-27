package com.intraedge.policy.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * User: Jitendra Patel
 * Date: 28/11/11
 * Time: 5:37 PM
 * To change this template use File | Settings | File Templates.
 */

@MappedSuperclass
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract public class Person extends AuditableEntity implements Serializable {
    private Long id;
    private String first;
    private String last;
    private String email;
    private String phone;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Column(nullable = false, unique = true)
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        sb.append(", first='").append(first).append('\'');
        sb.append(", last='").append(last).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        return sb.toString();
    }
}
