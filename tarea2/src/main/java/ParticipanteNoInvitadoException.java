/**
 * Excepcion lanzada cuando un participante no invitado ingresa a la reunion.
 */

public class ParticipanteNoInvitadoException extends Exception {
    /**
     * Crea la excepcion con el mensaje.
     *
     * @param mensaje Mensaje que describe el error.
     */
    public ParticipanteNoInvitadoException(String mensaje) {
        super("[Error Asistencia]" + mensaje);
    }
}
