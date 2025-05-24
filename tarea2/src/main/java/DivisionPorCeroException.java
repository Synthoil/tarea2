/**
 * En algunos casos, el porcentaje de asistentes puede ser (numero)/0 * 100, se considera un error
 */

public class DivisionPorCeroException extends Exception {
    /**
     * Crea la excepcion con el mensaje.
     *
     * @param mensaje Mensaje que describe el error.
     */
    public DivisionPorCeroException(String mensaje) {
        super(mensaje);
    }
}
