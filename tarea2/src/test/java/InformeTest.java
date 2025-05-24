import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class InformeTest {
    private ReunionPresencial reunion;
    private Informe informe;
    private final String ARCHIVO = "informe_date_test.txt";
    private Date fecha;

    @BeforeEach
    void setUp() {
        fecha = new Date(124, Calendar.APRIL, 10);
        reunion = new ReunionPresencial(
                fecha,
                Instant.parse("2024-04-10T15:00:00Z"),
                Duration.ofHours(1),
                TipoReunion.MARKETING,
                new Empleado("001", "Org", "Reunion", "org@empresa.com"),
                "Sala C2"
        );

        informe = new Informe();
        try {
            Files.deleteIfExists(Paths.get(ARCHIVO));
        } catch (IOException e) {
            fail("No se pudo limpiar archivo previo");
        }
    }

    @Test
    @DisplayName("Informe muestra fecha correctamente")
    void testFechaEnInforme() throws Exception {
        reunion.iniciar();
        reunion.finalizar();

        informe.exportarResumen(reunion);

        String contenido = Files.readString(Paths.get(ARCHIVO));
        assertTrue(contenido.contains("2024-04-10"));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(ARCHIVO));
    }
}