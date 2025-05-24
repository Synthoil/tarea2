import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

/**
 * Clase especifica de reunion que tiene una sala donde se realiza la reunion.
 * */

class ReunionPresencial extends Reunion {
    private String sala;

    /**
     * @param fecha Fecha de la reunion.
     * @param horaPrevista Hora prevista a realizar la reunion.
     * @param duracionPrevista Duracion esperada para la reunion.
     * @param tipoReunion Tipo o proposito para la reunion.
     * @param organizador Empleado que organiza la reunion.
     * @param sala Ubicacion de la reunion.
     */
    public ReunionPresencial(Date fecha, Instant horaPrevista, Duration duracionPrevista, TipoReunion tipoReunion, Empleado organizador, String sala) {
        super(fecha, horaPrevista, duracionPrevista, tipoReunion, organizador);
    }

    /**
     * Devuelve la sala donde se realiza la reunion
     *
     * @return String de la sala
     */
    public String getSala() {
        return sala;
    }
}