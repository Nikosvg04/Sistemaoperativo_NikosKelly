package interfaz;

import clases.Administrador;
import clases.LectorArchivos;
import clases.PanelGrafica; // Importamos la grafica
import clases.Reloj;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaSimulacion extends javax.swing.JFrame {

    private Administrador admin;
    private Reloj reloj;
    private Timer timerGrafica; // Refresco visual

    public VentanaSimulacion() {
        initComponents();
        configurarTablas();
        
        // Inicializar Sistema
        admin = new Administrador(lblCPU, tablaListos, tablaBloqueados, tablaTerminados, tablaSuspendidos, txtLog);
        
        // Conectar Grafica
        panelGrafica.setAdministrador(admin);
        
        // Arrancar Reloj Lógico
        reloj = new Reloj(admin, lblReloj);
        reloj.start();
        
        // Arrancar Refresco Visual (Grafica) - Cada 100ms
        timerGrafica = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelGrafica.actualizar();
            }
        });
        timerGrafica.start();
    }
    
    private void configurarTablas() {
        String[] cols = {"ID", "Nombre", "Tiempo", "Tipo", "Prio"};
        tablaListos.setModel(new DefaultTableModel(null, cols));
        tablaBloqueados.setModel(new DefaultTableModel(null, cols));
        tablaTerminados.setModel(new DefaultTableModel(null, cols));
        tablaSuspendidos.setModel(new DefaultTableModel(null, cols));
    }

    private void initComponents() {
        // Paneles y Labels
        lblTitulo = new javax.swing.JLabel();
        lblReloj = new javax.swing.JLabel();
        lblCPU = new javax.swing.JLabel();
        panelGrafica = new PanelGrafica(); // INSTANCIA DE TU NUEVA CLASE
        
        // Tablas
        scrollListos = new javax.swing.JScrollPane(); tablaListos = new javax.swing.JTable();
        scrollBloq = new javax.swing.JScrollPane(); tablaBloqueados = new javax.swing.JTable();
        scrollTerm = new javax.swing.JScrollPane(); tablaTerminados = new javax.swing.JTable();
        scrollSusp = new javax.swing.JScrollPane(); tablaSuspendidos = new javax.swing.JTable();
        scrollLog = new javax.swing.JScrollPane(); txtLog = new javax.swing.JTextArea();
        
        // Textos
        jLabel1 = new javax.swing.JLabel(); jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel(); jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel(); // Suspendidos
        
        // Controles
        btnCargar = new javax.swing.JButton();
        btnRandom20 = new javax.swing.JButton();
        btnInterrupcion = new javax.swing.JButton();
        btnDesbloquear = new javax.swing.JButton();
        sliderVelocidad = new javax.swing.JSlider();
        comboAlgo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simulador SO - Final");
        setSize(1200, 800);

        lblTitulo.setText("CPU STATUS");
        lblCPU.setText("[LIBRE]");
        lblCPU.setFont(new java.awt.Font("Arial", 1, 24));
        lblCPU.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
        lblCPU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // Configurar componentes
        txtLog.setEditable(false); scrollLog.setViewportView(txtLog);
        scrollListos.setViewportView(tablaListos);
        scrollBloq.setViewportView(tablaBloqueados);
        scrollTerm.setViewportView(tablaTerminados);
        scrollSusp.setViewportView(tablaSuspendidos);
        
        jLabel1.setText("Listos (RAM)"); jLabel2.setText("Bloqueados");
        jLabel3.setText("Terminados"); jLabel4.setText("Log del Sistema");
        jLabel5.setText("Suspendidos (Disco / Swap)");

        btnCargar.setText("Cargar CSV");
        btnCargar.addActionListener(e -> cargarArchivo());
        
        btnRandom20.setText("+20 Procesos (Test Swap)");
        btnRandom20.addActionListener(e -> admin.crearProcesosAleatorios(20));
        
        btnInterrupcion.setText("Interrupcion E/S");
        btnInterrupcion.addActionListener(e -> admin.bloquearProceso());
        
        btnDesbloquear.setText("Fin E/S");
        btnDesbloquear.addActionListener(e -> admin.desbloquearProceso());

        sliderVelocidad.setMinimum(100); sliderVelocidad.setMaximum(2000);
        sliderVelocidad.setValue(1000); sliderVelocidad.setInverted(true);
        sliderVelocidad.addChangeListener(e -> reloj.setVelocidad(sliderVelocidad.getValue()));

        comboAlgo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FCFS", "Round Robin" }));
        comboAlgo.addActionListener(e -> admin.setAlgoritmo(comboAlgo.getSelectedIndex()));

        // LAYOUT (Distribuimos todo para que quepa)
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    // ARRIBA: CPU | GRAFICA | CONTROLES
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCPU, 200, 200, 200)
                        .addGap(20)
                        .addComponent(panelGrafica, 350, 350, 350) // La gráfica aquí
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblReloj)
                            .addComponent(btnInterrupcion).addComponent(btnDesbloquear)
                            .addComponent(sliderVelocidad, 200, 200, 200)
                            .addComponent(comboAlgo, 150, 150, 150)
                            .addComponent(btnRandom20)
                        ))
                    
                    // MEDIO: TABLAS
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1).addComponent(scrollListos, 200, 200, 200).addComponent(btnCargar))
                        .addGap(10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5).addComponent(scrollSusp, 200, 200, 200)) // Suspendidos
                        .addGap(10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2).addComponent(scrollBloq, 200, 200, 200))
                        .addGap(10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3).addComponent(scrollTerm, 200, 200, 200)))
                            
                    // ABAJO: LOG
                    .addComponent(jLabel4)
                    .addComponent(scrollLog, 850, 850, 850)
                ).addContainerGap())
        );
        
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addGap(20)
                // ZONA SUPERIOR
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCPU, 100, 100, 100)
                    .addComponent(panelGrafica, 180, 180, 180)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblReloj)
                        .addComponent(btnInterrupcion).addComponent(btnDesbloquear)
                        .addComponent(sliderVelocidad, 30, 30, 30)
                        .addComponent(comboAlgo, 30, 30, 30)
                        .addComponent(btnRandom20)))
                .addGap(20)
                // TITULOS
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1).addComponent(jLabel5).addComponent(jLabel2).addComponent(jLabel3))
                // TABLAS
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollListos, 200, 200, 200)
                    .addComponent(scrollSusp, 200, 200, 200)
                    .addComponent(scrollBloq, 200, 200, 200)
                    .addComponent(scrollTerm, 200, 200, 200))
                .addGap(10)
                .addComponent(btnCargar)
                .addGap(10)
                // LOG
                .addComponent(jLabel4)
                .addComponent(scrollLog, 150, 150, 150)
                .addContainerGap())
        );
        pack();
    }
    
    private void cargarArchivo() {
        JFileChooser selector = new JFileChooser();
        if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String ruta = selector.getSelectedFile().getAbsolutePath();
            LectorArchivos lector = new LectorArchivos();
            admin.cargarDesdeArchivo(lector.leerCSV(ruta));
        }
    }

    // Componentes
    private javax.swing.JLabel lblTitulo, lblReloj, lblCPU;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5;
    private javax.swing.JTable tablaListos, tablaBloqueados, tablaTerminados, tablaSuspendidos;
    private javax.swing.JScrollPane scrollListos, scrollBloq, scrollTerm, scrollSusp, scrollLog;
    private javax.swing.JTextArea txtLog;
    private javax.swing.JButton btnCargar, btnRandom20, btnInterrupcion, btnDesbloquear;
    private javax.swing.JSlider sliderVelocidad;
    private javax.swing.JComboBox<String> comboAlgo;
    
    // Grafica Personalizada
    private PanelGrafica panelGrafica;
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new VentanaSimulacion().setVisible(true));
    }
}