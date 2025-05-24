public class InvitadoExterno implements Participante {
    private String nombre;
    private String apellidos;
    private String correo;

    public InvitadoExterno(String nombre, String apellidos, String correo){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    @Override
    public String getCorreo(){
        return correo;
    }

    @Override
    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }

    @Override
    public String toString(){
        return "Invitado externo "+ nombre + " " + apellidos + "("+correo+")";
    }
}
