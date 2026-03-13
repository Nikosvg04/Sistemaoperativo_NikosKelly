package modelo;

public abstract class NodoSistemaArchivos {
    protected String nombre;
    protected String dueño; // "Administrador" o "Usuario"
    protected boolean esDirectorio;

    public NodoSistemaArchivos(String nombre, String dueño, boolean esDirectorio) {
        this.nombre = nombre;
        this.dueño = dueño;
        this.esDirectorio = esDirectorio;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; } // Solo el admin usará esto

    public String getDueño() { return dueño; }
    
    public boolean isDirectorio() { return esDirectorio; }
    
    // Este método es obligatorio para que el JTree (la interfaz visual) muestre el nombre correcto
    @Override
    public String toString() {
        return this.nombre;
    }
}