package com.hackathon.agenda.vista;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {

    // Campos de texto para que el usuario ingrese informacion
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;

    // Referencias de los botones para las funciones de la agenda
    private JButton btnAgregar;
    private JButton btnBuscar;
    private JButton btnModificar;
    private JButton btnEliminar;

    // Tabla donde se van a mostrar los contactos registrados
    private JTable tablaDeContactos;
    private DefaultTableModel modeloTabla;

    // JLabel permite mostrar texto al usuario, como errores, estados o exitos en procesos.
    private JLabel lblEstado;

    //JPanel permite crear espacios para la interfaz, algo como los contenedores
    private JPanel panelFormulario;
    private JPanel panelBotones;
    private JPanel panelTabla;
    private JPanel panelEstado;

    // Constructor de la ventana
    public VentanaPrincipal(){
        inicializarComponentes();
        configurarVentana();
        construirInterfaz();
    }

    // Metodo para crear e inicializar los componentes de la interfaz
    private void inicializarComponentes() {

        // 20 es para el tamaño inicial del espacio
        txtNombre = new JTextField(20);
        txtApellido = new JTextField(20);
        txtTelefono = new JTextField(20);

        // Crea los botones y cambiamos colores
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(Color.GREEN);
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(Color.WHITE);
        btnModificar = new JButton("Modificar");
        btnModificar.setBackground(Color.ORANGE);
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(Color.RED);

        //Crea la tabla con el modelo que va a usar
        modeloTabla = new DefaultTableModel();
        tablaDeContactos = new JTable(modeloTabla);

        //Muestra el estado
        lblEstado = new JLabel("Estado: Esperando una acción...");

        // Crea los paneles
        panelFormulario = new JPanel();
        panelBotones = new JPanel();
        panelTabla = new JPanel();
        panelEstado = new JPanel();
    }

    // Metodo para configurar las propiedades de la ventana
    private void configurarVentana() {

        //Titulo de la ventana
        setTitle("Agenda Telefónica");

        //Tamaño de la ventana
        setSize(800, 600);

        //Cerrar completamente al cerrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centrar la ventana en la pantalla al iniciar
        setLocationRelativeTo(null);

        //No permitir que el usuario cambie el tamaño de la pantalla
        setResizable(false);
    }

    // Metodo para organizar y agregar los componentes de la ventana
    private void construirInterfaz() {

        //--------------- FORMULARIO -----------------------
        //BorderLayout para distribuir los paneles por zonas
        setLayout(new BorderLayout());

        //GridLayout panel en cuadricula, con 3 filas, 2 columnas,
        // 10px entre filas y 10 px entre columnas
        panelFormulario.setLayout(new GridLayout(3, 2, 10, 10));

        //Margenes para mejor visualizacion
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        //Se agrega los componentes al formulario, agregamos texto con JLabel para
        // indicar lo que va en los campos y despues los campos tambien
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Apellido:"));
        panelFormulario.add(txtApellido);

        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefono);

        // --------------- BOTONES ---------------------
        //FlowLayout organiza los componentes de forma horizontal
        // de izquierda a derecha, uno al lado del otro
        //.CENTER para centrarlos con 15px entre ellos y 10px arriba y abajo
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        // Agregamos los botones al panel de botones
        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);

        // ---------------- TABLA ---------------------
        //Creamos las columnas de la tabla y asignamos titulo a cada una
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Teléfono");

        //Si son muchos contactos se adapta, asi no se sale de la ventana la tabla
        JScrollPane scrollTabla = new JScrollPane(tablaDeContactos);

        panelTabla.setLayout(new BorderLayout());
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        // Si no hay datos en la tabla igualmente ocupa el area y se ve mejor
        tablaDeContactos.setFillsViewportHeight(true);

        // ---------------- ESTADO --------------------
        //FlowLayout para organizar de izquierda a derecha
        // Y .LEFT para alinearlos a la izquierda
        panelEstado.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelEstado.add(lblEstado);

        //Creamos un panel superior para no reemplazar infromacion con .NORTH
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());

        // Organizamos los paneles por zonas
        panelSuperior.add(panelFormulario, BorderLayout.NORTH);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelTabla, BorderLayout.CENTER);
        add(panelEstado, BorderLayout.SOUTH);

        //Swing reorganiza los componentes
        revalidate();

        //Vuelve a dibujar la ventana
        repaint();
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JTable getTablaDeContactos() {
        return tablaDeContactos;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JLabel getLblEstado() {
        return lblEstado;
    }
}