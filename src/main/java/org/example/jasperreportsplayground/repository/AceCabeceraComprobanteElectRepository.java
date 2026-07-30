package org.example.jasperreportsplayground.repository;

import org.example.jasperreportsplayground.entity.AceCabeceraComprobanteElect;
import org.example.jasperreportsplayground.entity.AceCabeceraComprobanteElectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AceCabeceraComprobanteElectRepository
        extends JpaRepository<AceCabeceraComprobanteElect, AceCabeceraComprobanteElectId> {


    /*
     * Buscar un comprobante por su clave primaria compuesta
     */
    @Override
    Optional<AceCabeceraComprobanteElect> findById(
            AceCabeceraComprobanteElectId id
    );


    /*
     * Buscar comprobantes por estado
     * Ejemplo:
     * estado = "A"
     */
    List<AceCabeceraComprobanteElect> findByEstado(
            String estado
    );


    /*
     * Buscar comprobantes aprobados
     */
    List<AceCabeceraComprobanteElect> findByComprobanteEstado(
            String comprobanteEstado
    );


    /*
     * Buscar por identificación del cliente
     */
    List<AceCabeceraComprobanteElect> findByIdentificacion(
            String identificacion
    );


    /*
     * Buscar rango de fechas
     */
    List<AceCabeceraComprobanteElect> findByFechaEmisionBetween(
            LocalDate fechaInicio,
            LocalDate fechaFin
    );


    /*
     * Buscar comprobante por secuencial
     */
    Optional<AceCabeceraComprobanteElect> findBySecuencial(
            Integer secuencial
    );


    /*
     * Buscar por RUC del emisor
     */
    List<AceCabeceraComprobanteElect> findByRuc(
            String ruc
    );


    /*
     * Consulta típica para reportes:
     * comprobantes activos y aprobados
     */
    List<AceCabeceraComprobanteElect> findByEstadoAndComprobanteEstado(
            String estado,
            String comprobanteEstado
    );

}