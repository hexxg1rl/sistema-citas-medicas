import control.GestorConsultorio;

public class Main {
    public static void main(String[] args) {
        try {
            GestorConsultorio sistema = new GestorConsultorio();
            sistema.ejecutar();
        } catch (Exception e) {
            System.err.println("Error al iniciar el sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
}