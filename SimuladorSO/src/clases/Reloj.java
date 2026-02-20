package clases;

import javax.swing.JLabel;

public class Reloj extends Thread {
    
    private Administrador administrador;
    private JLabel lblReloj; 
    private int ciclo = 0;

    // Constructor que recibe el admin y la etiqueta
    public Reloj(Administrador administrador, JLabel lblReloj) {
        this.administrador = administrador;
        this.lblReloj = lblReloj;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 1. Actualizamos el texto del ciclo visualmente
                lblReloj.setText("Ciclo: " + ciclo);
                
                // 2. ¡AQUÍ ESTÁ LA CLAVE! 
                // Le ordenamos al administrador que mueva los procesos
                administrador.gestionarCPU();
                
                // 3. Esperamos 1 segundo (1000 milisegundos)
                Thread.sleep(1000);
                
                // 4. Aumentamos el contador
                ciclo++;
            }
        } catch (InterruptedException e) {
            System.err.println("Reloj interrumpido");
        }
    }
}