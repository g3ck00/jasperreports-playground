package org.example.jasperreportsplayground.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
public class FacturaReporteDTO {

    // Cabecera factura
    private String numeroFactura;
    private LocalDate fechaEmision;
    private String claveAcceso;

    // Información emisor
    private String razonSocial;
    private String nombreComercial;
    private String ruc;
    private String direccionMatriz;


    // Cliente
    private ClienteFacturaDTO cliente;


    // Totales
    private BigDecimal subtotal;
    private BigDecimal descuento;
    private BigDecimal propina;
    private BigDecimal total;

    //Otros
    private String numeroAutorizacion;
    private String direccionEstablecimiento;
    private String obligadoContabilidad;
    private String fechaAutorizacion;
    private Short tipoAmbiente;
    private Short tipoEmision;

    // Detalle
    private List<DetalleFacturaDTO> detalles;

    public FacturaReporteDTO(
            String numeroFactura,
            LocalDate fechaEmision,
            String claveAcceso,
            String razonSocial,
            String nombreComercial,
            String ruc,
            String direccionMatriz,
            ClienteFacturaDTO cliente,
            BigDecimal subtotal,
            BigDecimal descuento,
            BigDecimal propina,
            BigDecimal total,
            List<DetalleFacturaDTO> detalles,

            String numeroAutorizacion,
            String direccionEstablecimiento,
            String obligadoContabilidad,
            String fechaAutorizacion,
            Short tipoAmbiente,
            Short tipoEmision
    ) {
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.claveAcceso = claveAcceso;
        this.razonSocial = razonSocial;
        this.nombreComercial = nombreComercial;
        this.ruc = ruc;
        this.direccionMatriz = direccionMatriz;
        this.cliente = cliente;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.propina = propina;
        this.total = total;
        this.detalles = detalles;

        this.numeroAutorizacion = numeroAutorizacion;
        this.direccionEstablecimiento = direccionEstablecimiento;
        this.obligadoContabilidad = obligadoContabilidad;
        this.fechaAutorizacion = fechaAutorizacion;
        this.tipoAmbiente = tipoAmbiente;
        this.tipoEmision = tipoEmision;
    }
}