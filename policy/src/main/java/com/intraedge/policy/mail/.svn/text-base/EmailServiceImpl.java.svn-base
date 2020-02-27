package com.intraedge.policy.mail;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.intraedge.policy.dao.EmployeeDao;
import com.intraedge.policy.dao.PolicyDao;
import com.intraedge.policy.dao.entity.Employee;
import com.intraedge.policy.dao.entity.Policy;

/**
 * User: Jitendra Patel
 * Date: 4/1/12
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmailServiceImpl implements  EmailService {

    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;
    private String adminEmail;
    private Logger log = Logger.getLogger(EmailServiceImpl.class);

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    PolicyDao policyDao;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    @Async
    @Override
    public void policyAddedNotification(Long policyId) {
        Policy policy = policyDao.findById(policyId);
        for(Employee e: employeeDao.listEmployees()) {
            try {
            	if(e.isEnabled()){
            		policyAddedNotification(e, policy);	
            	}
            } catch (Throwable ex) { log.info("Error sending policy added notification for: " + e.getEmail()); }
        }
    }

    @Async
    @Override
    public void acceptPolicyNotification(Long empId, Long policyId) {
        Employee employee = employeeDao.findById(empId);
        Policy policy = policyDao.findById(policyId);

        Map<String, Object> model = new HashMap<String, Object>();

        model.put("employee", employee);
        model.put("policy", policy);

        sendEmail(employee.getEmail(), adminEmail, new String[]{employee.getEmail()}, "[Policy Accepted]: " + policy.getName(), "policy_accepted_notification.vm", model);
    }

    @Async
    @Override
    public void userCreatedNotification(Employee employee) {
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("employee", employee);
        try {
            model.put("url", "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/policy/login.do");
        } catch (UnknownHostException e) { }

        sendEmail(adminEmail, employee.getEmail(), new String[]{adminEmail}, "[Account Created]: Policy Management System", "user_notification.vm", model);
    }

    @Async
    @Override
    public void resetPasswordNotification(Employee employee) {
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("employee", employee);
        try {
            model.put("url", "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/policy/login.do");
        } catch (UnknownHostException e) { }

        sendEmail(adminEmail, employee.getEmail(), null, "[Password Reset]: Policy Management System", "forgot_password.vm", model);
    }

    private void policyAddedNotification(Employee employee, Policy policy) {
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("employee", employee);
        model.put("policy", policy);

        sendEmail(employee.getEmail(), employee.getEmail(), new String[]{adminEmail}, "[New Policy Added]: " + policy.getName(), "policy_added_notification.vm", model);
    }

    private void sendEmail(final String from, final String to, final String[] cc, final String subject, final String template, final Map<String, Object> model) {
    	log.info("Sending email to "+to);
        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setSubject(subject);
                message.setTo(to);
                if (from != null) message.setFrom(from);
                if(cc != null) message.setCc(cc);

                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/" + template, model);

                message.setText(text, true);
            }
        };
        try{
        	this.mailSender.send(preparator);
        	log.info("Email has been successfully sent to "+to);
        }catch(Exception e){
        	log.error("Error sending email : "+e.getMessage());
        }
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
}
