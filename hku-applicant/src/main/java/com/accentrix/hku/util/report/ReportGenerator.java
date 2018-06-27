package com.accentrix.hku.util.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ReportGenerator {

    private final static Logger logger = LoggerFactory.getLogger(ReportGenerator.class);

    private static final String WEB_INF = "/WEB-INF";

    public static StreamedContent generaterPDF(String jasperTemplatePath, String pdfFileName,
            Map<String, Object> params, JRBeanCollectionDataSource dataSource, Connection conn)
            throws JRException, SQLException {
        logger.info("generaterPDF");
        ByteArrayOutputStream out = null;
        InputStream in = null;
        try {
            JasperPrint print = fillReport(jasperTemplatePath, params, dataSource, conn);
            out = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(print, out);
            in = new ByteArrayInputStream(out.toByteArray());
            StreamedContent file = new DefaultStreamedContent(in, "application/pdf", pdfFileName);
            return file;
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(in);
        }
    }

    private static JasperPrint fillReport(String jasperTemplatePath, Map<String, Object> parameters,
            JRBeanCollectionDataSource dataSource, Connection conn) throws JRException, SQLException {
        logger.info("fillReport");
        JasperReport jasperReport = null;
        InputStream jasperStream = null;
        try {
            jasperStream = FacesContext.getCurrentInstance().getExternalContext()
                    .getResourceAsStream(WEB_INF + jasperTemplatePath);
            JasperDesign jasperDesign = JRXmlLoader.load(jasperStream);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            parameters.put("REPORT_LOCALE", Locale.ENGLISH);

            JasperPrint print = null;
            if (dataSource != null) {
                print = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            } else if (conn != null) {
                print = JasperFillManager.fillReport(jasperReport, parameters, conn);
            }
            return print;
        } finally {
            IOUtils.closeQuietly(jasperStream);
        }
    }

}
