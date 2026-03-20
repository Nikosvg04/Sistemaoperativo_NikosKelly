package gui;

import controlador.SistemaArchivos;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.Color;

public class VentanaPrincipal extends javax.swing.JFrame {

    private SistemaArchivos sistema;    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName());

    public VentanaPrincipal() {
        initComponents();
        this.sistema = new SistemaArchivos();
        
        // ¡MAGIA! Cargamos el árbol y la tabla apenas se abre la ventana
        refrescarArbol(sistema.getCarpetaRaiz()); 
        refrescarTablaFAT();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        arbolArchivos = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        panelDiscoVisual = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaFAT = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        comboModoUsuario = new javax.swing.JComboBox<>();
        comboPoliticas = new javax.swing.JComboBox<>();
        btnCrear = new javax.swing.JButton();
        btnLeer = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(arbolArchivos);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.LINE_START);

        panelDiscoVisual.setBorder(javax.swing.BorderFactory.createTitledBorder("Simulación de Disco (SD)"));

        javax.swing.GroupLayout panelDiscoVisualLayout = new javax.swing.GroupLayout(panelDiscoVisual);
        panelDiscoVisual.setLayout(panelDiscoVisualLayout);
        panelDiscoVisualLayout.setHorizontalGroup(
            panelDiscoVisualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelDiscoVisualLayout.setVerticalGroup(
            panelDiscoVisualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        tablaFAT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaFAT);

        comboModoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboPoliticas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnLeer.setText("Leer");
        btnActualizar.setText("Actualizar");
        btnEliminar.setText("Eliminar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboModoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPoliticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrear)
                    .addComponent(btnLeer)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(comboModoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboPoliticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCrear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLeer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(panelDiscoVisual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelDiscoVisual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>                        

    // --- ACCIÓN DEL BOTÓN CREAR ARCHIVO ---
    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del archivo (Ej. reporte.txt):");
        
        if (nombre == null || nombre.trim().isEmpty()) {
            return; 
        }

        String tamañoStr = JOptionPane.showInputDialog(this, "Ingrese el tamaño en bloques (Ej. 5):");
        if (tamañoStr == null || tamañoStr.trim().isEmpty()) {
            return;
        }

        try {
            int tamaño = Integer.parseInt(tamañoStr); 
            Color colorAleatorio = new Color((int)(Math.random() * 0x1000000));
            
            // Llamamos al cerebro (Backend)
            String mensaje = sistema.solicitarCreacionArchivo(nombre, tamaño, "Usuario", colorAleatorio);
            
            // Mostramos resultado
            JOptionPane.showMessageDialog(this, mensaje, "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
            
            // ¡ACTUALIZAMOS EL ÁRBOL Y LA TABLA VISUALMENTE!
            refrescarArbol(sistema.getCarpetaRaiz());
            refrescarTablaFAT(); 
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: El tamaño debe ser un número entero válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }                                        

    // --- MÉTODOS DEL JTREE ---
    public void refrescarArbol(modelo.DirectorioVirtual carpetaRaiz) {
        DefaultMutableTreeNode nodoRaiz = construirArbol(carpetaRaiz);
        DefaultTreeModel modeloArbol = new DefaultTreeModel(nodoRaiz);
        arbolArchivos.setModel(modeloArbol);
        
        for (int i = 0; i < arbolArchivos.getRowCount(); i++) {
            arbolArchivos.expandRow(i);
        }
    }

    private DefaultMutableTreeNode construirArbol(modelo.DirectorioVirtual directorioActual) {
        String etiquetaDir = directorioActual.getNombre();
        DefaultMutableTreeNode nodoDir = new DefaultMutableTreeNode(etiquetaDir);

        estructuras.ListaEnlazada<modelo.NodoSistemaArchivos> elementos = directorioActual.getContenido();
        
        for (int i = 0; i < elementos.tamaño(); i++) {
            modelo.NodoSistemaArchivos elementoActual = elementos.obtener(i);
            
            if (elementoActual instanceof modelo.ArchivoVirtual) {
                modelo.ArchivoVirtual archivo = (modelo.ArchivoVirtual) elementoActual;
                String etiquetaArchivo = archivo.getNombre() + " | " + archivo.getTamañoEnBloques() + " bloques";
                nodoDir.add(new DefaultMutableTreeNode(etiquetaArchivo));
            } 
            else if (elementoActual instanceof modelo.DirectorioVirtual) {
                modelo.DirectorioVirtual subCarpeta = (modelo.DirectorioVirtual) elementoActual;
                nodoDir.add(construirArbol(subCarpeta)); 
            }
        }
        return nodoDir;
    }

    // --- MÉTODO PARA ACTUALIZAR LA TABLA FAT ---
    public void refrescarTablaFAT() {
        // Obtenemos el modelo de tu tabla visual
        javax.swing.table.DefaultTableModel modeloTabla = (javax.swing.table.DefaultTableModel) tablaFAT.getModel();
        
        // ¡Mejora visual! Ponemos nombres bonitos a las columnas de la tabla
        modeloTabla.setColumnIdentifiers(new String[]{"Bloque ID", "Estado / Archivo", "Siguiente Bloque"});
        
        // Limpiamos la tabla para no duplicar datos
        modeloTabla.setRowCount(0); 

        // Traemos el arreglo de bloques que simula el disco (son 200 bloques)
        modelo.BloqueDisco[] disco = sistema.getGestorDisco().getDisco();
        
        // Recorremos todo el disco bloque por bloque
        for (int i = 0; i < disco.length; i++) {
            modelo.BloqueDisco bloque = disco[i];
            
            // Si está ocupado, mostramos el nombre del archivo. Si no, dice "Libre"
            String estadoArchivo = bloque.isOcupado() ? bloque.getNombreArchivo() : "Libre";
            
            // Si es el final del archivo mostramos "-1 (EOF)". Si no, mostramos el número del siguiente bloque.
            String siguiente = bloque.getSiguienteBloque() == -1 ? "-1 (EOF)" : String.valueOf(bloque.getSiguienteBloque());
            
            // Agregamos la fila a la tabla
            modeloTabla.addRow(new Object[]{
                bloque.getId(), 
                estadoArchivo, 
                bloque.isOcupado() ? siguiente : "-"
            });
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTree arbolArchivos;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLeer;
    private javax.swing.JComboBox<String> comboModoUsuario;
    private javax.swing.JComboBox<String> comboPoliticas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelDiscoVisual;
    private javax.swing.JTable tablaFAT;
    // End of variables declaration                   
}