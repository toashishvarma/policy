package com.intraedge.policy.service;

import com.intraedge.policy.service.report.Exportable;
import com.intraedge.policy.service.report.PDFExporter;
import com.intraedge.policy.service.report.XLSExport;
import com.lowagie.text.pdf.codec.Base64.OutputStream;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: jpatel
 * Date: 22/2/12
 * Time: 3:43 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private DataSource dataSource;

    @Override
    public void exportPolicyReportASPDF(java.io.OutputStream os) throws JRException, SQLException {
        exportPolicyReport(new PDFExporter(),os);
    }

    @Override
    public void exportPolicyReportASXLS(java.io.OutputStream os) throws JRException, SQLException {
        exportPolicyReport(new XLSExport(),os);
    }

    private void exportPolicyReport(Exportable exporter,java.io.OutputStream os) throws JRException, SQLException {
        HashMap jasperParameter = new HashMap();
        //JasperReport jasperReport = JasperCompileManager.compileReport("C:\\POC\\PolicySystem\\src\\main\\resources\\templates\\jasper\\employees_policy_acceptance_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getClassLoader().getResourceAsStream("templates/jasper/employees_policy_acceptance_report.jrxml"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, dataSource.getConnection());
        exporter.export(jasperPrint, os);
    }
}
