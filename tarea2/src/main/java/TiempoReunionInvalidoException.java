/**
 * Excepcion lanzada cuando se usan tiempos invalidos, por ejemplo que finalize antes de empezar
 */

public class TiempoReunionInvalidoException extends Exception {
    /**
     * Crea la excepcion con el mensaje.
     *
     * @param mensaje Mensaje que describe el error.
     */
    public TiempoReunionInvalidoException(String mensaje) {
        super(mensaje);
    }
}
