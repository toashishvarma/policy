package com.intraedge.policy.dao;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.intraedge.policy.auditing.Audit;
import com.intraedge.policy.dao.entity.EmpPolicyStatus;
import com.intraedge.policy.dao.entity.Policy;
import com.intraedge.policy.exception.PolicySystemException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * @author SujayB
 * 
 */
@Repository("policyDao")
public class PolicyDaoImpl implements PolicyDao {
	private Logger log = Logger.getLogger(PolicyDaoImpl.class);

	@PersistenceContext(name = "policy-jpa")
	private EntityManager entityManager;
	
	@Autowired
	private ComboPooledDataSource ds;

	@Override
	public List<Policy> getAllActivePolicies() {
		String query = "from " + Policy.class.getName() + " where enabled=true";
		return entityManager.createQuery(query, Policy.class).getResultList();
	}

	@Override
	public List<Policy> getAllPolicies() {
		String sQuery = "from " + Policy.class.getName();
		return entityManager.createQuery(sQuery, Policy.class).getResultList();
	}

	@Override
	@Audit
	public void createPolicy(Policy policy) {
		entityManager.persist(policy);
	}

	@Override
	public Policy getPolicyByName(String name) {
		Policy policy = null;
		try {
			Query query = entityManager.createQuery("from " + Policy.class.getName() + " p where p.name=:policyName");
			query.setParameter("policyName", name);
			policy = (Policy) query.getSingleResult();
		} catch (NoResultException e) {
		}
		return policy;
	}

	@Override
	public EmpPolicyStatus getEmployeePolicyStatusByPolicyName(String policyName, String employeeEmail) {
		EmpPolicyStatus policyStatus = null;
		try {
			Query query = entityManager.createNamedQuery("EmpPolicyStatus.policyStatusByName");
			query.setParameter("policyName", policyName);
			query.setParameter("employeeEmail", employeeEmail);
			policyStatus = (EmpPolicyStatus) query.getSingleResult();
		} catch (NoResultException e) {
			log.info("No result for policy "+policyName+" and employee "+employeeEmail);
		}catch(EmptyResultDataAccessException e){
			log.info("No result for policy "+policyName+" and employee "+employeeEmail);
		}
		return policyStatus;
	}

	@Override
	public void createPolicyStatusForEmployee(EmpPolicyStatus status) {
		entityManager.persist(status);
		entityManager.flush();
	}

	@Override
	public Policy findById(Long id) {
		return entityManager.find(Policy.class, id);
	}

	@Override
	@Audit
	public void updatePolicy(Policy policy) {
		entityManager.merge(policy);
	}

	@Override
	public void getDocument(String policyName, OutputStream os) throws Exception {
		String query="select file from policy where name='"+policyName+"'";
		Connection con = ds.getConnection();
		ResultSet rs=con.prepareStatement(query).executeQuery();
		Blob cb = null;
		if(rs.next()){
			cb=rs.getBlob(1);
		}else{
			throw new Exception();
		}
		IOUtils.copy(cb.getBinaryStream(), os);
		os.flush();
		os.close();
	}
}
