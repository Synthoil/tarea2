import java.time.Instant;

public class Asistencia {
    private Empleado empleado;
    private Instant hora;

    public Asistencia(Empleado empleado, Instant hora){
        this.empleado = empleado;
        this.hora = hora;
    }
}
