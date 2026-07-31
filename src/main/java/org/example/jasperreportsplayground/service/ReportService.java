package org.example.jasperreportsplayground.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.example.jasperreportsplayground.dto.ComprobanteReporteDTO;
import org.example.jasperreportsplayground.dto.DetalleFacturaDTO;
import org.example.jasperreportsplayground.repository.AceCabeceraComprobanteElectRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ReportService {

    private final ComprobanteReportService comprobanteReportService;
    private final AceCabeceraComprobanteElectRepository aceCabeceraComprobanteElectRepository;

    public ReportService(
            ComprobanteReportService comprobanteReportService,
            AceCabeceraComprobanteElectRepository aceCabeceraComprobanteElectRepository
    ) {
        this.comprobanteReportService = comprobanteReportService;
        this.aceCabeceraComprobanteElectRepository = aceCabeceraComprobanteElectRepository;
    }

    // Generar Reporte Por ID
    public byte[] generarReportePorId(
            Long codigo,
            Integer ageLicencCodigo,
            String formato
    ) throws JRException, IOException {

        ComprobanteReporteDTO factura = comprobanteReportService.readFactura(codigo, ageLicencCodigo);

        return generarReporte(formato, factura);
    }

    public byte[] generarReporte(String formato, ComprobanteReporteDTO factura
    ) throws JRException, IOException {

        Map<String, Object> parametros = new HashMap<>();

        // Definir logo
        InputStream logo = getClass().getResourceAsStream("/company-logo-transparent-png-19.png");

        if (logo == null) {
            throw new IllegalStateException("No se encontró el logo");
        }

        parametros.put("logoEmpresa", logo);

        // Definir fecha del reporte

        parametros.put("fechaReporte", new java.util.Date());

        // Definir Datasource

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(List.of(factura));

        // Definir el archivo desde los recursos locales de la PC (no recomendado para desarrollo)
        // Oficina
        //InputStream reporte = new FileInputStream("C:\\Users\\bescalante\\JaspersoftWorkspace\\MyReports\\factura_sasf.jrxml");
        InputStream reporte = new FileInputStream("C:\\Users\\bescalante\\Desktop\\factura_sasf.jrxml");

        // Definir el archivo desde los recursos locales de la PC (no recomendado para desarrollo)
        // Casa
        //InputStream reporte = new FileInputStream("C:\\Users\\Bryantcore3\\Desktop\\factura_sasf.jrxml");

        // Definir el JRXML desde la ruta del IDE
        /*
        InputStream reporte =getClass().getResourceAsStream("/reports/factura.jrxml");
         */

        if (reporte == null) {
            throw new RuntimeException("No se encontró el reporte");
        }

        // Compilar el reporte
        JasperReport jasperReport = JasperCompileManager.compileReport(reporte);

        System.out.println(factura.getIdentificacion());
        System.out.println(factura.getRazonSocial());
        System.out.println(factura.getFechaEmision());

        // Rellenar el reporte
        JasperPrint print = JasperFillManager.fillReport(
                        jasperReport,
                        parametros,
                        dataSource
        );

        System.out.println("Paginas: " + print.getPages().size());

        // Exportar el reporte

        if (formato.equalsIgnoreCase("pdf")) {
            return JasperExportManager.exportReportToPdf(print);
        }

        if (formato.equalsIgnoreCase("xlsx")) {

            ByteArrayOutputStream output = new ByteArrayOutputStream();

            JRXlsxExporter exporter = new JRXlsxExporter();

            exporter.setExporterInput(new SimpleExporterInput(print));

            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));

            exporter.exportReport();

            return output.toByteArray();
        }

        throw new IllegalArgumentException("Formato no soportado: " + formato);
    }
}