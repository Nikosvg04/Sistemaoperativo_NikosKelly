package modelo;

public class BloqueDisco {
    private int id;                 // El número del bloque (ej: Bloque 0, 1, 2...)
    private boolean ocupado;        // ¿Hay algo guardado aquí?
    private String nombreArchivo;   // ¿A qué archivo pertenece? (Útil para la tabla FAT)
    private int siguienteBloque;    // Magia de la asignación encadenada: apunta al ID del siguiente bloque (-1 si es el último)

    public BloqueDisco(int id) {
        this.id = id;
        this.ocupado = false;
        this.nombreArchivo = "";
        this.siguienteBloque = -1;  // -1 significa "Fin del archivo" o "Bloque Libre"
    }

    // --- Getters y Setters ---
    public int getId() { return id; }
    
    public boolean isOcupado() { return ocupado; }
    public void setOcupado(boolean ocupado) { this.ocupado = ocupado; }

    public String getNombreArchivo() { return nombreArchivo; }
    public void setNombreArchivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; }

    public int getSiguienteBloque() { return siguienteBloque; }
    public void setSiguienteBloque(int siguienteBloque) { this.siguienteBloque = siguienteBloque; }
    
    // Método para vaciar el bloque rápidamente cuando se elimine un archivo
    public void liberar() {
        this.ocupado = false;
        this.nombreArchivo = "";
        this.siguienteBloque = -1;
    }
}
