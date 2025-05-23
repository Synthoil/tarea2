import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.time.*;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws DivisionPorCeroException, TiempoReunionInvalidoException, AsistenciaInvalidaException, DepartamentoVacioException, NotaInvalidaException, IOException {
        Empleado e1 = new Empleado("123", "abc", "def", "abc@def");
        ReunionVirtual r = new ReunionVirtual(LocalDate.now(), Instant.now(), Duration.ofMinutes(90), TipoReunion.TECNICA, e1, "reunion.com");
        Empleado e2 = new Empleado("345", "ghi", "jkl", "ghi@jkl");
        Empleado e3 = new Empleado("456", "mno", "pqr", "mno@pqr");
        Empleado e4 = new Empleado("567", "mno", "pqr", "mno@pqr");
        InvitadoExterno ext1 = new InvitadoExterno("Inasistente Externo","inasistente@mail");
        InvitadoExterno ext2 = new InvitadoExterno("Impuntual Externo", "Atrasado@mail");
        Departamento d = new Departamento("abecedario");
        d.addEmpleado(e3);
        d.addEmpleado(e4);
        r.invitarDepartamento(d);
        r.invitarEmpleado(e1);
        r.invitarEmpleado(e2);
        r.invitarExterno(ext1);

        r.iniciar();
        r.asisteDepartamento(d.getEmpleados());
        Nota n = new Nota("Reunion de tecnica");
        r.agregarNota(n);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        r.asisteEmpleado(e1);
        r.asisteParticipante(ext2);

        r.finalizar();

        System.out.println(r.getEnlace());
        System.out.println(r.getTipoReunion());
        System.out.println(r.getNotas().getElemento().getContenido());
        System.out.println(r.obtenerTotalAsistencia());
        System.out.println(r.obtenerPorcentajeAsistencia());
        System.out.println(r.calcularTiempoReal());



        System.out.println("asistentes");
        for (Participante asistente : r.obtenerAsistencias().copiaElementos()) {
            if (asistente instanceof Empleado) {
                Empleado emp = (Empleado) asistente;
                System.out.println(emp.getId() + " - " + emp.getNombreCompleto());
            } else {
                System.out.println(asistente.getNombreCompleto());
            }
        }

        System.out.println("ausentes");
        for (Participante ausente : r.obtenerAusencias().copiaElementos()) {
            if (ausente instanceof Empleado) {
                Empleado emp = (Empleado) ausente;
                System.out.println(emp.getId() + " - " + emp.getNombreCompleto());
            } else {
                System.out.println(ausente.getNombreCompleto());
            }
        }

        System.out.println("atrasos");
        for (Participante atrasado : r.obtenerAtrasos().copiaElementos()) {
            if (atrasado instanceof Empleado) {
                Empleado emp = (Empleado) atrasado;
                System.out.println(emp.getId() + " - " + emp.getNombreCompleto());
            } else {
                System.out.println(atrasado.getNombreCompleto());
            }
        }

        Informe in = new Informe();
        in.exportarResumen(r);
    }
}