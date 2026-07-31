package org.example.jasperreportsplayground.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AceDetallesComprobantesElecId implements Serializable {
    @Column (name="codigo")
    private Long codigo;

    @Column(name="ace_cab_ce_codigo")
    private Long aceCabCeCodigo;

    @Column(name="ace_cab_ce_age_licenc_codigo")
    private Integer aceCabCeAgeLicencCodigo;
}
