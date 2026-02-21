package clases;

import javax.swing.JLabel;

public class Reloj extends Thread {
    private Administrador admin;
    private JLabel lblCiclos;
    private int ciclo = 0;
    private int velocidad = 1000; // 1 segundo por defecto
    private boolean pausado = false;

    public Reloj(Administrador admin, JLabel lblCiclos) {
        this.admin = admin;
        this.lblCiclos = lblCiclos;
    }

    public void setVelocidad(int ms) {
        this.velocidad = ms;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!pausado) {
                    lblCiclos.setText("Ciclo: " + ciclo);
                    admin.ejecutar(); // Mueve todo el sistema
                    ciclo++;
                }
                Thread.sleep(velocidad);
            }
        } catch (InterruptedException e) {
            System.out.println("Reloj detenido");
        }
    }
}