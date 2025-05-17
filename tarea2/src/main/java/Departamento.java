class Departamento {
    private String nombre_dept;
    private int empleados;

    public Departamento(String nombre_dept) {
        this.nombre_dept = nombre_dept;
    }

    public int obtenerCantidadEmpleados() {
        return empleados;
    }

    public void addEmpleado() {
        empleados++;
    }

}