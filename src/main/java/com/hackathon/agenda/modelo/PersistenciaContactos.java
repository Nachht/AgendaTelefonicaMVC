package com.hackathon.agenda.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PersistenciaContactos {

    public static final String ARCHIVO = "contactos.txt";

    public static void guardar(ArrayList<Contacto> contactos) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Contacto contacto : contactos) {
                writer.println(contacto.getNombre() + ";" + contacto.getApellido() + ";" + contacto.getTelefono());
            }
        }
    }

    public static ArrayList<Contacto> cargar() throws IOException {
        ArrayList<Contacto> contactos = new ArrayList<>();
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            return contactos;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) {
                    continue;
                }

                String[] partes = linea.split(";", 3);
                if (partes.length == 3) {
                    contactos.add(new Contacto(partes[0], partes[1], partes[2]));
                }
            }
        }

        return contactos;
    }
}
