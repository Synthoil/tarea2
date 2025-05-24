import org.junit.jupiter.api.*;
import java.time.*;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class ReunionTest {
    private ReunionPresencial reunion;
    private Empleado organizador;
    private Date fecha;
    private Instant horaPrevista;

    @BeforeEach
    void setUp() {
        fecha = new Date(124, 4, 15);
        horaPrevista = Instant.parse("2024-05-15T14:00:00Z");
        organizador = new Empleado("001", "Pérez", "Juan", "juan@empresa.com");

        reunion = new ReunionPresencial(
                fecha,
                horaPrevista,
                Duration.ofHours(2),
                TipoReunion.TECNICA,
                organizador,
                "Sala A1"
        );
    }

    @Test
    @DisplayName("Creación correcta ")
    void testCreacion() {
        assertAll(
                () -> assertEquals(fecha, reunion.getFecha()),
                () -> assertEquals(horaPrevista, reunion.getHoraPrevista()),
                () -> assertEquals("Sala A1", reunion.getSala())
        );
    }

    @Test
    @DisplayName("Iniciar reunión actualiza horaInicio")
    void testIniciarReunion() {
        reunion.iniciar();
        assertNotNull(reunion.getHoraInicio());
        assertTrue(Instant.now().compareTo(reunion.getHoraInicio()) >= 0);
    }
}