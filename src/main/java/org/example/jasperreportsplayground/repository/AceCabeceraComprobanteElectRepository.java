package org.example.jasperreportsplayground.repository;

import org.example.jasperreportsplayground.dto.FacturaReporteDTO;
import org.example.jasperreportsplayground.entity.AceCabeceraComprobanteElect;
import org.example.jasperreportsplayground.entity.AceCabeceraComprobanteElectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AceCabeceraComprobanteElectRepository
        extends JpaRepository<AceCabeceraComprobanteElect, AceCabeceraComprobanteElectId> {

    public interface ComprobanteRepository extends JpaRepository<FacturaReporteDTO, Long> {
        Page<FacturaReporteDTO> findAll(Pageable pageable);
    }

    // Buscar un comprobante por su clave primaria compuesta
    @Override
    Optional<AceCabeceraComprobanteElect> findById(
            AceCabeceraComprobanteElectId id
    );
}