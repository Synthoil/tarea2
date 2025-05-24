/**
 * La interfaz define un comportamiento similar para Empleado e InvitadoExterno
 * lo que permite usarlos polimorficamente.
 */

public interface Participante {
    /**
     * Retorna la concatenacion de nombre y apellidos (Implementado en las clases que usan la interfaz).
     *
     * @return nombre + apellido.
     */
    String getNombreCompleto();

    /**
     * Retorna el correo (Implementado en las clases que usan la interfaz).
     *
     * @return correo.
     */
    String getCorreo();
}
