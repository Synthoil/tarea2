import java.time.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.*;

/**
 * Clase que representa la reunion, extendida para la reunion virtual y presencial.
 * Controla las horas previstas y reales en base al tiempo actual.
 * Tiene listas para controlar los invitados, que compara a las otras listas para llenar los atrasos y ausentes.
 */

abstract class Reunion {

    private Date fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;
    private TipoReunion tipoReunion;

    private Lista<Participante> invitados = new Lista<>();
    private Lista<Participante> asistentes = new Lista<>();
    private Lista<Participante> atrasos = new Lista<>();
    private Lista<Instant> hora_llegada = new Lista<>();
    private Lista<Nota> notas = new Lista<>();
    private Lista<Participante> ausentes = new Lista<>();

    /**
     * Constructor que guarda los datos previstos para la reunion
     *
     * @param fecha            Fecha prevista para la reunion.
     * @param horaPrevista     Hora prevista para la reunion.
     * @param duracionPrevista Duracion prevista para la reunion.
     * @param tipoReunion      Enumeracion que dicta el tipo de reunion.
     * @param organizador      Emplado que organiza la reunion, forzado a ser el primer invitado.
     */
    public Reunion(Date fecha, Instant horaPrevista, Duration duracionPrevista, TipoReunion tipoReunion, Empleado organizador) {
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
    public void agregarNota(Nota nota) throws NotaInvalidaException {
        if (nota == null || nota.getContenido() == null || nota.getContenido().trim().isEmpty()) {
            throw new NotaInvalidaException("No se puede agregar: La nota está vacia o es nula");
        }
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
    public void finalizar() throws TiempoReunionInvalidoException {
        Instant ahora = Instant.now();
        if (horaInicio != null && ahora.isBefore(horaInicio)) {
            throw new TiempoReunionInvalidoException("La reunion de " + tipoReunion + "tiene tiempos invalidos");
        }
        horaFin = ahora;
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
    public Lista<Participante> obtenerAsistencias() {
        return asistentes;
    }

    /**
     * Recibe una persona y revisa si ya fue invitado previamente, si no estaba en la lista
     * de invitados se agrega.
     *
     * @param participante Persona a invitar.
     */
    public void invitarParticipante(Participante participante) {
        if (!invitados.contieneElemento(participante)) {
            invitados.addElemento(participante);
        }
    }

    /**
     * Recibe un departamento al cual le extrae la lista de empleados, los cuales invita uno a uno
     * y comprueba si ya estaban invitados para no invitarlos 2 veces
     *
     * @param departamento lista de empleados.
     * @throws DepartamentoVacioException Cuando el departamento esta vacio.
     */
    public void invitarDepartamento(Departamento departamento) throws DepartamentoVacioException {
        if (departamento.obtenerCantidadEmpleados() == 0) {
            throw new DepartamentoVacioException("No se puede invitar: El departamento no tiene empleados");
        }

        Lista<Empleado> empleados = departamento.getEmpleados();
        List<Empleado> copia = empleados.copiaElementos();

        for (Empleado emp : copia) {
            if (!invitados.contieneElemento(emp)) {
                invitados.addElemento(emp);
            }
        }
    }

    /**
     * Recibe un participante, si está a la hora es agregado a los asistentes, pero si llega tarde tambien
     * es agregado a la lista de los atrasados (ventana de 15 minutos).
     *
     * @param p Participante (Empleado o Invitado).
     * @throws AsistenciaInvalidaException     Cuando la una persona asiste dos veces,
     * @throws ParticipanteNoInvitadoException Cuando una persona no invitada asiste.
     */
    public void asisteParticipante(Participante p) throws AsistenciaInvalidaException, ParticipanteNoInvitadoException {
        if (!invitados.contieneElemento(p)) {
            throw new ParticipanteNoInvitadoException(
                    "El participante " + p.getNombreCompleto() + " no esta invitado esta reunion"
            );
        }
        if (asistentes.contieneElemento(p)) {
            throw new AsistenciaInvalidaException("Ya esta registrado");
        }
        Instant ahora = Instant.now();
        if (horaInicio != null && ahora.isBefore(horaInicio)) {
            throw new AsistenciaInvalidaException("Llego antes de iniciar la reunion");
        }

        asistentes.addElemento(p);
        hora_llegada.addElemento(ahora);
        if (Duration.between(horaInicio, ahora).toSeconds() > 3) {
            atrasos.addElemento(p);
        }
    }


    /**
     * Toma toda la lista de empleados de un departamento y llama al registro de asistencia con cada uno.
     *
     * @param departamento Departamento que provee una lista.
     * @throws DepartamentoVacioException Cuando la una persona asiste dos veces,
     * @throws ParticipanteNoInvitadoException Cuando una persona no invitada asiste.
     */
    public void asisteDepartamento(Departamento departamento) throws DepartamentoVacioException, ParticipanteNoInvitadoException {
        Lista<Empleado> empleados = departamento.getEmpleados();
        Lista<Empleado> temp = new Lista<>();
        while (!empleados.estaVacia()) {
            Empleado emp = empleados.getElemento();
            temp.addElemento(emp);
            try {
                asisteParticipante(emp);
            } catch (AsistenciaInvalidaException e) {
                System.err.println("Error en asistencia: " + e.getMessage());
            }
        }
        while (!temp.estaVacia()) {
            empleados.addElemento(temp.getElemento());
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
    public Lista<Participante> obtenerAusencias() {
        ausentes = new Lista<>();
        for (Participante invitado : invitados.copiaElementos()) {
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
    public Lista<Participante> obtenerAtrasos() {
        return atrasos;
    }

    /**
     * Calcula el porcentaje de personas que asistieron.
     *
     * @return asistencia%
     * @throws DivisionPorCeroException Cuando el departamento esta vacio.
     */
    public float obtenerPorcentajeAsistencia() throws DivisionPorCeroException {
        int totalInvitados = invitados.obtenerCantidad();
        if (totalInvitados == 0) {
            throw new DivisionPorCeroException("No se puede calcular porcentaje: La lista de invitados esta vacía");
        }
        return (asistentes.obtenerCantidad() * 100f) / totalInvitados;
    }

    /**
     * Retorna el tipo de reunion que se realizo.
     *
     * @return Tipo de reunion.
     */

    public TipoReunion getTipoReunion() {
        return tipoReunion;
    }

    /**
     * Devuelve el tiempo de inicio previsto entregado en el constructor.
     *
     * @return fecha de inicio previsto.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Devuelve la hora prevista para el inicio de la reunion.
     *
     * @return hora prevista.
     */
    public Instant getHoraPrevista() {
        return horaPrevista;
    }

    /**
     * Devuelve la hora de inicio real de la reunion
     *
     * @return hora de inicio.
     */
    public Instant getHoraInicio() {
        return horaInicio;
    }

    /**
     * Devuelve la hora de fin real para la reunion.
     *
     * @return de finalizacion.
     */
    public Instant getHoraFin() {
        return horaFin;
    }

    /**
     * Entrega la descripcion de la clase.
     *
     * @return descripcion.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("hh:mm:ss");

        String horaPrevistaStr = horaPrevista != null ? LocalTime.ofInstant(horaPrevista, ZoneId.systemDefault()).format(formatoHora) : "Sin hora prevista";

        String duracionPrevistaStr = duracionPrevista != null ? duracionPrevista.toHours() + " horas " + (duracionPrevista.toMinutes() % 60) + "minutos" : "No definida";

        String horaInicioStr = horaInicio != null ? LocalTime.ofInstant(horaInicio, ZoneId.systemDefault()).format(formatoHora) : "No iniciada";

        String horaFinStr = horaFin != null ? LocalTime.ofInstant(horaFin, ZoneId.systemDefault()).format(formatoHora) : "No ha finalizado";

        return "Tipo de reunion: " + tipoReunion +
                "\nFecha: " + fecha +
                "\nHora prevista: " + horaPrevistaStr +
                "\nDuracion prevista: " + duracionPrevistaStr +
                "\nInicio de la reunion: " + horaInicioStr +
                "\nFin de la reunion: " + horaFinStr +
                "\nInvitados: " + invitados.obtenerCantidad() +
                "\nAsistentes: " + asistentes.obtenerCantidad() +
                "\nInasistentes: " + ausentes.obtenerCantidad() +
                "\nAtrasos: " + atrasos.obtenerCantidad();
    }
}