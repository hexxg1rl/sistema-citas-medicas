package modelo;

public class Paciente extends Persona {
    public String telefono;

    public Paciente(int id, String nombre, String telefono) {
        super(id, nombre);
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Paciente ID: " + id + " | Nombre: " + nombre + " | Teléfono: " + telefono;
    }
}
