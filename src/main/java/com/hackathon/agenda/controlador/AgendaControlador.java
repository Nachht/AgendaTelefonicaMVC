package com.hackathon.agenda.controlador;

import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.modelo.Contacto;
import com.hackathon.agenda.vista.VentanaPrincipal;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class AgendaControlador {
    private Agenda agenda;
    private VentanaPrincipal ventana;

    public AgendaControlador(Agenda agenda, VentanaPrincipal ventana) {
        this.agenda = agenda;
        this.ventana = ventana;

        configurarEventos();
        actualizarTabla();
    }
     private void configurarEventos(){
        ventana.getBtnAgregar().addActionListener(e -> agregarContacto() );
         ventana.getBtnBuscar().addActionListener(e -> buscarContacto());
         ventana.getBtnModificar().addActionListener(e -> modificarContacto());
         ventana.getBtnEliminar().addActionListener(e -> eliminarContacto());

     }
    private void agregarContacto() {
        try {
            String nombre = ventana.getTxtNombre().getText();
            String apellido = ventana.getTxtApellido().getText();
            String telefono = ventana.getTxtTelefono().getText();
            Contacto contacto = new Contacto(nombre, apellido, telefono);

            agenda.anadirContacto(contacto);

            actualizarTabla();
            limpiarCampos();

            ventana.getLblEstado().setText("Estado: Contacto agregado correctamente");

        } catch (Exception e) {
            ventana.mostrarError(e.getMessage());
            ventana.getLblEstado().setText("Estado: Error al agregar contacto");
        }
    }

    private void buscarContacto() {
        try {
            String nombre = ventana.getTxtNombre().getText().trim();
            String apellido = ventana.getTxtApellido().getText().trim();
            String telefono = ventana.getTxtTelefono().getText().trim();

            if (nombre.isEmpty() && apellido.isEmpty() && telefono.isEmpty()) {
                actualizarTabla();
                ventana.getLblEstado().setText("Estado: Mostrando todos los contactos");
                return;
            }

            ArrayList<Contacto> resultados = agenda.buscarContactos(nombre, apellido, telefono);

            if (resultados.isEmpty()) {
                ventana.mostrarError("No se encontraron contactos.");
                ventana.getLblEstado().setText("Estado: Búsqueda sin resultados");
            } else {
                mostrarContactosEnTabla(resultados);
                ventana.getLblEstado().setText("Estado: " + resultados.size() + " contacto(s) encontrado(s)");
            }
        } catch (Exception e) {
            ventana.mostrarError(e.getMessage());
        }
    }
    private void modificarContacto() {
        try {
            String nombre = ventana.getTxtNombre().getText();
            String apellido = ventana.getTxtApellido().getText();
            String telefono = ventana.getTxtTelefono().getText();

            agenda.modificarContacto(nombre, apellido, telefono);
            actualizarTabla();
            limpiarCampos();
            ventana.getLblEstado().setText("Estado: Contacto modificado correctamente");

        } catch (Exception e) {
            ventana.mostrarError(e.getMessage());
            ventana.getLblEstado().setText("Estado: Error al modificar contacto");
        }
    }
    private void eliminarContacto() {
        try {
            String nombre = ventana.getTxtNombre().getText();
            String apellido = ventana.getTxtApellido().getText();
            Contacto contacto = agenda.buscarContacto(nombre, apellido);
            if (contacto != null) {
                agenda.eliminarContacto(contacto);
                actualizarTabla();
                limpiarCampos();
            } else {
                ventana.mostrarError("Contacto no encontrado.");
            }
        } catch (Exception e) {
            ventana.mostrarError(e.getMessage());
        }
    }
    private void actualizarTabla() {
        mostrarContactosEnTabla(agenda.getContactos());
    }

    private void mostrarContactosEnTabla(ArrayList<Contacto> contactos) {
        DefaultTableModel modelo = ventana.getModeloTabla();
        modelo.setRowCount(0);
        for (Contacto contacto : contactos) {
            modelo.addRow(new Object[]{
                    contacto.getNombre(),
                    contacto.getApellido(),
                    contacto.getTelefono()
            });
        }
    }
    private void limpiarCampos() {

        ventana.getTxtNombre().setText("");
        ventana.getTxtApellido().setText("");
        ventana.getTxtTelefono().setText("");

    }
}