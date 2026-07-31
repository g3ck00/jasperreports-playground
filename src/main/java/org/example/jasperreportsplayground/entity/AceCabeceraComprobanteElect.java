package org.example.jasperreportsplayground.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ace_cabeceras_comproban_elect")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AceCabeceraComprobanteElect {

    @EmbeddedId
    private AceCabeceraComprobanteElectId id;

    @Column(name = "codigo_documento")
    private Short codigoDocumento;


    @Column(name = "codigo_documento_origen")
    private Short codigoDocumentoOrigen;


    @Column(name = "tipo_ambiente")
    private Short tipoAmbiente;


    @Column(name = "tipo_emision")
    private Short tipoEmision;


    @Column(name = "razon_social")
    private String razonSocial;


    @Column(name = "nombre_comercial")
    private String nombreComercial;


    @Column(name = "ruc")
    private String ruc;


    @Column(name = "establecimiento")
    private Short establecimiento;


    @Column(name = "punto_emision")
    private Short puntoEmision;


    @Column(name = "secuencial")
    private Integer secuencial;


    @Column(name = "direccion_matriz_emisor")
    private String direccionMatrizEmisor;


    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;


    @Column(name = "direccion_establecimiento")
    private String direccionEstablecimiento;


    @Column(name = "contribuyente_especial")
    private String contribuyenteEspecial;


    @Column(name = "obligado_contabilidad")
    private String obligadoContabilidad;


    @Column(name = "clave_acceso")
    private String claveAcceso;


    @Column(name = "correo_electronico1")
    private String correoElectronico1;


    @Column(name = "identificacion")
    private String identificacion;


    @Column(name = "direccion")
    private String direccion;


    @Column(name = "total_sin_impuestos",
            precision = 18,
            scale = 6)
    private BigDecimal totalSinImpuestos;


    @Column(name = "valor",
            precision = 18,
            scale = 6)
    private BigDecimal valor;


    @Column(name = "fe_total_descuento",
            precision = 18,
            scale = 6)
    private BigDecimal totalDescuento;


    @Column(name = "fe_propina",
            precision = 18,
            scale = 6)
    private BigDecimal propina;


    @Column(name = "fe_importe_total",
            precision = 18,
            scale = 6)
    private BigDecimal importeTotal;


    @Column(name = "comprobante_estado")
    private String comprobanteEstado;


    @Column(name = "estado")
    private String estado;


    @Column(name = "fecha_estado")
    private LocalDate fechaEstado;


    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;


    @Column(name = "usuario_ingreso")
    private Long usuarioIngreso;


    @Column(name = "ubicacion_ingreso")
    private String ubicacionIngreso;


    @Column(name = "age_tip_id_codigo")
    private Long ageTipIdCodigo;

    @Column(name="numero_autorizacion")
    private String numeroAutorizacion;

    @Column(name="fecha_autorizacion")
    private String fechaAutorizacion;
}