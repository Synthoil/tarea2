/**
 * Clase que representa una nota con un contenido de texto.
 */

public class Nota {
    private String contenido;

    /**
     * En el constructor se escribe el texto dentro de la nota.
     *
     * @param contenido Texto de la nota.
     */
    public Nota(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Obtiene el texto contenido en la nota.
     *
     * @return contenido o texto.
     */
    public String getContenido() {
        return contenido;
    }

}
