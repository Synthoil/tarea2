import org.w3c.dom.ls.LSOutput;

import java.time.*;
import java.util.List;

import java.util.ArrayList;

class lista<T> {
    private ArrayList<T> elementos = new ArrayList<>();

    public void addElemento(T elemento) {
        elementos.add(elemento);
    }

    public T getElemento() {
        return elementos.isEmpty() ? null : elementos.remove(0);
    }
}


class ReunionVirtual extends Reunion {
    private String enlace;

    public String getEnlace() {
        return enlace;
    }
}

class ReunionPresencial extends Reunion {
    private String sala;

    public String getSala() {
        return sala;
    }
}

class Departamento {
    private String nombre_dept;
    private int empleados;

    public Departamento(String nombre_dept) {
        this.nombre_dept = nombre_dept;
    }

    public int obtenerCantidadEmpleados() {
        return empleados;
    }

    public void addEmpleado() {
        empleados++;
    }

}

class Empleado {
    private String id;
    private String apellidos;
    private String nombre;
    private String correo;

    public Empleado(String id, String apellidos, String nombre, String correo) {
        this.id = id;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.correo = correo;
    }
}

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

public class Main {
    public static void main(String[] args) {
        ReunionVirtual reunion = new ReunionVirtual();
        Duration diff = Duration.between(reunion.horaInicio, reunion.horaFin);
        String hms = String.format("%d:%02d:%02d",
                diff.toHours(),
                diff.toMinutesPart(),
                diff.toSecondsPart());
        System.out.println(hms);
        reunion.iniciar();
        System.out.println(reunion.horaInicio);
    }
}