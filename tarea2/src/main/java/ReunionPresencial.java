import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

class ReunionPresencial extends Reunion {
    private String sala;

    public ReunionPresencial(LocalDate fecha, Instant horaPrevista, Duration duracionPrevista) {
        super(fecha, horaPrevista, duracionPrevista);
    }

    public String getSala() {
        return sala;
    }
}