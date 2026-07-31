package org.example.jasperreportsplayground.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DetalleFacturaDTO {

    private Long codigo;
    private String productoCodigoPrincipal;
    private String productoCodigoAuxiliar;
    private Double cantidad;
    private String descripcion;
    private Double precio;
    private Double valorDescuento;

    public DetalleFacturaDTO(
            Long codigo,
            String productoCodigoPrincipal,
            String productoCodigoAuxiliar,
            Double cantidad,
            String descripcion,
            Double precio,
            Double valorDescuento
    ) {
        this.codigo=codigo;
        this.productoCodigoPrincipal = productoCodigoPrincipal;
        this.productoCodigoAuxiliar = productoCodigoAuxiliar;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.valorDescuento = valorDescuento;
    }
}