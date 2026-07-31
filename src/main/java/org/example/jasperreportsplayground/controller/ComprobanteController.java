package org.example.jasperreportsplayground.controller;

import net.sf.jasperreports.engine.JRException;
import org.example.jasperreportsplayground.dto.ClienteDTO;
import org.example.jasperreportsplayground.dto.FacturaReporteDTO;
import org.example.jasperreportsplayground.entity.AceCabeceraComprobanteElect;
import org.example.jasperreportsplayground.entity.AceCabeceraComprobanteElectId;
import org.example.jasperreportsplayground.repository.AceCabeceraComprobanteElectRepository;
import org.example.jasperreportsplayground.service.ClienteService;
import org.example.jasperreportsplayground.service.FacturaReportService;
import org.example.jasperreportsplayground.service.ReportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/comprobantes")
public class ComprobanteController {

    private final ClienteService clienteService;
    private final ReportService reportService;
    private final FacturaReportService facturaReportService;
    private final AceCabeceraComprobanteElectRepository aceCabeceraComprobanteElectRepository;

    public ComprobanteController(
            ClienteService clienteService,
            FacturaReportService facturaReportService,
            ReportService reportService,
            AceCabeceraComprobanteElectRepository aceCabeceraComprobanteElectRepository
    ){
        this.clienteService = clienteService;
        this.facturaReportService=facturaReportService;
        this.reportService = reportService;
        this.aceCabeceraComprobanteElectRepository=aceCabeceraComprobanteElectRepository;
    }

    /*
    @GetMapping
    public List<ClienteDTO> readClientes() {
        return clienteService.readClientes();
    }
     */

    // Read All Comprobantes
    @GetMapping
    public Page<AceCabeceraComprobanteElect> readAllComprobantes(Pageable pageable) {
        return facturaReportService.readAllComprobantes(pageable);
    }

    // Read Comprobante by Compound Primary Key
    @GetMapping("/busqueda")
    public ResponseEntity<AceCabeceraComprobanteElect> readComprobanteByCompoundPrimaryKey(
            @RequestParam Long codigo,
            @RequestParam int codigoDocumento
    ) {
        AceCabeceraComprobanteElectId id =
                new AceCabeceraComprobanteElectId(codigo, codigoDocumento);

        return aceCabeceraComprobanteElectRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Read Comprobante by Compound Primary Key (Endpoint with Params)
    @GetMapping("/{codigo}/{ageLicencCodigo}")
    public ResponseEntity<AceCabeceraComprobanteElect> obtener(@PathVariable Long codigo, @PathVariable Integer ageLicencCodigo
    ) {
        return ResponseEntity.ok(facturaReportService.readComprobanteByCompoundPrimaryKey(codigo, ageLicencCodigo));
    }

    //Consultar datos de prueba de Factura
    /*@GetMapping("/demo")
    public FacturaReporteDTO obtenerFacturaDemo() {

        return facturaReportService.getFacturaDemo();
    }*/

    @GetMapping("/reporte")
    public ResponseEntity<byte[]> generateReporte(
            @RequestParam Long codigo,
            @RequestParam Integer ageLicencCodigo,
            @RequestParam(defaultValue = "pdf") String formato
    ) throws JRException, IOException, SQLException {

        byte[] archivo = reportService.generarReportePorId(codigo, ageLicencCodigo, formato);

        MediaType contentType = formato.equals("xlsx")
                ? MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                : MediaType.APPLICATION_PDF;

        String extension = formato.equals("xlsx") ? "xlsx" : "pdf";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report." + extension)
                .contentType(contentType)
                .body(archivo);
    }
}