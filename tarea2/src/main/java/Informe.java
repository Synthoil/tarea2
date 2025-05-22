import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.*;

public class Informe {
    public void exportarResumen(Reunion reunion) throws IOException {
        try (BufferedWriter informe = new BufferedWriter(new FileWriter("informe.txt"))) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String horaPrevista = LocalDateTime.ofInstant(reunion.getHoraPrevista(), ZoneId.systemDefault()).format(formatter);
            String horaInicio = LocalDateTime.ofInstant(reunion.getHoraInicio(), ZoneId.systemDefault()).format(formatter);
            String horaFin = LocalDateTime.ofInstant(reunion.getHoraFin(), ZoneId.systemDefault()).format(formatter);

            informe.write("Fecha: " + reunion.getFecha() + " Hora: " + horaPrevista + "\n");
            informe.write("Hora de inicio: " + horaInicio + " Hora de fin: " + horaFin + " Duracion: " + reunion.calcularTiempoReal() + "\n");
            informe.write("tipo de reunion: " + reunion.getTipoReunion() + "\n");
            if (reunion instanceof ReunionVirtual) {
                informe.write("Enlace de reunion: " + ((ReunionVirtual) reunion).getEnlace() + "\n");
            }
            if (reunion instanceof ReunionPresencial) {
                informe.write("Sala de reunion: " + ((ReunionPresencial) reunion).getSala() + "\n");
            }

            informe.write("Organizador: " + reunion.obtenerAsistencias().elementoInicial().getNombre() + " " + reunion.obtenerAsistencias().elementoInicial().getApellidos() + "\n");
            informe.write("---Participantes---\n");
            for (Empleado asistente : reunion.obtenerAsistencias().copiaElementos()) {
                informe.write(asistente.getNombre() + " " + asistente.getApellidos() + "\n");
            }
            informe.write("---Atrasados---\n");
            for (Empleado atrasado : reunion.obtenerAtrasos().copiaElementos()) {
                informe.write(atrasado.getNombre() + " " + atrasado.getApellidos() + "\n");
            }
            informe.write("---Ausente---\n");
            for (Empleado ausente : reunion.obtenerAusencias().copiaElementos()) {
                informe.write(ausente.getNombre() + " " + ausente.getApellidos() + "\n");
            }

        }
    }
}
