package modelo;

public class Cita {
    public int idCita;
    public String fechaHora;
    public String motivo;
    public Doctor doctor;
    public Paciente paciente;

    public Cita(int idCita, String fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }
}