package org.example.jasperreportsplayground.service;

import org.example.jasperreportsplayground.dto.ClienteFacturaDTO;
import org.example.jasperreportsplayground.dto.DetalleFacturaDTO;
import org.example.jasperreportsplayground.dto.FacturaReporteDTO;
import org.example.jasperreportsplayground.entity.AceCabeceraComprobanteElect;
import org.example.jasperreportsplayground.repository.AceCabeceraComprobanteElectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaReportService {

    private final AceCabeceraComprobanteElectRepository repository;


    public FacturaReportService(
            AceCabeceraComprobanteElectRepository repository
    ) {
        this.repository = repository;
    }


    public List<FacturaReporteDTO> readFacturas() {


        List<AceCabeceraComprobanteElect> facturas =
                repository.findAll();

        System.out.println("Registros BD: " + facturas.size());


        return facturas.stream()
                .map(this::mapToReporteDTO)
                .toList();
    }


    private FacturaReporteDTO mapToReporteDTO(
            AceCabeceraComprobanteElect factura
    ) {


        ClienteFacturaDTO cliente =
                new ClienteFacturaDTO(
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



        return new FacturaReporteDTO(

                String.valueOf(
                        factura.getSecuencial()
                ),

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
}