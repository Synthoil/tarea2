import java.time.*;
public class Invitacion {
    private Instant hora;
    private Invitable invitable;

    public Invitacion(Instant hora, Invitable invitable) {
        this.hora = hora;
        this.invitable = invitable;
    }

    public Instant getHora() {
        return hora;
    }

    public void setHora(Instant hora) {
        this.hora = hora;
    }

    public Invitable getInvitable() {
        return invitable;
    }

    public void setInvitable(Invitable invitable) {
        this.invitable = invitable;
    }
}
