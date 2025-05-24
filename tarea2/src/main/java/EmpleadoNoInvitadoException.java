public class EmpleadoNoInvitadoException extends Exception {
    public EmpleadoNoInvitadoException(String mensaje) {
        super("[Error Asistencia]" + mensaje);
    }
}
