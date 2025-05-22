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
    private Lista<Empleado> ausentes = new Lista<>();

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
        horaInicio = Instant.now();
    }

    public void finalizar() {
        horaFin = Instant.now();
    }

    public String calcularTiempoReal() {
        long diferenciaMillis = Duration.between(horaInicio, horaFin).toMillis();
        Duration duration = Duration.ofMillis(diferenciaMillis);

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public Lista<Empleado> obtenerAsistencias() {
        return asistentes;
    }

    public void invitarEmpleado(Empleado empleado) {
        if (!(invitados.contieneElemento(empleado))) invitados.addElemento(empleado);
    }

    public void invitarDepartamento(Lista<Empleado> empleados) {
        Lista<Empleado> temp = new Lista<>();

        while (!empleados.estaVacia()) {
            Empleado emp = empleados.getElemento();
            temp.addElemento(emp);
            if (!invitados.contieneElemento(emp)) {
                invitados.addElemento(emp);
            }
        }
        while (!temp.estaVacia()) {
            empleados.addElemento(temp.getElemento());
        }
    }

    public void asisteEmpleado(Empleado empleado) {
        if (horaInicio == null) {
            System.out.println("No se puede registrar asistencia: la reunión no ha comenzado.");
            return;
        }
        if (asistentes.contieneElemento(empleado)) {
            System.out.println("Este empleado ya ha registrado asistencia.");
            return;
        }

        asistentes.addElemento(empleado);
        Instant temp = Instant.now();
        hora_llegada.addElemento(temp);
        if (Duration.between(horaInicio, temp).toSeconds() > 3) {
            atrasos.addElemento(empleado);
        }
    }

    public void asisteDepartamento(Lista<Empleado> empleados) {
        while (!empleados.estaVacia()) {
            Empleado emp = empleados.getElemento();
            asisteEmpleado(emp);
        }
    }


    public int obtenerTotalAsistencia() {
        return asistentes.obtenerCantidad();
    }

    public Lista<Empleado> obtenerAusencias() {
        for (Empleado invitado : invitados.copiaElementos()) {
            if (!asistentes.contieneElemento(invitado)) {
                ausentes.addElemento(invitado);
            }
        }
        return ausentes;
    }

    public Lista<Empleado> obtenerAtrasos() {
        return atrasos;
    }

    public float obtenerPorcentajeAsistencia() {
        if (invitados.obtenerCantidad() == 0) {
            return 0;
        }
        return (asistentes.obtenerCantidad() * 100f) / invitados.obtenerCantidad();
    }
}