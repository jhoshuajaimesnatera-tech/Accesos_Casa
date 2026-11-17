package Tema3_Serializacion;

public class ProbarTransient {
    public static void main(String[] args) {
        ConexionOculta c = new ConexionOculta();
        System.out.println("Antes de serializar:");
        c.mostrarDatos();
        // Al serializar y deserializar este objeto, passwordSecreta valdrá null.
    }
}
