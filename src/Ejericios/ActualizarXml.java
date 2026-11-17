package Ejercicios;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.nio.file.Path;

public class ActualizarXml {
    public static void main(String[] args) {
        try {
            Path origen = Path.of("alumnos.xml");
            Path destino = Path.of("alumnos_modificados.xml");

            // 1. Cargar el XML en memoria de forma segura
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            fabrica.setNamespaceAware(true);
            fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            
            Document documento = fabrica.newDocumentBuilder().parse(origen.toFile());

            // 2. Buscar al alumno que queremos modificar (Carlos)
            NodeList listaAlumnos = documento.getElementsByTagName("alumno");
            boolean modificado = false;

            for (int i = 0; i < listaAlumnos.getLength(); i++) {
                Element alumno = (Element) listaAlumnos.item(i);
                
                String nombre = alumno.getElementsByTagName("nombre").item(0).getTextContent();
                
                if ("Carlos".equals(nombre)) {
                    // Encontramos a Carlos: cambiamos su nota
                    Node nodoNota = alumno.getElementsByTagName("nota").item(0);
                    nodoNota.setTextContent("6.0");
                    modificado = true;
                    System.out.println("¡Nota de Carlos actualizada con éxito!");
                    break;
                }
            }

            if (!modificado) {
                System.out.println("No se encontró al alumno para modificar.");
                return;
            }

            // 3. Volcar el árbol DOM modificado de vuelta al disco usando un Transformer
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformador = tf.newTransformer();
            
            // Propiedades para que el XML guardado sea legible (con saltos de línea y UTF-8)
            transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");

            // Realizar la transformación guardando en el fichero de destino
            DOMSource fuente = new DOMSource(documento);
            StreamResult resultado = StreamResult.create(destino.toFile());
            
            transformador.transform(fuente, resultado);
            System.out.println("Fichero guardado correctamente en: " + destino.toAbsolutePath());

        } catch (Exception e) {
            System.err.println("Error al modificar el XML: " + e.getMessage());
        }
    }
}