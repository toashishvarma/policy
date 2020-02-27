package com.intraedge.policy.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.intraedge.policy.service.ReportService;
/**
 * 
 * @author sbawaskar
 *
 */
@Controller
public class PolicyReportController {
	
	/** Reporting service*/
	 @Autowired
	 private ReportService reportService;
	 /** Logger */
	 private Logger log = Logger.getLogger(PolicyReportController.class);
	 
	 /**
     * Retrieves the download file in XLS format
     *
     * @return
     */
    @Profiled
    @RequestMapping(value = "/download/xls", method = RequestMethod.GET)
    public ModelAndView xlsReport(HttpServletResponse response) {
        log.debug("Received request to download Excel report");
        try {
            String fileName = "PolicyReport.xls";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.setContentType("application/vnd.ms-excel");
            reportService.exportPolicyReportASXLS(response.getOutputStream());
        } catch (Exception e) {
            log.error("Ërror exporting policy report: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves the download file in XLS format
     *
     * @return
     */
    @Profiled
    @RequestMapping(value = "/download/pdf", method = RequestMethod.GET)
    public ModelAndView pdfReport(HttpServletResponse response) {
        log.debug("Received request to download PDF report");
        try {
            String fileName = "PolicyReport.pdf";
        	response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.setContentType("application/pdf");
            reportService.exportPolicyReportASPDF(response.getOutputStream());
        } catch (Exception e) {
            log.error("Ërror exporting policy report: " + e.getMessage());
        }

        return null;
    }
    
    @Profiled
    @RequestMapping(value = {"/reports"}, method = RequestMethod.GET)
    public String showPolicy(ModelMap model) {
    	return "reports";
    }

}
