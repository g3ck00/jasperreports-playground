package org.example.jasperreportsplayground.dto;

import lombok.Getter;

@Getter
public class ClienteFacturaDTO {

    private String nombre;
    private String identificacion;
    private String direccion;


    public ClienteFacturaDTO(
            String nombre,
            String identificacion,
            String direccion
    ) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.direccion = direccion;
    }
}