package com.intraedge.policy.mail;

import com.intraedge.policy.dao.entity.Employee;

/**
 * User: Jitendra Patel
 * Date: 4/1/12
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EmailService {

    /**
     *
     * @param empId
     * @param policyId
     */
    public void acceptPolicyNotification(Long empId, Long policyId);

    /**
     *
     * @param employee
     *
     */
    public void userCreatedNotification(Employee employee);

    /**
     *
     * @param employee
     */
    public void resetPasswordNotification(Employee employee);

    /**
     *
     * @param policyId
     */
    public void policyAddedNotification(Long policyId);
}
