package interfaz;

import clases.Reloj;
import clases.Administrador;

/**
 * Ventana principal de la simulación.
 */
public class VentanaSimulacion extends javax.swing.JFrame {

    // --- VARIABLES GLOBALES ---
    private clases.Administrador administrador;
    private clases.Reloj hiloReloj;

    // --- CONSTRUCTOR ---
    public VentanaSimulacion() {
        // 1. Cargar el diseño visual
        initComponents();

        // 2. Inicializar el Administrador (Lógica)
        administrador = new clases.Administrador(lblCPU, listaListos, listaBloqueados);

        // 3. Inicializar y arrancar el Reloj
        hiloReloj = new clases.Reloj(administrador, lblReloj);
        hiloReloj.start();
    }

    /**
     * CÓDIGO GENERADO DE LA INTERFAZ (No tocar)
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblReloj = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        btnCrear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblCPU = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaListos = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaBloqueados = new javax.swing.JList();
        btnInterrupcion = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblReloj.setText("Ciclo 0");
        lblReloj.setFont(new java.awt.Font("Segoe UI", 1, 14)); // Un poco más grande

        btnIniciar.setText("Iniciar simulacion");
        // El botón iniciar ya no es necesario porque inicia automático, pero lo dejamos por si acaso
        btnIniciar.setEnabled(false); 

        btnCrear.setText("Crear proceso");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        jLabel1.setText("CPU");

        lblCPU.setText("[Vacio]");
        lblCPU.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblCPU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setText("Cola de listos");

        jScrollPane1.setViewportView(listaListos);

        jLabel3.setText("Cola de Bloqueados");

        jScrollPane2.setViewportView(listaBloqueados);

        btnInterrupcion.setText("Interrupcion E/S");
        btnInterrupcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInterrupcionActionPerformed(evt);
            }
        });

        jButton1.setText("Terminar E/S");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        // Diseño (Layout)
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addComponent(lblCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnInterrupcion)
                            .addComponent(lblReloj)
                            .addComponent(btnIniciar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCrear)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(230, 230, 230) // Espacio para separar la cola de bloqueados
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnInterrupcion)
                        .addGap(18, 18, 18)
                        .addComponent(lblReloj)
                        .addGap(18, 18, 18)
                        .addComponent(btnIniciar)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrear)
                    .addComponent(jButton1))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    // --- ACCIONES DE LOS BOTONES ---

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {                                         
        administrador.crearProcesoManual();
    }                                        

    private void btnInterrupcionActionPerformed(java.awt.event.ActionEvent evt) {                                                
        administrador.bloquearProceso();
    }                                               

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        administrador.desbloquearProceso();
    }                                        

    /**
     * MAIN
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(VentanaSimulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Crear y mostrar la ventana */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaSimulacion().setVisible(true);
            }
        });
    }

    // --- DECLARACIÓN DE VARIABLES DE LA INTERFAZ ---
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnInterrupcion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCPU;
    private javax.swing.JLabel lblReloj;
    private javax.swing.JList listaBloqueados;
    private javax.swing.JList listaListos;
}
