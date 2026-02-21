package clases;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.Random;

public class Administrador {

    // --- GUI ---
    private JLabel lblCPU;
    private JTable tablaListos;
    private JTable tablaBloqueados;
    private JTable tablaTerminados;
    private JTable tablaSuspendidos; // <--- NUEVO
    private JTextArea txtLog; 

    // --- ESTRUCTURAS ---
    private Cola colaListos;
    private Cola colaBloqueados;
    private Cola colaTerminados;
    private Cola colaSuspendidos; // <--- NUEVO
    
    // --- LÓGICA ---
    private Proceso procesoEnCPU;
    private int algoritmo = 0; // 0=FCFS, 1=RR
    private int quantum = 3;
    private int contadorQuantum = 0;
    private int maxMemoria = 10; // Si pasa de 10, va a suspendidos
    
    private Random azar = new Random();
    private int contadorID = 100;
    private Planificador planificador = new Planificador();

    public Administrador(JLabel lblCPU, JTable tListos, JTable tBloq, JTable tTerm, JTable tSusp, JTextArea log) {
        this.lblCPU = lblCPU;
        this.tablaListos = tListos;
        this.tablaBloqueados = tBloq;
        this.tablaTerminados = tTerm;
        this.tablaSuspendidos = tSusp;
        this.txtLog = log;
        
        this.colaListos = new Cola();
        this.colaBloqueados = new Cola();
        this.colaTerminados = new Cola();
        this.colaSuspendidos = new Cola();
    }
    
    // --- GETTERS PARA LA GRÁFICA ---
    public int getCantidadListos() { return contarCola(colaListos); }
    public int getCantidadBloqueados() { return contarCola(colaBloqueados); }
    public int getCantidadSuspendidos() { return contarCola(colaSuspendidos); }

    // Auxiliar para contar (ya que Cola no tiene size público a veces)
    private int contarCola(Cola c) {
        int cont = 0;
        Nodo temp = c.getInicio();
        while(temp != null) { cont++; temp = temp.getSiguiente(); }
        return cont;
    }

    public void setAlgoritmo(int tipo) {
        this.algoritmo = tipo;
        String nombreAlgo = "";
        switch(tipo) {
            case 0: nombreAlgo = "FCFS"; break;
            case 1: nombreAlgo = "Round Robin"; break;
            case 2: nombreAlgo = "Prioridad"; break;
            case 3: nombreAlgo = "SRT"; break;
        }
        log("Algoritmo cambiado a: " + nombreAlgo);
    }

    public void ejecutar() {
        // 1. REVISAR SUSPENDIDOS (Swapping in)
        // Si hay espacio en RAM (Listos < 5) y hay gente esperando en Disco (Suspendidos)
        if (contarCola(colaListos) < 5 && !colaSuspendidos.esVacia()) {
            Proceso p = colaSuspendidos.desencolar();
            colaListos.encolar(p);
            log("SWAP-IN: Proceso " + p.getId() + " regresa a RAM.");
            actualizarTabla(tablaSuspendidos, colaSuspendidos);
        }

        // 2. CPU
        if (procesoEnCPU != null) {
            procesoEnCPU.restarTiempo();
            contadorQuantum++;
            
            if (procesoEnCPU.haTerminado()) {
                log("Terminó: " + procesoEnCPU.getId());
                colaTerminados.encolar(procesoEnCPU);
                actualizarTabla(tablaTerminados, colaTerminados);
                procesoEnCPU = null;
                contadorQuantum = 0;
                lblCPU.setText("[LIBRE]");
            } 
            else if (algoritmo == 1 && contadorQuantum >= quantum) {
                log("Quantum RR: " + procesoEnCPU.getId() + " sale de CPU.");
                gestionarIngreso(procesoEnCPU); // Reingresa a cola (o suspendidos)
                procesoEnCPU = null;
                contadorQuantum = 0;
                lblCPU.setText("[CAMBIO]");
            }
            else {
                lblCPU.setText(procesoEnCPU.getId() + " (" + procesoEnCPU.getTiempoRestante() + "s)");
            }
        }
        
        // 3. DESPACHADOR
        if (procesoEnCPU == null && !colaListos.esVacia()) {
            // Aquí llamamos a la lógica que copiaste en la clase Planificador
            procesoEnCPU = planificador.obtenerSiguiente(colaListos, algoritmo); 
            
            contadorQuantum = 0;
            if (procesoEnCPU != null) {
                lblCPU.setText(procesoEnCPU.getId() + " (" + procesoEnCPU.getTiempoRestante() + "s)");
                log("Despachador: Seleccionado " + procesoEnCPU.getId() + " usando algoritmo " + algoritmo);
            }
        }
        
        actualizarTabla(tablaListos, colaListos);
        actualizarTabla(tablaBloqueados, colaBloqueados);
    }

    // --- GESTIÓN DE MEMORIA (Listos vs Suspendidos) ---
    private void gestionarIngreso(Proceso p) {
        // Si la RAM está llena (> MaxMemoria), mandamos a Suspendidos
        if (contarCola(colaListos) >= maxMemoria) {
            colaSuspendidos.encolar(p);
            log("MEMORIA LLENA: " + p.getId() + " va a Suspendidos (Disco).");
            actualizarTabla(tablaSuspendidos, colaSuspendidos);
        } else {
            colaListos.encolar(p);
        }
        actualizarTabla(tablaListos, colaListos);
    }

    public void cargarDesdeArchivo(Cola nuevos) {
        while (!nuevos.esVacia()) {
            gestionarIngreso(nuevos.desencolar());
        }
    }
    
    public void crearProcesosAleatorios(int cantidad) {
        for (int i=0; i<cantidad; i++) {
            contadorID++;
            String id = "R" + contadorID;
            String tipo = (azar.nextBoolean()) ? "Computo" : "E/S";
            int tiempo = azar.nextInt(10) + 2;
            int prio = azar.nextInt(3) + 1;
            Proceso p = new Proceso(id, "Random", tipo, tiempo, prio);
            gestionarIngreso(p);
        }
        log("Creados " + cantidad + " procesos aleatorios.");
    }

    public void bloquearProceso() {
        if (procesoEnCPU != null) {
            log("Bloqueo E/S: " + procesoEnCPU.getId());
            colaBloqueados.encolar(procesoEnCPU);
            procesoEnCPU = null;
            contadorQuantum = 0;
            lblCPU.setText("[LIBRE]");
            actualizarTabla(tablaBloqueados, colaBloqueados);
        }
    }

    public void desbloquearProceso() {
        if (!colaBloqueados.esVacia()) {
            Proceso p = colaBloqueados.desencolar();
            gestionarIngreso(p); // Intenta volver a listos o suspendidos
            log("Termina E/S: " + p.getId());
            actualizarTabla(tablaBloqueados, colaBloqueados);
        }
    }

    private void actualizarTabla(JTable tabla, Cola cola) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        Nodo temp = cola.getInicio();
        while (temp != null) {
            Proceso p = temp.getDato();
            modelo.addRow(new Object[]{p.getId(), p.getNombre(), p.getTiempoRestante(), p.getTipo(), p.getPrioridad()});
            temp = temp.getSiguiente();
        }
    }
    
    private void log(String msg) {
        txtLog.append(msg + "\n");
        txtLog.setCaretPosition(txtLog.getDocument().getLength());
    }
}
