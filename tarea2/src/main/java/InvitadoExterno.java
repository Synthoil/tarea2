public class InvitadoExterno implements Participante {
    private String nombreCompleto;
    private String correo;

    public InvitadoExterno(String nombreCompleto, String correo){
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
    }

    @Override
    public String getCorreo(){
        return correo;
    }

    @Override
    public String getNombreCompleto(){
        return nombreCompleto;
    }

    @Override
    public String toString(){
        return "Invitado externo "+ nombreCompleto + "("+correo+")";
    }
}
