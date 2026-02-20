package clases;

public class Proceso {
    
    private String nombre;
    private int tiempoRestante;

    public Proceso(String nombre, int tiempoRestante) {
        this.nombre = nombre;
        this.tiempoRestante = tiempoRestante;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void restarTiempo() {
        this.tiempoRestante--;
    }

    @Override
    public String toString() {
        return nombre + " (" + tiempoRestante + "s)";
    }
}