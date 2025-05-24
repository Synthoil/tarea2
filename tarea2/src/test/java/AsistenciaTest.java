import java.time.Duration;
import java.time.Instant;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class AsistenciaTest {
    private ReunionPresencial reunion;
    private Empleado empleado;
    private Date fecha;

    @BeforeEach
    void setUp() {
        fecha = new Date(124, Calendar.JUNE, 1);
        empleado = new Empleado("002", "Martínez", "Carlos", "carlos@empresa.com");

        reunion = new ReunionPresencial(
                fecha,
                Instant.now().plusSeconds(120),
                Duration.ofHours(1),
                TipoReunion.TECNICA,
                new Empleado("001", "Organizador", "Reu", "org@empresa.com"),
                "Sala A1"
        );
    }

    @Test
    @DisplayName("Asistencia con fecha correcta")
    void testAsistenciaFechaCorrecta() throws Exception {
        reunion.iniciar();
        reunion.asisteParticipante(empleado);

        assertEquals(fecha, reunion.getFecha());
        assertEquals(2, reunion.obtenerTotalAsistencia());
    }

    @Test
    @DisplayName("Ausencias con fecha")
    void testAusencias() {
        reunion.invitarParticipante(empleado);
        reunion.iniciar();

        assertEquals(1, reunion.obtenerAusencias().obtenerCantidad());
        assertTrue(reunion.obtenerAusencias().contieneElemento(empleado));
    }
}
