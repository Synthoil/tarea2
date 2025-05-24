/**
 * Clase que representa a un empleado y su informacion personal, es participante.
 */

class Empleado implements Participante{
    private String id;
    private String apellidos;
    private String nombre;
    private String correo;

    /**
     * Construye al empleado con sus datos personales.
     *
     * @param id Identificador del empleado.
     * @param nombre Nombre del empleado.
     * @param apellidos Apellidos del empleado.
     * @param correo Correo del empleado.
     */
    public Empleado(String id, String nombre, String apellidos, String correo) {
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


    /**
     * Retorna la concatenacion de nombre y apellidos.
     *
     * @return nombre + apellido.
     */
    @Override
    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }

    /**
     * Entrega la descripcion de la clase.
     *
     * @return descripcion.
     */
    @Override
    public String toString(){
        return "Empleado{"+
                "Id = "+ id + '\'' +
                ", Nombre='" + nombre + " " + apellidos + '\'' +
                ", Correo='" + correo + '\'' +
                '}';
    }
}