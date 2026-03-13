package modelo;

import java.awt.Color;

public class ArchivoVirtual extends NodoSistemaArchivos {
    private int tamañoEnBloques;
    private int idPrimerBloque; // Apunta al primer bloque en el disco
    private Color color;        // Para dibujarlo en la interfaz visual

    public ArchivoVirtual(String nombre, String dueño, int tamañoEnBloques, Color color) {
        super(nombre, dueño, false); // false porque NO es un directorio
        this.tamañoEnBloques = tamañoEnBloques;
        this.idPrimerBloque = -1;    // Inicialmente no tiene bloques asignados
        this.color = color;
    }

    public int getTamañoEnBloques() { return tamañoEnBloques; }
    
    public int getIdPrimerBloque() { return idPrimerBloque; }
    public void setIdPrimerBloque(int idPrimerBloque) { this.idPrimerBloque = idPrimerBloque; }
    
    public Color getColor() { return color; }
}