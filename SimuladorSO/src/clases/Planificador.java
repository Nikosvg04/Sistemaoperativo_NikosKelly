package clases;

public class Planificador {

    public Planificador() {}

    public Proceso obtenerSiguiente(Cola listaListos, int tipoAlgoritmo) {
        if (listaListos.esVacia()) return null;

        // 0 = FCFS / RR (Saca el primero que llegó)
        if (tipoAlgoritmo == 0 || tipoAlgoritmo == 1) {
            return listaListos.desencolar();
        }

        // 2 = PRIORIDAD (Busca el de menor número de prioridad: 1 es Alta)
        if (tipoAlgoritmo == 2) {
            return extraerPorPrioridad(listaListos);
        }

        // 3 = SRT (Shortest Remaining Time: Busca el de menor tiempo restante)
        if (tipoAlgoritmo == 3) {
            return extraerPorSRT(listaListos);
        }

        return listaListos.desencolar();
    }

    private Proceso extraerPorPrioridad(Cola lista) {
        Nodo temp = lista.getInicio();
        Proceso mejor = temp.getDato();
        
        // Buscamos el de mayor prioridad (prioridad 1)
        while (temp != null) {
            if (temp.getDato().getPrioridad() < mejor.getPrioridad()) {
                mejor = temp.getDato();
            }
            temp = temp.getSiguiente();
        }
        return extraerEspecifico(lista, mejor);
    }

    private Proceso extraerPorSRT(Cola lista) {
        Nodo temp = lista.getInicio();
        Proceso mejor = temp.getDato();
        
        while (temp != null) {
            if (temp.getDato().getTiempoRestante() < mejor.getTiempoRestante()) {
                mejor = temp.getDato();
            }
            temp = temp.getSiguiente();
        }
        return extraerEspecifico(lista, mejor);
    }

    // Método auxiliar para sacar un nodo que no está al principio
    private Proceso extraerEspecifico(Cola lista, Proceso objetivo) {
        Cola aux = new Cola();
        Proceso retornado = null;
        while (!lista.esVacia()) {
            Proceso p = lista.desencolar();
            if (p.getId().equals(objetivo.getId())) {
                retornado = p;
            } else {
                aux.encolar(p);
            }
        }
        while (!aux.esVacia()) { lista.encolar(aux.desencolar()); }
        return retornado;
    }
}
