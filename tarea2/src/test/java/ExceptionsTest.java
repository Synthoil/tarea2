import java.time.Duration;
import java.time.Instant;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class ExceptionsTest {
    private Date fechaPasada;
    private Date fechaFutura;

    @BeforeEach
    void setUp() {
        fechaPasada = new Date(124, Calendar.JANUARY, 1);
        fechaFutura = new Date(124, Calendar.DECEMBER, 31);
    }

    @Test
    @DisplayName("Reunión con fecha pasada no puede iniciarse")
    void testFechaPasada() {
        ReunionVirtual reunion = new ReunionVirtual(
                fechaPasada,
                Instant.now().minusSeconds(3600),
                Duration.ofHours(1),
                TipoReunion.OTRO,
                new Empleado("001", "Test", "User", "test@empresa.com"),
                "https://meet.example.com"
        );

        assertThrows(TiempoReunionInvalidoException.class, () -> {
            reunion.iniciar();
        });
    }

    @Test
    @DisplayName("Reunión con fecha futura no puede finalizarse")
    void testFechaFutura() {
        ReunionPresencial reunion = new ReunionPresencial(
                fechaFutura,
                Instant.now().plusSeconds(3600 * 24),
                Duration.ofHours(1),
                TipoReunion.TECNICA,
                new Empleado("001", "Test", "User", "test@empresa.com"),
                "Sala X1"
        );

        assertThrows(TiempoReunionInvalidoException.class, () -> {
            reunion.finalizar();
        });
    }
}