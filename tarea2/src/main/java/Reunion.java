import java.time.*;
import java.util.*;

abstract class Reunion {

    private LocalDate fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;
    private String tipoReunion;

    private Lista<Empleado> invitados = new Lista<>();
    private Lista<Empleado> asistentes = new Lista<>();
    private Lista<Empleado> atrasos = new Lista<>();
    private Lista<Empleado> no_asistentes = new Lista<>();
    private Lista<Instant> hora_llegada = new Lista<>();
    private Lista<Nota> notas = new Lista<>();

    public Reunion(LocalDate fecha, Instant horaPrevista, Duration duracionPrevista, String tipoReunion) {
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
        this.tipoReunion = tipoReunion;
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

    public Lista<Empleado> obtenerAsistencias() {
        return asistentes;
    }

    public void invitarEmpleado(Empleado empleado) {
        if(!(invitados.contieneElemento(empleado))) invitados.addElemento(empleado);
    }

    public void invitarDepartamento(Lista<Empleado> empleados) {
        Lista<Empleado> temp = new Lista<>();

        while(!empleados.estaVacia()){
            Empleado emp = empleados.getElemento();
            temp.addElemento(emp);
            if(!invitados.contieneElemento(emp)){
                invitados.addElemento(emp);
            }
        }
        while(!temp.estaVacia()){
            empleados.addElemento(temp.getElemento());
        }
    }

    public void asiste(Empleado empleado) {
        asistentes.addElemento(empleado);
        hora_llegada.addElemento(Instant.now());
        if (Duration.between(hora_llegada.leerElemento(), horaInicio).toMinutes() > 15) {
            atrasos.addElemento(empleado);
        }
    }

    public int obtenerTotalAsistencia() {
        return asistentes.obtenerCantidad();
    }

    public Lista<Empleado> obtenerAusencias() {
        Lista<Empleado> ausentes = new Lista<>();
        return ausentes;
    }

    public float obtenerPorcentajeAsistencia() {
        if(invitados.obtenerCantidad() == 0){
            return 0;
        }
        return (asistentes.obtenerCantidad() *100f) /invitados.obtenerCantidad();
    }
}