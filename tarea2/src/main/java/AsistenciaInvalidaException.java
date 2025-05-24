/**
 * Excepcion lanzada cuando asiste la misma persona dos veces.
 */

public class AsistenciaInvalidaException extends Exception {
    /**
     * Crea la excepcion con un mensaje.
     *
     * @param mensaje Mensaje que describe el error.
     */
    public AsistenciaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
