/**
 * Clase que describe a un invitado sin identificacion, es participante.
 */
public class InvitadoExterno implements Participante {
    private String nombre;
    private String apellidos;
    private String correo;

    /**
     * Construye al invitado con su informacion personal.
     *
     * @param nombre Nombre del invitado.
     * @param apellidos Apellidos del invitado.
     * @param correo Correo del invitado.
     */
    public InvitadoExterno(String nombre, String apellidos, String correo){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    /**
     * Entrega el correo del invitado.
     *
     * @return correo.
     */
    @Override
    public String getCorreo(){
        return correo;
    }

    /**
     * Devuelve la concatenacion del nombre con los apellidos de la persona.
     *
     * @return nombre + apellido.
     */
    @Override
    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }

    /**
     * Entrega la descripcion de la clase.
     *
     * @return descripcion.
     */
    @Override
    public String toString(){
        return "Invitado externo "+ nombre + " " + apellidos + "("+correo+")";
    }
}
