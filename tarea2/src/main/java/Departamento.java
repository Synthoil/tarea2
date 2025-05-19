import java.util.ArrayList;

class Departamento {
    private String nombre_dept;
    private lista<Empleado> empleados = new lista<>();

    public Departamento(String nombre_dept) {
        this.nombre_dept = nombre_dept;
    }

    public int obtenerCantidadEmpleados() {
        int count = 0;
        while(empleados.getElemento() != null) count++;
        return count;
    }

    public void addEmpleado(Empleado emp){
        empleados.addElemento(emp);
    }

}