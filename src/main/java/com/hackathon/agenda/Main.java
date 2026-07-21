package com.hackathon.agenda;

import com.hackathon.agenda.controlador.AgendaControlador;
import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.vista.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        VentanaPrincipal ventana = new VentanaPrincipal();
        AgendaControlador agendaControlador = new AgendaControlador(agenda, ventana);
        ventana.setVisible(true);

    }
}