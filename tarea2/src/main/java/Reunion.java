import java.time.*;
abstract class Reunion {

    public LocalDate fecha = LocalDate.of(2025, 1, 1);
    public Instant horaPrevista = Instant.parse("2025-01-01T19:00:00.00Z");
    public Duration duracionPrevista = Duration.ofHours(3);
    public Instant horaInicio = Instant.parse("2025-01-01T19:00:00.00Z");
    public Instant horaFin = Instant.parse("2025-01-01T22:00:00.00Z");

    lista<Empleado> asistentes;


    public void iniciar() {
        horaInicio = Instant.from(LocalDateTime.now());
    }

    public lista obtenerAsistencias() {
        return asistentes;
    }
}