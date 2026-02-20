package clases;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.util.Random;

public class Administrador {

    // --- VARIABLES DE LA INTERFAZ (VISUAL) ---
    private JLabel lblCPU;
    private JList listaVisualListos;     // Referencia a la lista gris de la izquierda
    private JList listaVisualBloqueados; // Referencia a la lista gris de la derecha

    // --- VARIABLES LÓGICAS (TUS ESTRUCTURAS DE DATOS) ---
    private Cola colaListos;      // Tu clase Cola
    private Cola colaBloqueados;  // Tu clase Cola
    private Proceso procesoEnCPU; // Tu clase Proceso

    // --- OTRAS VARIABLES ---
    private int contadorProcesos;
    private Random azar;

    // --- CONSTRUCTOR ---
    public Administrador(JLabel lblCPU, JList listaListos, JList listaBloqueados) {
        // 1. Guardamos las referencias visuales
        this.lblCPU = lblCPU;
        this.listaVisualListos = listaListos;
        this.listaVisualBloqueados = listaBloqueados;

        // 2. Inicializamos las colas lógicas (VACÍAS AL INICIO)
        this.colaListos = new Cola();
        this.colaBloqueados = new Cola();

        // 3. Inicializamos variables de control
        this.procesoEnCPU = null;
        this.contadorProcesos = 1; // Empezamos en P1
        this.azar = new Random();
    }

    // --- MÉTODO 1: CREAR PROCESO ---
    public void crearProcesoManual() {
        int tiempo = azar.nextInt(8) + 3; // Tiempo entre 3 y 10 segundos
        String nombre = "P" + contadorProcesos;

        Proceso nuevoProceso = new Proceso(nombre, tiempo);

        // A. Lógica: Lo metemos en la cola de datos
        colaListos.encolar(nuevoProceso);

        // B. Visual: Actualizamos la lista de la izquierda
        actualizarLista(listaVisualListos, colaListos);

        contadorProcesos++;
        System.out.println("Creado: " + nombre);
    }

    // --- MÉTODO 2: GESTIONAR CPU (El Reloj llama a esto) ---
    public void gestionarCPU() {
        // CASO A: La CPU está vacía
        if (procesoEnCPU == null) {
            // Verificamos si hay alguien esperando en la cola de listos
            if (!colaListos.esVacia()) {
                // Sacamos el primero de la cola lógica
                procesoEnCPU = colaListos.desencolar();
                
                // Actualizamos la lista visual (porque sacamos uno)
                actualizarLista(listaVisualListos, colaListos);
                
                // Mostramos en CPU
                lblCPU.setText(procesoEnCPU.getNombre() + " (" + procesoEnCPU.getTiempoRestante() + "s)");
            } else {
                lblCPU.setText("[Vacio]");
            }
        } 
        // CASO B: Hay un proceso en CPU
        else {
            procesoEnCPU.restarTiempo();
            
            // Si el tiempo se acabó
            if (procesoEnCPU.getTiempoRestante() <= 0) {
                System.out.println("Terminó proceso: " + procesoEnCPU.getNombre());
                procesoEnCPU = null;
                lblCPU.setText("[Vacio]");
                
                // Intentamos meter el siguiente inmediatamente
                gestionarCPU(); 
            } else {
                // Si no acabó, solo actualizamos el texto
                lblCPU.setText(procesoEnCPU.getNombre() + " (" + procesoEnCPU.getTiempoRestante() + "s)");
            }
        }
    }

    // --- MÉTODO 3: BLOQUEAR (Interrupción E/S) ---
    public void bloquearProceso() {
        if (this.procesoEnCPU != null) {
            // A. Lógica: Lo metemos en la Cola de Bloqueados
            colaBloqueados.encolar(procesoEnCPU);
            
            // B. Visual: Actualizamos la lista derecha
            actualizarLista(listaVisualBloqueados, colaBloqueados);
            
            // C. Liberamos la CPU
            this.procesoEnCPU = null;
            lblCPU.setText("[Vacio]");
            
            System.out.println("Proceso bloqueado por E/S.");
        }
    }

    // --- MÉTODO 4: DESBLOQUEAR (Terminar E/S) ---
    public void desbloquearProceso() {
        if (!colaBloqueados.esVacia()) {
            // A. Sacamos de bloqueados
            Proceso p = colaBloqueados.desencolar();
            
            // B. Metemos en listos
            colaListos.encolar(p);
            
            // C. Actualizamos AMBAS listas visualmente
            actualizarLista(listaVisualBloqueados, colaBloqueados);
            actualizarLista(listaVisualListos, colaListos);
            
            System.out.println("Proceso desbloqueado: " + p.getNombre());
        }
    }

    // --- MÉTODO AUXILIAR PARA PINTAR LAS LISTAS ---
    // Este método lee tu Cola (Nodo a Nodo) y llena el JList
    private void actualizarLista(JList listaVisual, Cola colaLogica) {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        
        // Verificamos que la cola no esté vacía para evitar errores
        if (!colaLogica.esVacia()) {
            Nodo temp = colaLogica.getInicio();
            while (temp != null) {
                // Agregamos el texto "P1 (5s)" a la lista
                modelo.addElement(temp.getDato().getNombre() + " (" + temp.getDato().getTiempoRestante() + "s)");
                temp = temp.getSiguiente();
            }
        }
        
        listaVisual.setModel(modelo);
    }
}