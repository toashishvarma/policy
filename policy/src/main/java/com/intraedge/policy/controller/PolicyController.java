package com.intraedge.policy.controller;

import com.intraedge.policy.dto.ChangePassword;
import com.intraedge.policy.dto.CreatePolicyRequest;
import com.intraedge.policy.dto.Employee;
import com.intraedge.policy.dto.Policy;
import com.intraedge.policy.exception.EmployeeNotFoundException;
import com.intraedge.policy.exception.PolicySystemException;
import com.intraedge.policy.service.EmployeeService;
import com.intraedge.policy.service.PolicyService;
import com.intraedge.policy.service.ReportService;
import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author SujayB
 */
@Controller
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private Comparator<Policy> policyComparator;
    
    private Logger log = Logger.getLogger(PolicyController.class);

    @Profiled
    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request) {
        List<Policy> policies = null;
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Employee employee = null;
            employee = employeeService.getEmployeeByEmail(user.getUsername());
            if (employee.isChangePassword()) {
                ChangePassword changePassword = new ChangePassword();
                model.addAttribute("changePassword", changePassword);
                return "changePassword";
            }
            for (GrantedAuthority auth : user.getAuthorities()) {
                if (auth.getAuthority().equals("ROLE_ADMIN") || auth.getAuthority().equals("ROLE_SUPERVISOR")) {
                    policies = policyService.getAllPolicies(user.getUsername());
                    Collections.sort(policies, policyComparator);
                    model.addAttribute("policies", policies);
                    return "menu";
                }
            }
            policies = policyService.getAllActivePolicies(user.getUsername());
            Collections.sort(policies, policyComparator);
            model.addAttribute("policies", policies);
        } catch (PolicySystemException e) {
            log.error("Error : " + e.getMessage());
        }
        return "index";
    }

    @Profiled
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @Profiled
    @RequestMapping(value = {"/accept"}, method = RequestMethod.POST)
    public String acceptPolicy(@RequestParam("policyName") String pName, ModelMap model, HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        log.info("Accepting the policy for : " + name);
        policyService.acceptPolicy(pName, name);

        model.addAttribute("policy", policyService.getPolicyByName(pName));
        model.addAttribute("accepted", policyService.isPolicyAccepted(pName, name));

        return "policydetails";
    }

    @Profiled
    @RequestMapping(value = {"/showpolicy"}, method = RequestMethod.GET)
    public String showPolicy(@RequestParam("policyName") String pName, ModelMap model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        Policy policy = policyService.getPolicyByName(pName);

        model.addAttribute("policy", policy);
        model.addAttribute("accepted", policyService.isPolicyAccepted(pName, name));

        return "policydetails";
    }

    @Profiled
    @RequestMapping(value = {"/createuser"}, method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("employee") Employee e, BindingResult result, ModelMap model, HttpServletRequest request) throws Exception {
        try {
            log.info(e.getEmail());
            model.addAttribute("action","createuser.do");
            if (result.hasErrors()) {
                return "add_user";
            }
            employeeService.createEmployee(e);
            model.addAttribute("successMessage", "Employee with email " + e.getEmail() + " Created successfully in system");
        } catch (Exception ex) {
            log.error("Error  : " + ex.getMessage());
            throw ex;
        }
        return "add_user";
    }

    @Profiled
    @RequestMapping(value = {"/adduser"}, method = RequestMethod.GET)
    public String addUser(@ModelAttribute("employee") Employee e, HttpServletRequest request,ModelMap model) {
    	model.addAttribute("action","createuser.do");
        return "add_user";
    }

    @Profiled
    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "login";
    }

    @Profiled
    @RequestMapping(value = {"/changePassword"}, method = RequestMethod.POST)
    public String changePassword(@Valid ChangePassword changePassword, BindingResult result, ModelMap model, HttpServletRequest request) throws Exception {
        try {
            String passwordError = null;

            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            PasswordEncoder encoder = new Md5PasswordEncoder();
            String passwd = encoder.encodePassword(changePassword.getOldPassword(), null);
            if (!user.getPassword().equals(passwd)) {
                passwordError = "Wrong old password.";
            } else if (!changePassword.getNewPassword().equals(changePassword.getRetypedPassword())) {
                passwordError = "New Password and Re-typed password does not match.";
            }

            if (passwordError != null || result.hasErrors()) {
                model.addAttribute("passwordError", passwordError);
                return "changePassword";
            }

            employeeService.changeEmployeePassword(user.getUsername(), changePassword, false, false);
            for (GrantedAuthority auth : user.getAuthorities()) {
                if (auth.getAuthority().equals("ROLE_ADMIN")) {
                    return "menu";
                }
            }
            List<Policy> policies = policyService.getAllPolicies(user.getUsername());
            model.addAttribute("policies", policies);
        } catch (Exception e) {
            log.error("Error : " + e.getMessage());
            throw e;
        }
        return "index";
    }

    @Profiled
    @RequestMapping(value = {"/resetPassword"}, method = RequestMethod.POST)
    public String resetPassword(@RequestParam String email, ModelMap model, HttpServletRequest request) throws Exception {
        log.info("resetting password for : " + email);
        Employee employee = employeeService.getEmployeeByEmail(email);
        String passwordResetMessage = null;
        if (employee == null) {
            passwordResetMessage = email + " is not registered with system. Please contact system administrator.";
            model.addAttribute("passwordResetMessage", passwordResetMessage);
            return "forgotPassword";
        } else {
            String password = java.util.UUID.randomUUID().toString();
            ChangePassword p = new ChangePassword();
            p.setNewPassword(password);
            employeeService.changeEmployeePassword(email, p, true, true);
            passwordResetMessage = "New password has been sent to " + email + ".";
            model.addAttribute("passwordResetMessage", passwordResetMessage);
        }
        return "login";
    }

    @Profiled
    @RequestMapping(value = {"/forgotPassword"}, method = RequestMethod.GET)
    public String showForgotPasswordPage(ModelMap model, HttpServletRequest request) throws Exception {
        return "forgotPassword";
    }

    @Profiled
    @RequestMapping(value = {"/approvepolicy"}, method = RequestMethod.POST)
    public String approvePolicy(@RequestParam("policyName") String pName, ModelMap model, HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        log.info("Accepting the policy for : " + name);
        policyService.approvePolicy(pName, name);

        model.addAttribute("policy", policyService.getPolicyByName(pName));
        model.addAttribute("accepted", policyService.isPolicyAccepted(pName, name));

        return "policydetails";
    }

    @Profiled
    @RequestMapping(value = {"/listemployees"})
    public String listEmployees(ModelMap model, HttpServletRequest request) throws Exception {
        model.addAttribute("employees", employeeService.listEmployees());
        return "listemployees";
    }

    @Profiled
    @RequestMapping(value = {"/addpolicy"}, method = RequestMethod.GET)
    public String addPolicy(ModelMap model, HttpServletRequest request) {
        CreatePolicyRequest changePolicyRequest = new CreatePolicyRequest();
        model.addAttribute("createPolicyRequest", changePolicyRequest);
        return "addPolicy";
    }

    @Profiled
    @RequestMapping(value = {"/createpolicy"}, method = RequestMethod.POST)
    public String createPolicy(@Valid CreatePolicyRequest createPolicyRequest, BindingResult result, ModelMap model, HttpServletRequest request) throws Exception {
        try {
            if (result.hasErrors()) {
                return "addPolicy";
            }
            log.info(createPolicyRequest.getPolicy().getFileData().getOriginalFilename());
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            policyService.createPolicy(createPolicyRequest, user.getUsername());
            model.addAttribute("createPolicyRequest", createPolicyRequest);
            model.addAttribute("successMessage", "Policy " + createPolicyRequest.getPolicy().getName() + " created successfully in draft state.");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
        return "addPolicy";
    }

/*

    */
/**
 * Handles and retrieves the download page
 *
 * @return the name of the JSP page
 *//*

    @Profiled
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String getDownloadPage() {
        log.debug("Received request to show download page");

        return "downloadpage";
    }
*/

   
    /**
     * Writes the report to the output stream
     */
    private void writeReportToResponseStream(HttpServletResponse response, ByteArrayOutputStream baos) {

        log.debug("Writing report to the stream");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            baos.writeTo(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            log.error("Unable to write report to the output stream");
        }
    }
    
    @Profiled
    @RequestMapping(value = {"/updateuserpage"}, method = RequestMethod.GET)
    public String updateUser(@RequestParam("email") String email, ModelMap model, HttpServletRequest request) throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        Employee employee=null;
        log.info("Accepting the policy for : " + name);
        model.addAttribute("action","updateuser.do");
        try {
			employee=employeeService.getEmployeeByEmail(email);
		} catch (PolicySystemException e) {
			log.error("Error while getting update page : "+e.getMessage());
			throw e;
		}
        model.addAttribute("employee",employee);
        model.addAttribute("update",true);
        return "add_user";
    }
    
    @Profiled
    @RequestMapping(value = {"/updateuser"}, method = RequestMethod.POST)
    public String updateUser(@Valid @ModelAttribute("employee") Employee e, BindingResult result, ModelMap model, HttpServletRequest request) throws Exception {
        try {
            log.info(e);
            model.addAttribute("update",true);
            model.addAttribute("action","updateuser.do");
            if (result.hasErrors()) {
                return "add_user";
            }
            employeeService.updateEmployee(e);
           
            model.addAttribute("successMessage", "Employee with email " + e.getEmail() + " Updated successfully in system");
        }catch(EmployeeNotFoundException ex){ 
        		model.addAttribute("errorMessage", ex.getMessage());
        }catch (Exception ex) {
            log.error("Error  : " + ex.getMessage());
            throw ex;
        }
        return "add_user";
    }
    
    @Profiled
    @RequestMapping(value = {"/document"}, method = RequestMethod.GET)
    public String getPolicyDoc(@RequestParam("policyName") String policyName, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("Getting policy doc for : " + policyName);
        model.addAttribute("action","updateuser.do");
        try {
        	response.setContentType("application/pdf");
			policyService.getPolicyDocument(policyName, response.getOutputStream());
		} catch (Exception e) {
			log.error("Error while getting policy : "+e.getMessage());
			throw e;
		}
        return null;
    }
    
}
