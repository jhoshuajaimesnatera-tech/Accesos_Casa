package Tema2_Ficheros_Propiedesdes;

import java.util.Properties;
import java.io.StringReader;

public class PropiedadesPorOmision {
    public static void main(String[] args) {
        Properties config = new Properties();
        
        String simulacionFichero = "bd.url=jdbc:postgresql://localhost/cine\n";
        
        try {
            config.load(new StringReader(simulacionFichero));

            // 1. Propiedad que SÍ existe en el fichero
            String url = config.getProperty("bd.url", "jdbc:postgresql://localhost/defecto");
            
            // 2. Propiedad que NO existe: devolverá automáticamente el segundo parámetro (el valor por omisión)[cite: 104]
            int puerto = Integer.parseInt(config.getProperty("bd.puerto", "5432"));

            System.out.println("URL: " + url);
            System.out.println("Puerto (usó valor por defecto): " + puerto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
