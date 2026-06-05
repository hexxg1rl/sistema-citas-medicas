package util;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoUtil {
    private static final String DB_FOLDER = "db";

    public static void crearCarpetaDB() {
        Path path = Paths.get(DB_FOLDER);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                System.err.println("Error al crear carpeta db: " + e.getMessage());
            }
        }
    }

    public static void guardarEnArchivo(String nombreArchivo, List<String> lineas) {
        try {
            crearCarpetaDB();
            Files.write(Paths.get(DB_FOLDER, nombreArchivo), lineas);
        } catch (IOException e) {
            System.err.println("Error al guardar en " + nombreArchivo + ": " + e.getMessage());
        }
    }

    public static List<String> leerArchivo(String nombreArchivo) {
        try {
            crearCarpetaDB();
            return Files.readAllLines(Paths.get(DB_FOLDER, nombreArchivo));
        } catch (IOException e) {
            // Si no existe el archivo, regresamos lista vacía
            return new ArrayList<>();
        }
    }
}
