package vistas;

import com.formdev.flatlaf.FlatClientProperties;
import lib.TextPrompt;
import modelos.ClientesTableModel;
import modelos.ColorCellRenderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class VistaClientes extends JPanel {
    private ClientesTableModel modeloTablaCliente;
    private JTable tablaClientes;
    private JButton buscarCliente, editarCliente, eliminarCliente, operacionCliente;
    private JPanel panelTablaClientes;
    private JTextField campoBuscarCliente, campoNombreCliente, campoApellido, campoRfc, campoCorreo;
    private JLabel etiquetaOperacionCliente, picLabel;
    private int id;

    public VistaClientes(){
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setBackground(new Color(223, 223, 223));
        setLayout(new BorderLayout());

        JPanel panelCliente = new JPanel();
        panelCliente.setLayout(new BorderLayout(0, 10));
        panelCliente.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        panelCliente.setBackground(new Color(223, 223, 223));
        add(panelCliente, BorderLayout.CENTER);

        JPanel panelBuscarCliente = new JPanel();
        panelBuscarCliente.setLayout(new FlowLayout());
        panelBuscarCliente.setBorder(BorderFactory.createEmptyBorder(15,50,15,50));
        panelBuscarCliente.setBackground(Color.WHITE);
        panelBuscarCliente.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelCliente.add(panelBuscarCliente, BorderLayout.NORTH);

        buscarCliente = agregarImagenBoton(30, 30, "iconoLupa.png");
        buscarCliente.setBackground(new Color(147, 163, 188));
        buscarCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBuscarCliente.add(buscarCliente, BorderLayout.WEST);

        campoBuscarCliente = new JTextField();
        campoBuscarCliente.setFont(new Font("Arial", Font.PLAIN, 16));
        campoBuscarCliente.setForeground(Color.DARK_GRAY);
        campoBuscarCliente.setPreferredSize(new Dimension(700, 36));
        TextPrompt textPromptBuscarProducto = new TextPrompt("Buscar cliente...", campoBuscarCliente);
        campoBuscarCliente.add(textPromptBuscarProducto);
        campoBuscarCliente.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        panelBuscarCliente.add(campoBuscarCliente, BorderLayout.CENTER);

        panelTablaClientes = new JPanel();
        panelTablaClientes.setLayout(new BorderLayout());
        panelTablaClientes.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelTablaClientes.setBackground(Color.WHITE);
        panelTablaClientes.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelCliente.add(panelTablaClientes, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.WHITE);
        panelBuscarCliente.add(panelBotones, BorderLayout.EAST);

        editarCliente = new JButton("Editar cliente");
        editarCliente.setFont(new Font("Arial", Font.PLAIN, 16));
        editarCliente.setForeground(Color.white);
        editarCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editarCliente.setBackground(new Color(147, 163, 188));
        editarCliente.setPreferredSize(new Dimension(150, 36));
        panelBotones.add(editarCliente);

        eliminarCliente = new JButton("Eliminar cliente");
        eliminarCliente.setFont(new Font("Arial", Font.PLAIN, 16));
        eliminarCliente.setForeground(Color.white);
        eliminarCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eliminarCliente.setBackground(new Color(147, 163, 188));
        eliminarCliente.setPreferredSize(new Dimension(165, 36));
        panelBotones.add(eliminarCliente);

        inicializarTabla();

        JPanel panelOperacionCliente = new JPanel();
        panelOperacionCliente.setLayout(new BoxLayout(panelOperacionCliente, BoxLayout.Y_AXIS));
        panelOperacionCliente.setBorder(BorderFactory.createEmptyBorder(25,25,550,25));
        panelOperacionCliente.setBackground(new Color(223, 223, 223));
        add(panelOperacionCliente, BorderLayout.WEST);

        JPanel panelCamposCliente = new JPanel();
        panelCamposCliente.setLayout(new BoxLayout(panelCamposCliente, BoxLayout.Y_AXIS));
        panelCamposCliente.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelCamposCliente.setBackground(Color.WHITE);
        panelCamposCliente.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelCamposCliente.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelOperacionCliente.add(panelCamposCliente);

        etiquetaOperacionCliente = new JLabel("Alta de clientes");
        etiquetaOperacionCliente.setFont(new Font("Arial", Font.PLAIN, 16));
        etiquetaOperacionCliente.setForeground(Color.BLACK);
        etiquetaOperacionCliente.setAlignmentX(etiquetaOperacionCliente.CENTER_ALIGNMENT);
        panelCamposCliente.add(etiquetaOperacionCliente);

        panelCamposCliente.add(Box.createRigidArea(new Dimension(0,20)));

        JPanel panelNombre = new JPanel();
        panelNombre.setLayout(new BoxLayout(panelNombre, BoxLayout.X_AXIS));
        panelNombre.setBackground(Color.WHITE);
        panelCamposCliente.add(panelNombre);

        panelNombre.add(agregarImagen(30, 30, "iconoCliente.png"));

        panelNombre.add(Box.createRigidArea(new Dimension(5,0)));

        campoNombreCliente = new JTextField();
        campoNombreCliente.setPreferredSize(new Dimension(450, 36));
        campoNombreCliente.setFont(new Font("Arial", Font.PLAIN, 16));
        campoNombreCliente.setForeground(Color.DARK_GRAY);
        TextPrompt textPromptNombre = new TextPrompt("Nombre del cliente", campoNombreCliente);
        campoNombreCliente.add(textPromptNombre);
        campoNombreCliente.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        campoNombreCliente.setMaximumSize(campoNombreCliente.getPreferredSize());
        panelNombre.add(campoNombreCliente);

        panelCamposCliente.add(Box.createRigidArea(new Dimension(0,20)));

        JPanel panelApellido = new JPanel();
        panelApellido.setLayout(new BoxLayout(panelApellido, BoxLayout.X_AXIS));
        panelApellido.setBackground(Color.WHITE);
        panelCamposCliente.add(panelApellido);

        panelApellido.add(agregarImagen(30, 30, "iconoApellido.png"));

        panelApellido.add(Box.createRigidArea(new Dimension(5,0)));

        campoApellido = new JTextField();
        campoApellido.setPreferredSize(new Dimension(450, 36));
        campoApellido.setFont(new Font("Arial", Font.PLAIN, 16));
        campoApellido.setForeground(Color.DARK_GRAY);
        TextPrompt textPromptApellido = new TextPrompt("Apellido del cliente", campoApellido);
        campoApellido.add(textPromptApellido);
        campoApellido.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        campoApellido.setMaximumSize(campoApellido.getPreferredSize());
        panelApellido.add(campoApellido);

        panelCamposCliente.add(Box.createRigidArea(new Dimension(0,20)));

        JPanel panelRfc = new JPanel();
        panelRfc.setLayout(new BoxLayout(panelRfc, BoxLayout.X_AXIS));
        panelRfc.setBackground(Color.WHITE);
        panelCamposCliente.add(panelRfc);

        panelRfc.add(agregarImagen(30, 30, "iconoRfc.png"));

        panelRfc.add(Box.createRigidArea(new Dimension(5,0)));

        campoRfc = new JTextField();
        campoRfc.setPreferredSize(new Dimension(450, 36));
        campoRfc.setFont(new Font("Arial", Font.PLAIN, 16));
        campoRfc.setForeground(Color.DARK_GRAY);
        TextPrompt textPromptRfc = new TextPrompt("RFC del cliente", campoRfc);
        campoRfc.add(textPromptRfc);
        campoRfc.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        campoRfc.setMaximumSize(campoRfc.getPreferredSize());
        panelRfc.add(campoRfc);

        panelCamposCliente.add(Box.createRigidArea(new Dimension(0,20)));

        JPanel panelCorreo = new JPanel();
        panelCorreo.setLayout(new BoxLayout(panelCorreo, BoxLayout.X_AXIS));
        panelCorreo.setBackground(Color.WHITE);
        panelCamposCliente.add(panelCorreo);

        panelCorreo.add(agregarImagen(30, 24, "iconoCorreo.png"));

        panelCorreo.add(Box.createRigidArea(new Dimension(5,0)));

        campoCorreo = new JTextField();
        campoCorreo.setPreferredSize(new Dimension(450, 36));
        campoCorreo.setFont(new Font("Arial", Font.PLAIN, 16));
        campoCorreo.setForeground(Color.DARK_GRAY);
        TextPrompt textPromptCorreo = new TextPrompt("Correo electrónico del cliente", campoCorreo);
        campoCorreo.add(textPromptCorreo);
        campoCorreo.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        campoCorreo.setMaximumSize(campoCorreo.getPreferredSize());
        panelCorreo.add(campoCorreo);

        panelCamposCliente.add(Box.createRigidArea(new Dimension(0,40)));

        JPanel panelBotonesCliente = new JPanel();
        panelBotonesCliente.setBackground(Color.WHITE);
        panelCamposCliente.add(panelBotonesCliente);

        operacionCliente = new JButton("Agregar cliente");
        operacionCliente.setFont(new Font("Arial", Font.PLAIN, 16));
        operacionCliente.setForeground(Color.white);
        operacionCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        operacionCliente.setBackground(new Color(147, 163, 188));
        operacionCliente.setPreferredSize(new Dimension(180, 36));
        panelBotonesCliente.add(operacionCliente);
    }

    public void setListeners(ActionListener listener) {
        buscarCliente.addActionListener(listener);
        editarCliente.addActionListener(listener);
        eliminarCliente.addActionListener(listener);
        operacionCliente.addActionListener(listener);
    }

    public void inicializarTabla() {
        modeloTablaCliente = new ClientesTableModel();

        tablaClientes = new JTable(modeloTablaCliente);
        tablaClientes.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(tablaClientes);
        panelTablaClientes.add(scroll, BorderLayout.CENTER);

        JTableHeader header = tablaClientes.getTableHeader();
        header.setForeground(Color.BLACK);
        header.setBackground(Color.LIGHT_GRAY);
        header.setFont(new Font("Arial", Font.BOLD, 12));

        ColorCellRenderer renderer = new ColorCellRenderer();
        for(int i = 0; i < tablaClientes.getColumnCount(); i++) {
            tablaClientes.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    public JLabel agregarImagen(int ancho, int largo, String nombreArchivo){
        try {
            Image imagen = ImageIO.read(new File("src/img/" + nombreArchivo));
            imagen = imagen.getScaledInstance(ancho, largo, Image.SCALE_SMOOTH);
            picLabel = new JLabel(new ImageIcon(imagen));
            return picLabel;
        } catch (IOException e) {
            System.out.println("Imagen no encontrada");
            return null;
        }
    }

    public JButton agregarImagenBoton(int ancho, int largo, String nombreArchivo){
        try {
            Image imagen = ImageIO.read(new File("src/img/" + nombreArchivo));
            imagen = imagen.getScaledInstance(ancho, largo, Image.SCALE_SMOOTH);
            JButton boton = new JButton(new ImageIcon(imagen));
            return boton;
        } catch (IOException e) {
            System.out.println("Imagen no encontrada");
            return null;
        }
    }

    public JTextField getCampoBuscarCliente() {
        return campoBuscarCliente;
    }

    public ClientesTableModel getModeloTablaCliente() {
        return modeloTablaCliente;
    }

    public JTable getTablaClientes() {
        return tablaClientes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JTextField getCampoNombreCliente() {
        return campoNombreCliente;
    }

    public void setCampoNombreCliente(String nombre) { campoNombreCliente.setText(nombre); }

    public JTextField getCampoApellido() {
        return campoApellido;
    }

    public void setCampoApellido(String apellido) {
        campoApellido.setText(apellido);
    }

    public JTextField getCampoRfc() {
        return campoRfc;
    }

    public void setCampoRfc(String rfc) {
        campoRfc.setText(rfc);
    }

    public JTextField getCampoCorreo() {
        return campoCorreo;
    }

    public void setCampoCorreo(String correo) {
        campoCorreo.setText(correo);
    }

    public JButton getOperacionCliente() {
        return operacionCliente;
    }

    public JLabel getEtiquetaOperacionCliente() {
        return etiquetaOperacionCliente;
    }
}
