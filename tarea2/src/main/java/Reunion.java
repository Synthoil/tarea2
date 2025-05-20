import java.time.*;
import java.util.*;

abstract class Reunion {

    private LocalDate fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;
    private String tipoReunion;

    private Lista<Empleado> invitados;
    private Lista<Empleado> asistentes;
    private Lista<Empleado> atrasos;
    private Lista<Empleado> no_asistentes;
    private Lista<Instant> hora_llegada;
    private Lista<Nota> notas;

    public Reunion(LocalDate fecha, Instant horaPrevista, Duration duracionPrevista, String tipoReunion) {
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
        notas = new Lista<>();
        invitados = new Lista<>();
        asistentes = new Lista<>();
        hora_llegada = new Lista<>();
        atrasos = new Lista<>();
        no_asistentes = new Lista<>();
        this.tipoReunion = String.valueOf(TipoReunion.valueOf(tipoReunion));
    }

    public void agregarNota(Nota nota) {
        notas.addElemento(nota);
    }

    public Lista<Nota> getNotas() {
        return notas;
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

    public void invitarEmpleado(Empleado empleado) {
        if(!(invitados.contieneElemento(empleado))) invitados.addElemento(empleado);
    }

    public void invitarDepartamento(Lista<Empleado> empleados) {
        while(empleados.getElemento() != null) {
            if(!(invitados.contieneElemento(empleados.getElemento()))) invitados.addElemento(empleados.getElemento());
        }
    }

    public void asiste(Empleado empleado) {
        asistentes.addElemento(empleado);
        hora_llegada.addElemento(Instant.now());
        if (Duration.between(hora_llegada.leerElemento(), horaInicio).toMinutes() > 15) {
            atrasos.addElemento(empleado);
        }
    }

    public void obtenerTotalAsistencia() {

    }

    public void ausencias() {

    }
    public void obtenerPorcentajeAsistencia() {

    }

}