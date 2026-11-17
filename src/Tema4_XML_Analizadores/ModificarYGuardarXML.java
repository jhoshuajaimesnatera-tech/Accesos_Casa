package Tema4_XML_Analizadores;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedOutputStream;
import java.io.OutputStream;

public class ModificarYGuardarXML {
    public static void main(String[] args) {
        try {
            Path origen = Path.of("centro.xml");
            Path destino = Path.of("centro_modificado.xml");

            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            Document documento = fabrica.newDocumentBuilder().parse(origen.toFile());

            // 1. Crear un nuevo elemento en memoria e introducirle texto
            Element nuevoCorreo = documento.createElement("correo");
            nuevoCorreo.setTextContent("nuevo.alumno@example.org");

            // 2. Buscamos dónde insertarlo (ej: en el primer alumno que encontremos)
            Element primerAlumno = (Element) documento.getElementsByTagName("alumno").item(0);
            primerAlumno.appendChild(nuevoCorreo);

            // 3. Volcar el árbol DOM modificado de vuelta al disco usando un Transformator[cite: 127]
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.setOutputProperty(OutputKeys.INDENT, "yes");

            try (OutputStream salida = new BufferedOutputStream(Files.newOutputStream(destino))) {
                t.transform(new DOMSource(documento), new StreamResult(salida));
                System.out.println("¡XML modificado y guardado con éxito en disco!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
