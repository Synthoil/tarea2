import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

class ReunionVirtual extends Reunion {
    private String enlace;

    public ReunionVirtual(LocalDate fecha, Instant horaPrevista, Duration duracionPrevista, String tipoReunion) {
        super(fecha, horaPrevista, duracionPrevista, tipoReunion );
    }

    public String getEnlace() {
        return enlace;
    }
}