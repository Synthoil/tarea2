import java.time.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

/**
 * Clase especifica de reunion que tiene un enlace.
 */
class ReunionVirtual extends Reunion {
    private String enlace;

    /**
     * @param fecha Fecha de la reunion.
     * @param horaPrevista Hora prevista a realizar la reunion.
     * @param duracionPrevista Duracion esperada para la reunion.
     * @param tipoReunion Tipo o proposito para la reunion.
     * @param organizador Empleado que organiza la reunion.
     * @param enlace String que contiene el enlace para ingresar a la reunion.
     */
    public ReunionVirtual(Date fecha, Instant horaPrevista, Duration duracionPrevista, TipoReunion tipoReunion, Empleado organizador, String enlace) {
        super(fecha, horaPrevista, duracionPrevista, tipoReunion, organizador);
        this.enlace = enlace;
    }

    /**
     * Devuelve el enlace para ingresar a la reunion.
     *
     * @return Enlace web
     */
    public String getEnlace() {
        return enlace;
    }
}