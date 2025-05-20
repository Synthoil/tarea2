public enum TipoReunion {
    TECNICA("Tecnica"), MARKETING("Marketing"), OTRO("Otro");

    private String tipoReunion;

    TipoReunion(String tipoReunion) {
        this.tipoReunion = tipoReunion;
    }

    public String getTipoReunion() {
        return tipoReunion;
    }
}
