package clases;

public class Nodo {
    
    // Variables
    private Proceso dato;
    private Nodo siguiente;

    // Constructor
    public Nodo(Proceso dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    // --- MÉTODOS GET Y SET QUE BUSCA EL ADMINISTRADOR ---
    
    public Proceso getDato() {
        return dato;
    }

    public void setDato(Proceso dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}