package modelo;

import estructuras.ListaEnlazada; // Importamos tu propia estructura, ¡cero librerías ilegales!

public class DirectorioVirtual extends NodoSistemaArchivos {
    // Aquí guardamos los archivos o subcarpetas que están dentro de este directorio
    private ListaEnlazada<NodoSistemaArchivos> contenido;

    public DirectorioVirtual(String nombre, String dueño) {
        super(nombre, dueño, true); // true porque SÍ es un directorio
        this.contenido = new ListaEnlazada<>();
    }

    public void agregarElemento(NodoSistemaArchivos elemento) {
        this.contenido.agregar(elemento);
    }

    public ListaEnlazada<NodoSistemaArchivos> getContenido() {
        return contenido;
    }
}