package com.hackathon.agenda.modelo;

import java.util.ArrayList;

public class Agenda {


    private ArrayList<Contacto> contactos;
    private int tamanoMaximo;


    public Agenda() {
        tamanoMaximo = 10;
        contactos = new ArrayList<>();
    }


    public Agenda(int tamanoMaximo) {
        this.tamanoMaximo = tamanoMaximo;
        contactos = new ArrayList<>();
    }


    public boolean agendaLlena() {

    }


    public int espaciosLibres() {

    }


    public boolean existeContacto(Contacto contacto) {

    }


    public void añadirContacto(Contacto contacto) {

    }


    public void listarContactos() {

    }


    public Contacto buscarContacto(String nombre, String apellido) {

    }


    public void eliminarContacto(Contacto contacto) {

    }


    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono) {

    }

}