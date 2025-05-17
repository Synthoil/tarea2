import java.util.ArrayList;
class lista<T> {
    private ArrayList<T> elementos = new ArrayList<>();

    public void addElemento(T elemento) {
        elementos.add(elemento);
    }

    public T getElemento() {
        return elementos.isEmpty() ? null : elementos.remove(0);
    }
}