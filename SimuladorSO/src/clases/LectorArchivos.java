package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivos {
    
    // Este método lee el archivo y devuelve una lista de procesos
    public List<String[]> leerCSV(String ruta) {
        List<String[]> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Separamos por comas: Nombre, Tipo, Tiempo, Prioridad, Deadline
                String[] datos = linea.split(",");
                lineas.add(datos);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return lineas;
    }
}