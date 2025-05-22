/**
 * Clase que representa a un empleado con id, appelidos, nombre y correo
 */

class Empleado {
    private String id;
    private String apellidos;
    private String nombre;
    private String correo;

    public Empleado(String id, String apellidos, String nombre, String correo) {
        this.id = id;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.correo = correo;
    }

    /**
     * Obtiene el identificador del empleado.
     *
     * @return el ID del empleado
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del empleado.
     *
     * @param id el ID a establecer
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene los apellidos del empleado.
     *
     * @return los apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del empleado.
     *
     * @param apellidos los apellidos a establecer
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el nombre del empleado.
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del empleado.
     *
     * @param nombre el nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el correo electrónico del empleado.
     *
     * @return el correo electrónico
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del empleado.
     *
     * @param correo el correo electrónico a establecer
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
