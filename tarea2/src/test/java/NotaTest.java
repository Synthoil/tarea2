import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.*;
import java.time.*;

class NotaTest {
    private ReunionVirtual reunion;
    private Date fecha;

    @BeforeEach
    void setUp() {
        fecha = new Date(124, Calendar.MAY, 15);
        reunion = new ReunionVirtual(
                fecha,
                Instant.now(),
                Duration.ofHours(1),
                TipoReunion.OTRO,
                new Empleado("001", "Admin", "Sistema", "admin@empresa.com"),
                "https://meet.example.com"
        );
    }

    @Test
    @DisplayName("Agregar nota válida")
    void testNotaValida() {
        assertDoesNotThrow(() -> {
            reunion.agregarNota(new Nota("Nota importante"));
        });
        assertEquals(1, reunion.getNotas().obtenerCantidad());
        assertEquals(fecha, reunion.getFecha());
    }

    @Test
    @DisplayName("Nota vacía lanza excepción")
    void testNotaVacia() {
        assertThrows(NotaInvalidaException.class, () -> {
            reunion.agregarNota(new Nota(""));
        });
        assertEquals(0, reunion.getNotas().obtenerCantidad());
    }

    @Test
    @DisplayName("Orden correcto de notas")
    void testOrdenNotas() throws Exception {
        assertEquals(2024, fecha.getYear() + 1900);
        assertEquals(Calendar.MAY, fecha.getMonth());

        reunion.agregarNota(new Nota("Primera"));
        reunion.agregarNota(new Nota("Segunda"));

        ArrayList<Nota> notas = reunion.getNotas().copiaElementos();
        assertAll(
                () -> assertEquals("Primera", notas.get(0).getContenido()),
                () -> assertEquals("Segunda", notas.get(1).getContenido()),
                () -> assertEquals(2, notas.size())
        );
    }

    @Test
    @DisplayName("Múltiples notas en reunión con fecha específica")
    void testMultiplesNotas() {
        Date fechaEspecifica = new Date(124, Calendar.JUNE, 10);
        ReunionPresencial reunionPresencial = new ReunionPresencial(
                fechaEspecifica,
                Instant.now(),
                Duration.ofHours(2),
                TipoReunion.TECNICA,
                new Empleado("002", "Org", "Reunion", "org@empresa.com"),
                "Sala B1"
        );

        assertDoesNotThrow(() -> {
            reunionPresencial.agregarNota(new Nota("Nota técnica 1"));
            reunionPresencial.agregarNota(new Nota("Nota técnica 2"));
        });

        assertEquals(2, reunionPresencial.getNotas().obtenerCantidad());
        assertEquals(10, reunionPresencial.getFecha().getDate());
    }
}