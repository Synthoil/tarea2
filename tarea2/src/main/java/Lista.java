import java.util.ArrayList;
public class Lista<T> {
    private ArrayList<T> elementos = new ArrayList<>();

    public void addElemento(T elemento) {
        elementos.add(elemento);
    }

    public T getElemento() {
        return elementos.isEmpty() ? null : elementos.remove(0);
    }

    public T leerElemento() {
        return elementos.isEmpty() ? null : elementos.get(elementos.size() - 1);
    }

    public Boolean contieneElemento(T elemento) {
        return elementos.contains(elemento);
    }

}