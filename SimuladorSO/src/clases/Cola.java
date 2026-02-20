package clases;

public class Cola {
    
    private Nodo inicio;
    private Nodo fin;
    private int tamano;

    public Cola() {
        this.inicio = null;
        this.fin = null;
        this.tamano = 0;
    }

    // --- MÉTODOS QUE BUSCA EL ADMINISTRADOR ---

    // 1. Método para saber si está vacía
    public boolean esVacia() {
        return inicio == null;
    }

    // 2. Método para meter procesos
    public void encolar(Proceso p) {
        Nodo nuevo = new Nodo(p);
        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        tamano++;
    }

    // 3. Método para sacar procesos (Devuelve un Proceso, no un Object)
    public Proceso desencolar() {
        if (!esVacia()) {
            Proceso dato = inicio.getDato();
            inicio = inicio.getSiguiente();
            if (inicio == null) {
                fin = null;
            }
            tamano--;
            return dato;
        }
        return null;
    }

    // 4. Método para ver el inicio (usado para actualizar la lista visual)
    public Nodo getInicio() {
        return inicio;
    }
}