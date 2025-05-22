import java.time.*;
import java.util.*;

/**
 * Clase que representa la reunion, extendida para la reunion virtual y presencial.
 * Controla las horas previstas y reales en base al tiempo actual.
 * Tiene listas para controlar los invitados, que compara a las otras listas para llenar los atrasos y ausentes.
 */

abstract class Reunion {

    private LocalDate fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;
    private TipoReunion tipoReunion;

    private Lista<Empleado> invitados = new Lista<>();
    private Lista<Empleado> asistentes = new Lista<>();
    private Lista<Empleado> atrasos = new Lista<>();
    private Lista<Instant> hora_llegada = new Lista<>();
    private Lista<Nota> notas = new Lista<>();
    private Lista<Empleado> ausentes = new Lista<>();

    /**
     * Constructor que guarda los datos previstos para la reunion
     *
     * @param fecha            Fecha prevista para la reunion.
     * @param horaPrevista     Hora prevista para la reunion.
     * @param duracionPrevista Duracion prevista para la reunion.
     * @param tipoReunion      Enumeracion que dicta el tipo de reunion.
     * @param organizador      Emplado que organiza la reunion, forzado a ser el primer invitado.
     */
    public Reunion(LocalDate fecha, Instant horaPrevista, Duration duracionPrevista, TipoReunion tipoReunion, Empleado organizador) {
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
        this.tipoReunion = tipoReunion;
        invitados.addElemento(organizador);
    }

    /**
     * Agrega una nota a una lista de notas.
     *
     * @param nota Nota con un texto guardado.
     */
    public void agregarNota(Nota nota) {
        notas.addElemento(nota);
    }

    /**
     * Retorna la lista de notas.
     *
     * @return la lista completa de notas.
     */
    public Lista<Nota> getNotas() {
        return notas;
    }

    /**
     * Toma el instante de reloj actual y lo guarda como el inicio de la reunion.
     */
    public void iniciar() {
        horaInicio = Instant.now();
    }

    /**
     * Toma el instante actual para guardar el final de la reunion.
     */
    public void finalizar() {
        horaFin = Instant.now();
    }

    /**
     * Toma las horas de inicio y fin para retornar el tiempo en formato hh:mm:ss.
     *
     * @return String del tiempo que tomó la reunion.
     */
    public String calcularTiempoReal() {
        long diferenciaMillis = Duration.between(horaInicio, horaFin).toMillis();
        Duration duration = Duration.ofMillis(diferenciaMillis);

        long hora = duration.toHours();
        long minuto = duration.toMinutes() % 60;
        long segundo = duration.getSeconds() % 60;

        return String.format("%02d:%02d:%02d", hora, minuto, segundo);
    }

    /**
     * Retorna la lista de asistentes a la reunion.
     *
     * @return asistentes
     */
    public Lista<Empleado> obtenerAsistencias() {
        return asistentes;
    }

    /**
     * Recibe un empleado y revisa si ya fue invitado previamente, si no estaba en la lista
     * de invitados se agrega.
     *
     * @param empleado Empleado a invitar.
     */
    public void invitarEmpleado(Empleado empleado) {
        if (!(invitados.contieneElemento(empleado))) invitados.addElemento(empleado);
    }

    /**
     * Recibe un departamento al cual le extrae la lista de empleados, los cuales invita uno a uno
     * y comprueba si ya estaban invitados para no invitarlos 2 veces
     *
     * @param departamento lista de empleados.
     */
    public void invitarDepartamento(Departamento departamento) {
        Lista<Empleado> empleados = departamento.getEmpleados();

        for (Empleado emp : empleados.copiaElementos()) {
            if (!invitados.contieneElemento(emp)) {
                invitados.addElemento(emp);
            }
        }
    }

    /**
     * Recibe un empleado y considera el momento acual para registrar su hora de llegada.
     * lanza excepcion si la reunion no ha comenzado o si la misma persona registra asistencia 2 veces.
     *
     * @param empleado Empleado que asiste a la reunion.
     */
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

    /**
     * Toma toda la lista de empleados de un departamento y llama al registro de asistencia con cada uno.
     *
     * @param empleados lista de empleados del departamento
     */
    public void asisteDepartamento(Lista<Empleado> empleados) {
        while (!empleados.estaVacia()) {
            Empleado emp = empleados.getElemento();
            asisteEmpleado(emp);
        }
    }

    /**
     * Calcula la cantidad de empleados en la lista de asistentes (incluye atrasos).
     *
     * @return int de la cantidad de asistentes.
     */
    public int obtenerTotalAsistencia() {
        return asistentes.obtenerCantidad();
    }

    /**
     * Compara la listas de personas invitadas con las que asistieron, agregando a ausentes los
     * empleados invitados pero que no aparecen en la lista de asistentes.
     *
     * @return lista de ausentes
     */
    public Lista<Empleado> obtenerAusencias() {
        for (Empleado invitado : invitados.copiaElementos()) {
            if (!asistentes.contieneElemento(invitado)) {
                ausentes.addElemento(invitado);
            }
        }
        return ausentes;
    }

    /**
     * Retorna la lista de las personas atrasadas.
     *
     * @return Empleados atrasados.
     */
    public Lista<Empleado> obtenerAtrasos() {
        return atrasos;
    }

    /**
     * Calcula el porcentaje de personas que asistieron.
     *
     * @return asistencia%
     */
    public float obtenerPorcentajeAsistencia() {
        if (invitados.obtenerCantidad() == 0) {
            return 0;
        }
        return (asistentes.obtenerCantidad() * 100f) / invitados.obtenerCantidad();
    }

    /**
     * Retorna el tipo de reunion que se realizo.
     *
     * @return Tipo de reunion.
     */

    public TipoReunion getTipoReunion() {
        return tipoReunion;
    }
}