package pe.controlador.ventas;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.ByteArrayOutputStream;
import pe.controlador.administracion.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pe.controlador.JsonTransformer;
import pe.modelo.dao.ventas.IReporteDao;

@Controller
public class ReporteController {

    @Autowired
    JsonTransformer jsonTransformer;

    @Autowired
    IReporteDao reporteDao;

    @RequestMapping(value = "/reporte/descargar/{anhio}/{idempresa}", method = RequestMethod.GET, produces = "application/pdf")
    public void descargar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("anhio") Integer anhio, @PathVariable("idempresa") Long idEmpresa) throws IOException {
        //PrintWriter out = httpServletResponse.getWriter();
        try {
            JasperPrint print = reporteDao.generarVentaXPlacaAnual(anhio, idEmpresa);
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            JasperExportManager.exportReportToPdfStream(print, out);

            byte[] data = out.toByteArray();

            httpServletResponse.setContentType("application/pdf");
            //To make it a download change "inline" to "attachment"
            httpServletResponse.setHeader("Content-disposition", "inline; filename=prueba.pdf");
            httpServletResponse.setContentLength(data.length);

            httpServletResponse.getOutputStream().write(data);
            //httpServletResponse.getOutputStream().flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println("{\"RSP\":\"ERROR\",\"MSG\":\"" + ex.getMessage() + "\"}");
        }
    }

    @RequestMapping(value = "/ventas/reporte/venta-x-placa-anual/{anhio}/{idempresa}/{tipo}", method = RequestMethod.GET)
    public void generarVentaXPlacaAnual(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("anhio") Integer anhio, @PathVariable("idempresa") Long idEmpresa, @PathVariable("tipo") String tipo) throws IOException {
        try {
            JasperPrint print = reporteDao.generarVentaXPlacaAnual(anhio, idEmpresa);
            JRExporter exporter = null;
            OutputStream ouputStream = httpServletResponse.getOutputStream();
            if (tipo.equals("html")) {

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                httpServletRequest.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, print);
                exporter = new JRHtmlExporter();

                exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.TRUE);
                exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, new Boolean(false));
                exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
                exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, new HashMap());
                exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRHtmlExporterParameter.OUTPUT_STREAM, baos);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, httpServletRequest.getContextPath() + "/srvImagenReporte?image=");
            } else if (tipo.equals("xls")) {
                httpServletResponse.setContentType("application/xls");
                httpServletResponse.setHeader("Content-Disposition", "inline; filename=\"venta-x-placa-anual.xls\"");
                exporter = new JRXlsExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
            } else if (tipo.equals("pdf")) {
                httpServletResponse.setContentType("application/pdf");
                exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
            }
            exporter.exportReport();
            ouputStream.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println("{\"RSP\":\"ERROR\",\"MSG\":\"" + ex.getMessage() + "\"}");
        }
    }

    @RequestMapping(value = "/ventas/reporte/con-documento-venta/{tipo}/{idEmpresa}/{anhio}/{mes}/{diaInicio}/{diaFin}/{inicioCorrelativo}", method = RequestMethod.GET)
    public void generarConDocumentoVenta(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("tipo") String tipo, @PathVariable("idEmpresa") Long idEmpresa, @PathVariable("anhio") Integer anhio, @PathVariable("mes") Integer mes, @PathVariable("diaInicio") Integer diaInicio, @PathVariable("diaFin") Integer diaFin, @PathVariable("inicioCorrelativo") Integer inicioCorrelativo) throws IOException {
        try {
            JasperPrint print = reporteDao.generarConDocumentoVenta(idEmpresa, anhio, mes, diaInicio, diaFin, inicioCorrelativo);
            JRExporter exporter = null;
            OutputStream ouputStream = httpServletResponse.getOutputStream();
            if (tipo.equals("html")) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                httpServletRequest.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, print);
                exporter = new JRHtmlExporter();
                exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.TRUE);
                exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, new Boolean(false));
                exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
                exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, new HashMap());
                exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRHtmlExporterParameter.OUTPUT_STREAM, baos);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, httpServletRequest.getContextPath() + "/srvImagenReporte?image=");
            } else if (tipo.equals("xls")) {
                httpServletResponse.setContentType("application/xls");
                httpServletResponse.setHeader("Content-Disposition", "inline; filename=\"venta-x-placa-anual.xls\"");
                exporter = new JRXlsExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            } else if (tipo.equals("pdf")) {
                httpServletResponse.setContentType("application/pdf");
                exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
            }
            exporter.exportReport();
            ouputStream.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println("{\"RSP\":\"ERROR\",\"MSG\":\"" + ex.getMessage() + "\"}");
        }
    }

}
