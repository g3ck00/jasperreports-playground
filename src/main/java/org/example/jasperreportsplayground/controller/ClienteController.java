package org.example.jasperreportsplayground.controller;

import net.sf.jasperreports.engine.JRException;
import org.example.jasperreportsplayground.dto.ClienteDTO;
import org.example.jasperreportsplayground.dto.FacturaReporteDTO;
import org.example.jasperreportsplayground.entity.AceCabeceraComprobanteElect;
import org.example.jasperreportsplayground.repository.AceCabeceraComprobanteElectRepository;
import org.example.jasperreportsplayground.service.ClienteService;
import org.example.jasperreportsplayground.service.FacturaReportService;
import org.example.jasperreportsplayground.service.ReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/comprobantes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ReportService reportService;
    private final FacturaReportService facturaReportService;

    public ClienteController(
            ClienteService clienteService,
            FacturaReportService facturaReportService,
            ReportService reportService
    ){
        this.clienteService = clienteService;
        this.facturaReportService=facturaReportService;
        this.reportService = reportService;
    }

    @GetMapping
    public List<ClienteDTO> readClientes() {
        return clienteService.readClientes();
    }

    //Consultar datos de prueba de Factura
    /*@GetMapping("/demo")
    public FacturaReporteDTO obtenerFacturaDemo() {

        return facturaReportService.getFacturaDemo();
    }*/

    @GetMapping("/reporte")
    public ResponseEntity<byte[]> generateReporte(
            @RequestParam(defaultValue="pdf")
            String formato
    ) throws JRException, IOException, SQLException {
        byte[] archivo=reportService.generateReporte(formato);

        //Condición ternaria; si PARAM = XLSX, genera el reporte en XLSX, sino, en PDF
        MediaType contentType=formato.equals("xlsx")
                ? MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") : MediaType.APPLICATION_PDF;

        //Misma E.S.E.N.C.I.A. de la de arriba
        String extension=formato.equals("xlsx") ? "xlsx" : "pdf";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report."+extension)
                .contentType(contentType)
                .body(archivo);
    }
}