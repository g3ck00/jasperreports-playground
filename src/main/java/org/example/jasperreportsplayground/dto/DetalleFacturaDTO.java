package org.example.jasperreportsplayground.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DetalleFacturaDTO {

    private String productoCodigoPrincipal;
    private String productoCodigoAuxiliar;
    private Double cantidad;
    private String descripcion;
    private Double precio;

    public DetalleFacturaDTO(
            String productoCodigoPrincipal,
            String productoCodigoAuxiliar,
            Double cantidad,
            String descripcion,
            Double precio
    ) {
        this.productoCodigoPrincipal = productoCodigoPrincipal;
        this.productoCodigoAuxiliar = productoCodigoAuxiliar;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precio = precio;
    }
}