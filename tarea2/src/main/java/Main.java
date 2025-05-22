import org.w3c.dom.ls.LSOutput;

import java.time.*;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws DivisionPorCeroException, TiempoReunionInvalidoException, AsistenciaInvalidaException {
        Empleado e1 = new Empleado("123", "abc", "def", "abc@def");
        ReunionVirtual r = new ReunionVirtual(LocalDate.now(), Instant.now(), Duration.ofMinutes(90), TipoReunion.TECNICA, e1,  "reunion.com");
        Empleado e2 = new Empleado("345", "ghi", "jkl", "ghi@jkl");
        Empleado e3 = new Empleado("456", "mno", "pqr", "mno@pqr");
        Empleado e4 = new Empleado("567", "mno", "pqr", "mno@pqr");
        Departamento d = new Departamento("abecedario");
        d.addEmpleado(e3);
        d.addEmpleado(e4);
        r.invitarDepartamento(d);
        r.invitarEmpleado(e1);
        r.invitarEmpleado(e2);

        r.iniciar();
        r.asisteDepartamento(d.getEmpleados());
        Nota n = new Nota("Reunion de tecnica");
        r.agregarNota(n);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        r.asisteEmpleado(e1);

        r.finalizar();

        System.out.println(r.getEnlace());
        System.out.println(r.getTipoReunion());
        System.out.println(r.getNotas().getElemento().getContenido());
        System.out.println(r.obtenerTotalAsistencia());
        System.out.println(r.obtenerPorcentajeAsistencia());
        System.out.println(r.calcularTiempoReal());

        System.out.println("asistentes");
        for (Empleado asistente : r.obtenerAsistencias().copiaElementos()) {
            System.out.println(asistente.getId() + " " + asistente.getNombre() + " " + asistente.getApellidos());
        }

        System.out.println("asuentes");
        for (Empleado ausente : r.obtenerAusencias().copiaElementos()) {
            System.out.println(ausente.getId() + " " +ausente.getNombre() + " " + ausente.getApellidos());
        }

        System.out.println("atrasos");
        for (Empleado atrasado : r.obtenerAtrasos().copiaElementos()) {
            System.out.println(atrasado.getId() + " " + atrasado.getNombre() + " " + atrasado.getApellidos());
        }
    }
}