import java.util.ArrayList;

class Departamento{
    private String nombre_dept;
    private Lista<Empleado> empleados = new Lista<>();

    public Departamento(String nombre_dept) {
        this.nombre_dept = nombre_dept;
    }

    public int obtenerCantidadEmpleados() {
        return empleados.obtenerCantidad();
    }

    public void addEmpleado(Empleado emp){
        empleados.addElemento(emp);
    }

    public Lista<Empleado> getEmpleados() {
        return empleados;
    }
}