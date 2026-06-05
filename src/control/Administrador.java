package control;

public class Administrador {
    private String usuario;
    private String contrasena;

    public Administrador() {
        this.usuario = "admin";
        this.contrasena = "1234";
    }

    public boolean login(String usuarioIngresado, String contrasenaIngresada) {
        return usuarioIngresado.equals(this.usuario) &&
                contrasenaIngresada.equals(this.contrasena);
    }

    public void logout() {
        System.out.println("\nSesión cerrada correctamente.");
    }
}