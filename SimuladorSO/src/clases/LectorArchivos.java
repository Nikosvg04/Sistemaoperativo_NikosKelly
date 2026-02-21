package clases;

import java.io.BufferedReader;
import java.io.FileReader;

public class LectorArchivos {
    
    public Cola leerCSV(String ruta) {
        Cola colaResultado = new Cola();
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            // Leer línea por línea
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                
                // Formato CSV esperado: ID,Nombre,Tipo,Tiempo,Prioridad
                String[] datos = linea.split(",");
                
                if (datos.length >= 5) {
                    try {
                        String id = datos[0].trim();
                        String nombre = datos[1].trim();
                        String tipo = datos[2].trim();
                        int tiempo = Integer.parseInt(datos[3].trim());
                        int prioridad = Integer.parseInt(datos[4].trim());
                        
                        Proceso p = new Proceso(id, nombre, tipo, tiempo, prioridad);
                        colaResultado.encolar(p);
                        
                    } catch (Exception e) {
                        System.out.println("Error en linea CSV: " + linea);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
        }
        return colaResultado;
    }
}