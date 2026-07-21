package com.hackathon.agenda.controlador;

import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.modelo.Contacto;
import com.hackathon.agenda.vista.VentanaPrincipal;
import javax.swing.table.DefaultTableModel;

public class AgendaControlador {
    private Agenda agenda;
    private VentanaPrincipal ventana;

    public AgendaControlador(Agenda agenda, VentanaPrincipal ventana) {
        this.agenda = agenda;
        this.ventana = ventana;

        configurarEventos();
    }
     private void configurarEventos(){
        ventana.getBtnAgregar().addActionListener(e -> agregarContacto() );

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
        } catch (Exception e) {
            ventana.mostrarError(e.getMessage());
        }
    }
    private void actualizarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) ventana.getTablaContactos().getModel();
        modelo.setRowCount(0);
        for (Contacto contacto : agenda.getContactos()) {
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