import org.w3c.dom.ls.LSOutput;
import java.time.*;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ReunionVirtual reunion = new ReunionVirtual();
        Duration diff = Duration.between(reunion.horaInicio, reunion.horaFin);
        String hms = String.format("%d:%02d:%02d",
                diff.toHours(),
                diff.toMinutesPart(),
                diff.toSecondsPart());
        System.out.println(hms);
        reunion.iniciar();
        System.out.println(reunion.horaInicio);
    }
}