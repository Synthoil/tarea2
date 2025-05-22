import java.util.ArrayList;

/**
 * Clase que representa un departamento, una agrupacion de empleados identificada por un nombre.
 * */

class Departamento{
    private String nombre_dept;
    private Lista<Empleado> empleados = new Lista<>();

    /**
     * Inicia el departamento entregandole un nombre.
     *
     * @param nombre_dept Nombre del departamento.
     */
    public Departamento(String nombre_dept) {
        this.nombre_dept = nombre_dept;
    }

    /**
     * Devuelve la cantidad de empleados dentro del departamento.
     *
     * @return int de la cantidad de empleados.
     */
    public int obtenerCantidadEmpleados() {
        return empleados.obtenerCantidad();
    }

    /**
     * Agrega un empleado a la lista de empleados dentro de este.
     *
     * @param emp Empleado que se agrega.
     */
    public void addEmpleado(Empleado emp){
        empleados.addElemento(emp);
    }

    /**
     * Devuelve la lista completa de todos los empleados en el departamento.
     *
     * @return lista de empleados
     */
    public Lista<Empleado> getEmpleados() {
        return empleados;
    }

    /**
     * Entrega el nombre del departamento
     *
     * @return nombre del departamente
     */
    public String getNombre_dept() {
        return nombre_dept;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Departamento ").append(nombre_dept).append("(")
                .append(obtenerCantidadEmpleados()).append(" Mienbros en el departamento)\n");

        ArrayList<Empleado> empleadosCopia = empleados.copiaElementos();

        for (int i=0; i<empleadosCopia.size();i++){
            sb.append("\n-").append(empleadosCopia.get(i).toString());
        }

        return sb.toString();
    }
}