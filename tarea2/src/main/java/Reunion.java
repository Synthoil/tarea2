import java.time.*;

abstract class Reunion {

    private LocalDate fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;

    private Lista<Empleado> asistentes;

    public Reunion(LocalDate fecha, Instant horaPrevista, Duration duracionPrevista) {
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
    }

    public void iniciar() {
        horaInicio = Instant.from(LocalDateTime.now());
    }

    public void finalizar() {
        horaFin = Instant.from(LocalDateTime.now());
    }

    public float calcularTiempoReal(){
        return Duration.between(horaInicio, horaFin).toMillis() / (60000f);
    }

    public Lista obtenerAsistencias() {
        return asistentes;
    }
}