package org.example.jasperreportsplayground.service;

import org.example.jasperreportsplayground.dto.ClienteFacturaDTO;
import org.example.jasperreportsplayground.dto.DetalleFacturaDTO;
import org.example.jasperreportsplayground.dto.ComprobanteReporteDTO;
import org.example.jasperreportsplayground.entity.AceCabeceraComprobanteElect;
import org.example.jasperreportsplayground.entity.AceCabeceraComprobanteElectId;
import org.example.jasperreportsplayground.entity.AceDetallesComprobantesElec;
import org.example.jasperreportsplayground.repository.AceCabeceraComprobanteElectRepository;
import org.example.jasperreportsplayground.repository.AceDetallesComprobantesElecRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprobanteReportService {

    private final AceCabeceraComprobanteElectRepository aceCabeceraComprobanteElectRepository;
    private final AceDetallesComprobantesElecRepository aceDetallesComprobantesElecRepository;

    public ComprobanteReportService(
            AceCabeceraComprobanteElectRepository aceCabeceraComprobanteElectRepository,
            AceDetallesComprobantesElecRepository aceDetallesComprobantesElecRepository
    ) {
        this.aceCabeceraComprobanteElectRepository = aceCabeceraComprobanteElectRepository;
        this.aceDetallesComprobantesElecRepository=aceDetallesComprobantesElecRepository;
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
        List<DetalleFacturaDTO> detalles = aceDetallesComprobantesElecRepository.findByIdAceCabCeCodigoAndIdAceCabCeAgeLicencCodigo(
                factura.getId().getCodigo(),
                factura.getId().getAgeLicencCodigo()
        ).stream().map(this::mapToDetalleDTO).toList();

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
                factura.getIdentificacion(),

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

    private DetalleFacturaDTO mapToDetalleDTO(
            AceDetallesComprobantesElec detalle
    ) {
        return new DetalleFacturaDTO(
                detalle.getId().getCodigo(),
                detalle.getProductoCodigoPrincipal(),
                detalle.getProductoCodigoAuxiliar(),
                detalle.getCantidad(),
                detalle.getDescripcion(),
                detalle.getPrecio(),
                //detalle.getUnidadMedida(),
                //detalle.getPrecioUnitario(),
                detalle.getValorDescuento()
                //detalle.getTotalSinImpuestos()
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