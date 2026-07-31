package org.example.jasperreportsplayground.repository;

import org.example.jasperreportsplayground.dto.DetalleFacturaDTO;
import org.example.jasperreportsplayground.entity.AceCabeceraComprobanteElectId;
import org.example.jasperreportsplayground.entity.AceDetallesComprobantesElec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AceDetallesComprobantesElecRepository
        extends JpaRepository<AceDetallesComprobantesElec, AceCabeceraComprobanteElectId> {

    public interface ComprobanteRepository extends JpaRepository<DetalleFacturaDTO, Long> {
        Page<DetalleFacturaDTO> findAll(Pageable pageable);
    }

    // Buscar un comprobante por su clave primaria compuesta
    @Override
    Optional<AceDetallesComprobantesElec> findById(
            AceCabeceraComprobanteElectId id
    );

    List<AceDetallesComprobantesElec> findByIdAceCabCeCodigoAndIdAceCabCeAgeLicencCodigo(Long aceCabCeCodigo, Integer aceCabCeAgeLicencCodigo);

    //NativeQuery: JOIN de práctica
    @Query(value="select * from ace_cabeceras_comproban_elect cc join ace_detalles_comprobantes_elect dc on (cc.codigo = dc.ace_cab_ce_codigo and cc.age_licenc_condigo = dc.ace_cab_ce_age_licenc_codigo) where cc.codigo=910", nativeQuery=true) List<AceDetallesComprobantesElec> readAllDetallesFromOneCabecera();
}