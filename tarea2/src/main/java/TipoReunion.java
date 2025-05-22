/**
 * Enumeración que representa los distintos tipos de reunión disponibles.
 * Cada tipo tiene una representación textual asociada.
 */
public enum TipoReunion {
    TECNICA("Tecnica"), MARKETING("Marketing"), OTRO("Otro");

    /**
     * Representación textual del tipo de reunión.
     */
    private String tipoReunion;

    /**
     * Constructor de la enumeración TipoReunion.
     *
     * @param tipoReunion String para el tipo de reunion.
     */
    TipoReunion(String tipoReunion) {
        this.tipoReunion = tipoReunion;
    }

    /**
     * Obtiene la representación textual del tipo de reunión.
     *
     * @return Una cadena con el tipo de reunión.
     */
    public String getTipoReunion() {
        return tipoReunion;
    }
}
