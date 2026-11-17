package Tema2_Ficheros_Propiedesdes;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class LeerRecursoEmpaquetado {
    public static void main(String[] args) {
        Properties config = new Properties();

        // La barra inicial "/" busca desde la raíz del empaquetado de recursos[cite: 106]
        try (InputStream in = LeerRecursoEmpaquetado.class.getResourceAsStream("/config-por-omision.properties")) {
            
            if (in == null) {
                System.out.println("El recurso no se encuentra en el empaquetado.");
                return;
            }

            try (Reader lector = new InputStreamReader(in, StandardCharsets.UTF_8)) {
                config.load(lector);
                System.out.println("¡Configuración interna cargada con éxito!");
                System.out.println("Idioma por defecto: " + config.getProperty("app.idioma"));
            }

        } catch (Exception e) {
            System.err.println("Error procesando el recurso: " + e.getMessage());
        }
    }
}
