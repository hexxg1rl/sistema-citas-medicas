package modelo;

public class Doctor extends Persona {
    public String especialidad;

    public Doctor(int id, String nombre, String especialidad) {
        super(id, nombre);
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + id + " | Nombre: " + nombre + " | Especialidad: " + especialidad;
    }
}