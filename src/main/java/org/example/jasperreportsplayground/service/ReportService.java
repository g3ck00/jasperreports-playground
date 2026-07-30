package org.example.jasperreportsplayground.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.example.jasperreportsplayground.dto.FacturaReporteDTO;
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


    public ReportService(
            FacturaReportService facturaReportService
    ) {
        this.facturaReportService = facturaReportService;
    }


    public byte[] generateReporte(String formato)
            throws JRException, IOException {


        /*
         * ============================
         * Obtener datos desde Spring
         * ============================
         */

        List<FacturaReporteDTO> facturas =
                facturaReportService.readFacturas();



        /*
         * ============================
         * Parámetros del reporte
         * ============================
         */

        Map<String, Object> parametros = new HashMap<>();


        InputStream logo = getClass().getResourceAsStream("/company-logo-transparent-png-19.png");


        if (logo == null) {
            throw new IllegalStateException(
                    "No se encontró el logo"
            );
        }


        parametros.put("logoEmpresa", logo);


        parametros.put("fechaReporte", new java.util.Date()
        );



        /*
         * ============================
         * Datasource para Jasper
         * ============================
         */

        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(
                        facturas
                );



        /*
         * ============================
         * Cargar JRXML
         * ============================
         */

        //Traer el archivo desde los recursos locales de la PC (no recomendado para desarrollo)
        InputStream reporte = new FileInputStream(
                "C:\\Users\\bescalante\\JaspersoftWorkspace\\MyReports\\factura_sasf.jrxml"
        );

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

        System.out.println("Facturas: " + facturas.size());

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