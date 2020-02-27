package com.intraedge.policy.dao;

import com.intraedge.policy.dao.entity.Employee;
import com.intraedge.policy.dao.entity.Person;

import java.util.List;

/**
 * User: Jitendra Patel
 * Date: 8/11/11
 * Time: 1:29 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IPersonDao<T extends Person> {

    public Long save(T p);
    public void update(T p);
    public void delete(T p);
    public T findById(Long id);
    public List<Employee> findEmployee(Employee employee);
}
