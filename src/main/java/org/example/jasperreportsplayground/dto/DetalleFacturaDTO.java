package org.example.jasperreportsplayground.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DetalleFacturaDTO {

    private String producto;
    private Integer cantidad;
    private BigDecimal precio;
    private BigDecimal subtotal;


    public DetalleFacturaDTO(
            String producto,
            Integer cantidad,
            BigDecimal precio
    ) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = precio.multiply(
                BigDecimal.valueOf(cantidad)
        );
    }
}