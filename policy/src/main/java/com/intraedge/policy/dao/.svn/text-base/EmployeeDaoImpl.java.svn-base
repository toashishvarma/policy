package com.intraedge.policy.dao;

import com.intraedge.policy.auditing.Audit;
import com.intraedge.policy.dao.entity.Employee;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

/**
 * User: Jitendra Patel Date: 8/11/11 Time: 1:32 PM To change this template use
 * File | Settings | File Templates.
 */
@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {
	private Logger log = Logger.getLogger(EmployeeDaoImpl.class);

	@PersistenceContext(name = "policy-jpa")
	private EntityManager entityManager;

	@Audit
	public Long save(Employee p) {
		log.info("persisting employee=" + p);
		entityManager.persist(p);
		entityManager.flush();
		return p.getId();
	}

	public void update(Employee p) {
		// TODO Auto-generated method stub

	}

	public void delete(Employee p) {
		// TODO Auto-generated method stub

	}

	@Override
	public Employee findById(Long id) {
		log.info("Finding employee for: " + id);
		return entityManager.find(Employee.class, id);
	}

	public List<Employee> findEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployeeByEmail(String employeeEmail) {
		Employee employee = null;
		try {
			Query query = entityManager.createNamedQuery("Employee.getEmployeeByEmail");
			query.setParameter("employeeEmail", employeeEmail);
			employee = (Employee) query.getSingleResult();
		} catch (NoResultException ex) {
		}
		return employee;
	}

	@Override
	public List<Employee> listEmployees() {
		return entityManager.createQuery("from " + Employee.class.getName(), Employee.class).getResultList();
	}

	@Override
	@Audit
	public void updateEmployee(Employee employee) {
		entityManager.merge(employee);
	}
}
