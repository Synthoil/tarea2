import java.util.ArrayList;

public class Departamento implements Invitable{
    private String nombre_dept;
    private Lista<Empleado> empleados = new Lista<>();

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

    @Override
    public void invitar() {

    }
}