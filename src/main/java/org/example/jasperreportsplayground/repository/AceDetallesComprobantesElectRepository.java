package org.example.jasperreportsplayground.repository;

import org.example.jasperreportsplayground.dto.DetalleFacturaDTO;
import org.example.jasperreportsplayground.entity.AceCabeceraComprobanteElectId;
import org.example.jasperreportsplayground.entity.AceDetallesComprobantesElec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AceDetallesComprobantesElectRepository
        extends JpaRepository<AceDetallesComprobantesElec, AceCabeceraComprobanteElectId> {

    public interface ComprobanteRepository extends JpaRepository<DetalleFacturaDTO, Long> {
        Page<DetalleFacturaDTO> findAll(Pageable pageable);
    }

    // Buscar un comprobante por su clave primaria compuesta
    @Override
    Optional<AceDetallesComprobantesElec> findById(
            AceCabeceraComprobanteElectId id
    );
}