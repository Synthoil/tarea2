/**
 * Excepcion lanzada cuando se intenta interactuar con un departamento sin empleados que lo conformen.
 */

class DepartamentoVacioException extends Exception {
    /**
     * Crea la excepcion con el mensaje.
     *
     * @param mensaje Mensaje que describe el error.
     */
    public DepartamentoVacioException(String mensaje) {
        super(mensaje);
    }
}
