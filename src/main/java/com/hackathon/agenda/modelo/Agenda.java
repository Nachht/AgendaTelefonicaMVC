package com.hackathon.agenda.modelo;

import java.util.ArrayList;

public class Agenda {

    private ArrayList<Contacto> contactos;
    private int tamanoMaximo;

    // Constructor por defecto
    public Agenda() {
        this.tamanoMaximo = 10;
        this.contactos = new ArrayList<>();
    }

    // Constructor con tamaño personalizado
    public Agenda(int tamanoMaximo) {
        this.tamanoMaximo = tamanoMaximo;
        this.contactos = new ArrayList<>();
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

        for (Contacto contacto : contactos) {

            if (contacto.getNombre().equalsIgnoreCase(nombre)
                    && contacto.getApellido().equalsIgnoreCase(apellido)) {

                return contacto;
            }
        }

        return null;
    }

    public void eliminarContacto(Contacto contacto) {

        if (!contactos.remove(contacto)) {
            System.out.println("El contacto no existe.");
        }
    }

    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono) {

        Contacto contacto = buscarContacto(nombre, apellido);

        if (contacto != null) {
            contacto.setTelefono(nuevoTelefono);
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }
    public ArrayList<Contacto> getContactos() {
        return contactos;
    }
}