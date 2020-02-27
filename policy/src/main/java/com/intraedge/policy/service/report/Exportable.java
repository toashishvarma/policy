package com.intraedge.policy.service.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.ByteArrayOutputStream;

import com.lowagie.text.pdf.codec.Base64.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: jpatel
 * Date: 22/2/12
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Exportable {

	void export(JasperPrint jp, java.io.OutputStream baos) throws JRException;
}
