package Ejericios;

import java.nio.file.Path;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class CineControl {
    public static void main(String[] args) {
        Path fichero = Path.of("app.properties");

        try {
            // 1. Leer el fichero de propiedades usando UTF-8
            Properties props = new Properties();
            try (var reader = Files.newBufferedReader(fichero, StandardCharsets.UTF_8)) {
                props.load(reader);
            }

            // 2. Obtener la ruta del log del fichero de propiedades
            String rutaLogStr = props.getProperty("ruta.logs", "logs/default.log");
            Path rutaLog = Path.of(rutaLogStr);

            // 3. Crear la carpeta contenedora del log si no existe (¡Aquí sí se usa!)
            if (rutaLog.getParent() != null) {
                Files.createDirectories(rutaLog.getParent());
            }

            System.out.println("¡Carpeta de logs lista y propiedades leídas correctamente!");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}