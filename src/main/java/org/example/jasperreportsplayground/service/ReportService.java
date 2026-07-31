package org.example.jasperreportsplayground.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.example.jasperreportsplayground.dto.FacturaReporteDTO;
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

    private final FacturaReportService facturaReportService;
    private final AceCabeceraComprobanteElectRepository aceCabeceraComprobanteElectRepository;

    public ReportService(
            FacturaReportService facturaReportService,
            AceCabeceraComprobanteElectRepository aceCabeceraComprobanteElectRepository
    ) {
        this.facturaReportService = facturaReportService;
        this.aceCabeceraComprobanteElectRepository = aceCabeceraComprobanteElectRepository;
    }

    // Generar Reporte Por ID
    public byte[] generarReportePorId(
            Long codigo,
            Integer ageLicencCodigo,
            String formato
    ) throws JRException, IOException {

        FacturaReporteDTO factura = facturaReportService.readFactura(codigo, ageLicencCodigo);

        return generarReporte(formato, factura);
    }

    public byte[] generarReporte(String formato, FacturaReporteDTO factura
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

        //Traer el archivo desde los recursos locales de la PC (no recomendado para desarrollo)
        //Oficina
        InputStream reporte = new FileInputStream("C:\\Users\\bescalante\\JaspersoftWorkspace\\MyReports\\factura_sasf.jrxml");

        //Casa
        //InputStream reporte = new FileInputStream("C:\\Users\\Bryantcore3\\Desktop\\factura_sasf.jrxml");

        /*
        InputStream reporte =
                getClass()
                        .getResourceAsStream(
                                "/reports/factura.jrxml"
                        );
         */

        if (reporte == null) {
            throw new RuntimeException(
                    "No se encontró el reporte"
            );
        }

        /*
         * ============================
         * Compilar reporte
         * ============================
         */

        JasperReport jasperReport = JasperCompileManager.compileReport(reporte);

        /*
         * ============================
         * Llenar reporte
         * ============================
         */

        JasperPrint print =
                JasperFillManager.fillReport(
                        jasperReport,
                        parametros,
                        dataSource
                );

        System.out.println("Paginas: " + print.getPages().size());

        /*
         * ============================
         * Exportar
         * ============================
         */

        if (formato.equalsIgnoreCase("pdf")) {

            return JasperExportManager
                    .exportReportToPdf(print);

        }

        if (formato.equalsIgnoreCase("xlsx")) {

            ByteArrayOutputStream output =
                    new ByteArrayOutputStream();

            JRXlsxExporter exporter =
                    new JRXlsxExporter();

            exporter.setExporterInput(
                    new SimpleExporterInput(print)
            );

            exporter.setExporterOutput(
                    new SimpleOutputStreamExporterOutput(
                            output
                    )
            );

            exporter.exportReport();

            return output.toByteArray();
        }

        throw new IllegalArgumentException(
                "Formato no soportado: " + formato
        );
    }
}