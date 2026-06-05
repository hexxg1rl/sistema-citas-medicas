package control;

import modelo.*;
import util.ArchivoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorConsultorio {
    private List<Doctor> listaDoctores;
    private List<Paciente> listaPacientes;
    private List<Cita> listaCitas;
    private int contadorDoctor = 1;
    private int contadorPaciente = 1;
    private int contadorCita = 1;
    private Scanner scanner;

    public GestorConsultorio() {
        listaDoctores = new ArrayList<>();
        listaPacientes = new ArrayList<>();
        listaCitas = new ArrayList<>();
        scanner = new Scanner(System.in);
        cargarDatos();
    }

    private void cargarDatos() {
        try {
            List<String> lineas = ArchivoUtil.leerArchivo("doctores.csv");
            for (String linea : lineas) {
                String[] datos = linea.split(";");
                if (datos.length == 3) {
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String especialidad = datos[2];
                    listaDoctores.add(new Doctor(id, nombre, especialidad));
                    if (id >= contadorDoctor) contadorDoctor = id + 1;
                }
            }

            lineas = ArchivoUtil.leerArchivo("pacientes.csv");
            for (String linea : lineas) {
                String[] datos = linea.split(";");
                if (datos.length == 3) {
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String telefono = datos[2];
                    listaPacientes.add(new Paciente(id, nombre, telefono));
                    if (id >= contadorPaciente) contadorPaciente = id + 1;
                }
            }

            // Nota: La carga de citas es más compleja por las relaciones,
            // por simplicidad en este avance, se cargarán doctores y pacientes.
            // Las citas se manejarán en memoria o se puede expandir luego.

        } catch (Exception e) {
            System.out.println("No se pudieron cargar datos previos o es la primera vez.");
        }
    }

    private void guardarDoctores() {
        List<String> lineas = new ArrayList<>();
        for (Doctor d : listaDoctores) {
            lineas.add(d.id + ";" + d.nombre + ";" + d.especialidad);
        }
        ArchivoUtil.guardarEnArchivo("doctores.csv", lineas);
    }

    private void guardarPacientes() {
        List<String> lineas = new ArrayList<>();
        for (Paciente p : listaPacientes) {
            lineas.add(p.id + ";" + p.nombre + ";" + p.telefono);
        }
        ArchivoUtil.guardarEnArchivo("pacientes.csv", lineas);
    }

    public void ejecutar() {
        Administrador admin = new Administrador();
        boolean sesionActiva = false;

        // LOGIN
        System.out.println("=== SISTEMA DE CITAS CLÍNICAS ===");
        while (!sesionActiva) {
            System.out.print("Usuario: ");
            String user = scanner.nextLine();
            System.out.print("Contraseña: ");
            String pass = scanner.nextLine();

            if (admin.login(user, pass)) {
                sesionActiva = true;
                System.out.println("\n¡Bienvenido al sistema!");
            } else {
                System.out.println("\nError: Credenciales incorrectas. Intente de nuevo.");
            }
        }

        // MENÚ PRINCIPAL
        int opcion = 0;
        while (opcion != 4) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestionar Doctores");
            System.out.println("2. Gestionar Pacientes");
            System.out.println("3. Gestionar Citas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1: menuDoctores(); break;
                case 2: menuPacientes(); break;
                case 3: menuCitas(); break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    admin.logout();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void menuDoctores() {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("\n--- GESTIÓN DE DOCTORES ---");
            System.out.println("1. Agregar Doctor");
            System.out.println("2. Ver Lista de Doctores");
            System.out.println("3. Regresar");
            System.out.print("Seleccione: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Nombre completo: ");
                    String nombreDoc = scanner.nextLine();
                    System.out.print("Especialidad: ");
                    String esp = scanner.nextLine();
                    listaDoctores.add(new Doctor(contadorDoctor++, nombreDoc, esp));
                    guardarDoctores();
                    System.out.println("Doctor agregado.");
                    break;
                case 2:
                    if (listaDoctores.isEmpty()) {
                        System.out.println("No hay doctores registrados.");
                    } else {
                        for (Doctor d : listaDoctores) {
                            System.out.println(d.toString());
                        }
                    }
                    break;
            }
        }
    }

    private void menuPacientes() {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("\n--- GESTIÓN DE PACIENTES ---");
            System.out.println("1. Agregar Paciente");
            System.out.println("2. Ver Lista de Pacientes");
            System.out.println("3. Regresar");
            System.out.print("Seleccione: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Nombre completo: ");
                    String nombrePac = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String tel = scanner.nextLine();
                    listaPacientes.add(new Paciente(contadorPaciente++, nombrePac, tel));
                    guardarPacientes();
                    System.out.println("Paciente agregado.");
                    break;
                case 2:
                    if (listaPacientes.isEmpty()) {
                        System.out.println("No hay pacientes registrados.");
                    } else {
                        for (Paciente p : listaPacientes) {
                            System.out.println(p.toString());
                        }
                    }
                    break;
            }
        }
    }

    private void menuCitas() {
        int opcion = 0;
        while (opcion != 4) {
            System.out.println("\n--- GESTIÓN DE CITAS ---");
            System.out.println("1. Crear Cita");
            System.out.println("2. Ver Citas");
            System.out.println("3. Ver Doctores Disponibles");
            System.out.println("4. Regresar");
            System.out.print("Seleccione: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    if (listaDoctores.isEmpty() || listaPacientes.isEmpty()) {
                        System.out.println("Error: Debe haber al menos un doctor y un paciente registrados.");
                        break;
                    }

                    System.out.println("Seleccione Doctor (ID):");
                    for (Doctor d : listaDoctores) System.out.println(d);
                    int idDoc = Integer.parseInt(scanner.nextLine());
                    Doctor docSel = null;
                    for (Doctor d : listaDoctores) if (d.id == idDoc) docSel = d;

                    if (docSel != null) {
                        System.out.println("Seleccione Paciente (ID):");
                        for (Paciente p : listaPacientes) System.out.println(p);
                        int idPac = Integer.parseInt(scanner.nextLine());
                        Paciente pacSel = null;
                        for (Paciente p : listaPacientes) if (p.id == idPac) pacSel = p;

                        if (pacSel != null) {
                            System.out.print("Fecha y Hora (YYYY-MM-DD HH:MM): ");
                            String fecha = scanner.nextLine();
                            System.out.print("Motivo: ");
                            String motivo = scanner.nextLine();

                            listaCitas.add(new Cita(contadorCita++, fecha, motivo, docSel, pacSel));
                            System.out.println("Cita creada con éxito.");
                        } else {
                            System.out.println("Paciente no encontrado.");
                        }
                    } else {
                        System.out.println("Doctor no encontrado.");
                    }
                    break;
                case 2:
                    if (listaCitas.isEmpty()) {
                        System.out.println("No hay citas agendadas.");
                    } else {
                        for (Cita c : listaCitas) {
                            System.out.println(c.toString());
                        }
                    }
                    break;
                case 3:
                    for (Doctor d : listaDoctores) System.out.println(d);
                    break;
            }
        }
    }
}
