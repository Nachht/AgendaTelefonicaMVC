package com.hackathon.agenda.modelo;

import java.io.IOException;
import java.util.ArrayList;

public class Agenda {

    private ArrayList<Contacto> contactos;
    private int tamanoMaximo;

    // Constructor por defecto
    public Agenda() {
        this.tamanoMaximo = 10;
        this.contactos = new ArrayList<>();
        cargarDesdeArchivo();
    }

    // Constructor con tamaño personalizado
    public Agenda(int tamanoMaximo) {
        this.tamanoMaximo = tamanoMaximo;
        this.contactos = new ArrayList<>();
        cargarDesdeArchivo();
    }

    private void cargarDesdeArchivo() {
        try {
            contactos = PersistenciaContactos.cargar();
        } catch (IOException e) {
            throw new IllegalStateException("No se pudieron cargar los contactos desde el archivo.");
        }
    }

    private void guardarEnArchivo() {
        try {
            PersistenciaContactos.guardar(contactos);
        } catch (IOException e) {
            throw new IllegalStateException("No se pudieron guardar los contactos en el archivo.");
        }
    }
    public boolean agendaLlena() {
        return contactos.size() >= tamanoMaximo;
    }

    public int espaciosLibres() {
        return tamanoMaximo - contactos.size();
    }

    public boolean existeContacto(Contacto contacto) {
        return contactos.contains(contacto);
    }

    public void anadirContacto(Contacto contacto) {

        if (agendaLlena()) {
            throw new IllegalStateException("La agenda está llena.");
        }

        if (existeContacto(contacto)) {
            throw new IllegalArgumentException("El contacto ya existe.");
        }

        contactos.add(contacto);
        guardarEnArchivo();
    }

    public void listarContactos() {

        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }

        for (Contacto contacto : contactos) {
            System.out.println(contacto);
        }
    }

    public Contacto buscarContacto(String nombre, String apellido) {
        String nombreBusqueda = nombre != null ? nombre.trim() : "";
        String apellidoBusqueda = apellido != null ? apellido.trim() : "";

        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombreBusqueda)
                    && contacto.getApellido().equalsIgnoreCase(apellidoBusqueda)) {
                return contacto;
            }
        }

        return null;
    }

    public ArrayList<Contacto> buscarContactos(String nombre, String apellido, String telefono) {
        String nombreBusqueda = nombre != null ? nombre.trim() : "";
        String apellidoBusqueda = apellido != null ? apellido.trim() : "";
        String telefonoBusqueda = telefono != null ? telefono.trim() : "";

        ArrayList<Contacto> resultados = new ArrayList<>();

        for (Contacto contacto : contactos) {
            boolean coincide = true;

            if (!nombreBusqueda.isEmpty()
                    && !contacto.getNombre().equalsIgnoreCase(nombreBusqueda)) {
                coincide = false;
            }
            if (!apellidoBusqueda.isEmpty()
                    && !contacto.getApellido().equalsIgnoreCase(apellidoBusqueda)) {
                coincide = false;
            }
            if (!telefonoBusqueda.isEmpty()
                    && !contacto.getTelefono().equals(telefonoBusqueda)) {
                coincide = false;
            }

            if (coincide) {
                resultados.add(contacto);
            }
        }

        return resultados;
    }

    public void eliminarContacto(Contacto contacto) {

        if (!contactos.remove(contacto)) {
            throw new IllegalArgumentException("El contacto no existe.");
        }

        guardarEnArchivo();
    }

    public Contacto buscarPorNombreYTelefono(String nombre, String telefono) {
        String nombreBusqueda = nombre != null ? nombre.trim() : "";
        String telefonoBusqueda = telefono != null ? telefono.trim() : "";

        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombreBusqueda)
                    && contacto.getTelefono().equals(telefonoBusqueda)) {
                return contacto;
            }
        }

        return null;
    }

    public Contacto buscarPorApellidoYTelefono(String apellido, String telefono) {
        String apellidoBusqueda = apellido != null ? apellido.trim() : "";
        String telefonoBusqueda = telefono != null ? telefono.trim() : "";

        for (Contacto contacto : contactos) {
            if (contacto.getApellido().equalsIgnoreCase(apellidoBusqueda)
                    && contacto.getTelefono().equals(telefonoBusqueda)) {
                return contacto;
            }
        }

        return null;
    }

    public void modificarContacto(String nombre, String apellido, String telefono) {
        String nombreIngresado = nombre != null ? nombre.trim() : "";
        String apellidoIngresado = apellido != null ? apellido.trim() : "";
        String telefonoIngresado = telefono != null ? telefono.trim() : "";

        if (nombreIngresado.isEmpty() || apellidoIngresado.isEmpty() || telefonoIngresado.isEmpty()) {
            throw new IllegalArgumentException(
                    "Ingrese los 3 campos: 2 para identificar el contacto y 1 con el dato nuevo.");
        }

        Contacto contacto = buscarContacto(nombreIngresado, apellidoIngresado);
        if (contacto != null && !telefonoIngresado.equals(contacto.getTelefono())) {
            contacto.setTelefono(telefonoIngresado);
            guardarEnArchivo();
            return;
        }

        contacto = buscarPorNombreYTelefono(nombreIngresado, telefonoIngresado);
        if (contacto != null && !apellidoIngresado.equalsIgnoreCase(contacto.getApellido())) {
            contacto.setApellido(apellidoIngresado);
            guardarEnArchivo();
            return;
        }

        contacto = buscarPorApellidoYTelefono(apellidoIngresado, telefonoIngresado);
        if (contacto != null && !nombreIngresado.equalsIgnoreCase(contacto.getNombre())) {
            contacto.setNombre(nombreIngresado);
            guardarEnArchivo();
            return;
        }

        throw new IllegalArgumentException("Contacto no encontrado o el dato nuevo es igual al actual.");
    }
    public ArrayList<Contacto> getContactos() {
        return contactos;
    }
}