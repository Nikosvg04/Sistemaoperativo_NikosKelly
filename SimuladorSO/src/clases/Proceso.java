package clases;

public class Proceso {
    private String id;
    private String nombre;
    private String tipo;      // "Computo" o "E/S"
    private int tiempoTotal;
    private int tiempoRestante;
    private int prioridad;    // 1=Alta, 2=Media, 3=Baja
    
    // Para métricas
    private long tiempoLlegada;

    public Proceso(String id, String nombre, String tipo, int tiempo, int prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.tiempoTotal = tiempo;
        this.tiempoRestante = tiempo;
        this.prioridad = prioridad;
        this.tiempoLlegada = System.currentTimeMillis();
    }

    public void restarTiempo() {
        if (tiempoRestante > 0) tiempoRestante--;
    }
    
    public boolean haTerminado() {
        return tiempoRestante <= 0;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public int getTiempoTotal() { return tiempoTotal; }
    public int getTiempoRestante() { return tiempoRestante; }
    public int getPrioridad() { return prioridad; }
}