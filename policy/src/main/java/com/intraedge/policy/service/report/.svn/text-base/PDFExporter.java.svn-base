package com.intraedge.policy.service.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import java.io.ByteArrayOutputStream;

import com.lowagie.text.pdf.codec.Base64.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: jpatel
 * Date: 22/2/12
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class PDFExporter implements Exportable {
    @Override
    public void export(JasperPrint jp, java.io.OutputStream baos) throws JRException {
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        exporter.exportReport();
    }
}


