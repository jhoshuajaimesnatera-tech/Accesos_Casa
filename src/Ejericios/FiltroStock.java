package Ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class FiltroStock {
    public static void main(String[] args) {
        Path rutaConfig = Path.of("config.properties");

        try {
            // 1. Cargar la configuración desde el fichero de propiedades
            Properties props = new Properties();
            try (BufferedReader lectorConfig = Files.newBufferedReader(rutaConfig, StandardCharsets.UTF_8)) {
                props.load(lectorConfig);
            }

            String rutaEntradaStr = props.getProperty("fichero.entrada");
            String rutaSalidaStr = props.getProperty("fichero.salida");
            int stockMinimo = Integer.parseInt(props.getProperty("stock.minimo", "5"));

            Path rutaEntrada = Path.of(rutaEntradaStr);
            Path rutaSalida = Path.of(rutaSalidaStr);

            // 2. Asegurarse de que la carpeta de salida existe
            if (rutaSalida.getParent() != null) {
                Files.createDirectories(rutaSalida.getParent());
            }

            // 3. Leer el inventario y filtrar los productos con stock bajo
            // Usamos un BufferedWriter para escribir el nuevo fichero de alertas
            try (BufferedReader lectorCsv = Files.newBufferedReader(rutaEntrada, StandardCharsets.UTF_8);
                 BufferedWriter escritorAlerta = Files.newBufferedWriter(rutaSalida, StandardCharsets.UTF_8)) {

                escritorAlerta.write("--- PRODUCTOS CON STOCK BAJO (Menor a " + stockMinimo + ") ---\n");
                
                String linea;
                boolean primeraLinea = true; // Para saltarnos la cabecera (ID,Producto...)

                while ((linea = lectorCsv.readLine()) != null) {
                    if (primeraLinea) {
                        primeraLinea = false;
                        continue; // Saltamos la cabecera
                    }

                    // Separamos los datos por comas (ID, Producto, Stock, Precio)
                    String[] partes = linea.split(",");
                    String nombreProducto = partes[1];
                    int stock = Integer.parseInt(partes[2]);

                    // Si el stock es menor al mínimo, lo escribimos en el fichero de alerta
                    if (stock < stockMinimo) {
                        String alerta = "¡Alerta! " + nombreProducto + " tiene un stock crítico de: " + stock + " unidades.\n";
                        escritorAlerta.write(alerta);
                        System.out.print("Detectado: " + alerta);
                    }
                }
            }

            System.out.println("\nInforme generado con éxito en: " + rutaSalida.toAbsolutePath());

        } catch (Exception e) {
            System.err.println("Error procesando los ficheros: " + e.getMessage());
        }
    }
}