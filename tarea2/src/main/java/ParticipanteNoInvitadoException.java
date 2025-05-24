public class ParticipanteNoInvitadoException extends Exception {
    public ParticipanteNoInvitadoException(String mensaje) {
        super("[Error Asistencia]" + mensaje);
    }
}
