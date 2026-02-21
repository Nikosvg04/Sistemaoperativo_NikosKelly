package clases;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelGrafica extends JPanel {
    
    private Administrador admin;

    public PanelGrafica() {
        this.setBackground(Color.BLACK);
    }
    
    public void setAdministrador(Administrador admin) {
        this.admin = admin;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (admin == null) return;
        
        // Obtenemos datos
        int listos = admin.getCantidadListos();
        int bloqueados = admin.getCantidadBloqueados();
        int suspendidos = admin.getCantidadSuspendidos();
        
        // Dibujamos Barras
        // Barra VERDE: Listos
        g.setColor(Color.GREEN);
        g.fillRect(50, 150 - (listos * 10), 50, listos * 10);
        g.drawString("Listos: " + listos, 50, 165);
        
        // Barra ROJA: Bloqueados
        g.setColor(Color.RED);
        g.fillRect(150, 150 - (bloqueados * 10), 50, bloqueados * 10);
        g.drawString("Bloq: " + bloqueados, 150, 165);
        
        // Barra AZUL: Suspendidos
        g.setColor(Color.CYAN);
        g.fillRect(250, 150 - (suspendidos * 10), 50, suspendidos * 10);
        g.drawString("Susp: " + suspendidos, 250, 165);
        
        // Titulo
        g.setColor(Color.WHITE);
        g.drawString("CARGA DEL SISTEMA", 100, 20);
    }
    
    // Método para repintar desde fuera
    public void actualizar() {
        this.repaint();
    }
}
