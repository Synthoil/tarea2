/**
 * Excepcion lanzada cuando la nota es vacia o nula.
 *
 */

public class NotaInvalidaException extends Exception {
    /**
     * Crea la excepcion con el mensaje.
     *
     * @param mensaje Mensaje que describe el error.
     */
    public NotaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
