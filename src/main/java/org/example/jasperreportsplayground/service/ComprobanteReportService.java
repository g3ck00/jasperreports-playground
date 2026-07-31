package org.example.jasperreportsplayground.service;

import org.example.jasperreportsplayground.dto.ClienteFacturaDTO;
import org.example.jasperreportsplayground.dto.DetalleFacturaDTO;
import org.example.jasperreportsplayground.dto.ComprobanteReporteDTO;
import org.example.jasperreportsplayground.entity.AceCabeceraComprobanteElect;
import org.example.jasperreportsplayground.entity.AceCabeceraComprobanteElectId;
import org.example.jasperreportsplayground.repository.AceCabeceraComprobanteElectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprobanteReportService {

    private final AceCabeceraComprobanteElectRepository aceCabeceraComprobanteElectRepository;

    public ComprobanteReportService(
            AceCabeceraComprobanteElectRepository aceCabeceraComprobanteElectRepository
    ) {
        this.aceCabeceraComprobanteElectRepository = aceCabeceraComprobanteElectRepository;
    }

    // Read All Comprobantes
    public Page<AceCabeceraComprobanteElect> readAllComprobantes(Pageable pageable){
        return aceCabeceraComprobanteElectRepository.findAll(pageable);
    }

    // Read Comprobante by Compound PK (Endpoint with Params)
    public AceCabeceraComprobanteElect readComprobanteByCompoundPrimaryKey(Long codigo, Integer ageLicencCodigo) {
        AceCabeceraComprobanteElectId id = new AceCabeceraComprobanteElectId(codigo, ageLicencCodigo);

        return aceCabeceraComprobanteElectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comprobante no encontrado."));
    }

    public List<ComprobanteReporteDTO> readFacturas() {
        List<AceCabeceraComprobanteElect> facturas =
                aceCabeceraComprobanteElectRepository.findAll();

        //System.out.println("Registros BD: " + facturas.size());

        return facturas.stream().map(this::mapToReporteDTO).toList();
    }


    private ComprobanteReporteDTO mapToReporteDTO(
            AceCabeceraComprobanteElect factura
    ) {
        ClienteFacturaDTO cliente = new ClienteFacturaDTO(
                        factura.getRazonSocial(),
                        factura.getIdentificacion(),
                        factura.getDireccion()
        );

        /*
         * Temporalmente vacío.
         *
         * Cuando exista la tabla detalle:
         *
         * List<DetalleFacturaDTO> detalles =
         *       detalleRepository
         *       .findByFactura(...)
         *
         */
        List<DetalleFacturaDTO> detalles = List.of();

        return new ComprobanteReporteDTO(
                String.valueOf(factura.getSecuencial()),

                factura.getFechaEmision(),
                factura.getClaveAcceso(),
                factura.getRazonSocial(),
                factura.getNombreComercial(),
                factura.getRuc(),
                factura.getDireccionMatrizEmisor(),

                cliente,

                factura.getTotalSinImpuestos(),
                factura.getTotalDescuento(),
                factura.getPropina(),
                factura.getImporteTotal(),

                detalles,

                //Bryant
                factura.getNumeroAutorizacion(),
                factura.getDireccionEstablecimiento(),
                factura.getObligadoContabilidad(),
                factura.getFechaAutorizacion(),
                factura.getTipoAmbiente(),
                factura.getTipoEmision()
        );
    }

    public ComprobanteReporteDTO readFactura(
            Long codigo,
            Integer ageLicencCodigo
    ) {

        AceCabeceraComprobanteElectId id =
                new AceCabeceraComprobanteElectId(
                        codigo,
                        ageLicencCodigo
                );

        AceCabeceraComprobanteElect factura =
                aceCabeceraComprobanteElectRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Comprobante no encontrado."));

        return mapToReporteDTO(factura);
    }
}