package vistas;

import com.formdev.flatlaf.FlatClientProperties;
import lib.TextPrompt;
import modelos.ColorCellRenderer;
import modelos.VentasTableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class VistaVentas extends JPanel {
    private VentasTableModel modeloTablaVenta;
    private JTable tablaVentas;
    private JButton buscarProducto, buscarCliente, agregarVenta, editarVenta, eliminarVenta, realizarCompra, bajarCantidad, subirCantidad;
    private JPanel panelTablaVentas;
    private JTextField campoProducto, campoCantidad, campoClienteVenta;
    private JLabel clienteVenta, etiquetaSubtotal, etiquetaDescuento, cantidadSubtotal, cantidadDescuento, etiquetaTotal, etiquetaTotalPagar, cantidadTotal, cantidadTotalPagar;
    private int id;

    public VistaVentas(){
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setBackground(new Color(223, 223, 223));
        setLayout(new BorderLayout());

        JPanel panelVenta = new JPanel();
        panelVenta.setLayout(new BorderLayout(0, 10));
        panelVenta.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        panelVenta.setBackground(new Color(223, 223, 223));
        add(panelVenta, BorderLayout.CENTER);

        JPanel panelBuscarProducto = new JPanel();
        panelBuscarProducto.setLayout(new FlowLayout());
        panelBuscarProducto.setBorder(BorderFactory.createEmptyBorder(15,50,15,50));
        panelBuscarProducto.setBackground(Color.WHITE);
        panelBuscarProducto.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelVenta.add(panelBuscarProducto, BorderLayout.NORTH);

        buscarProducto = agregarImagenBoton(30, 30, "iconoLupa.png");
        buscarProducto.setBackground(new Color(147, 163, 188));
        buscarProducto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBuscarProducto.add(buscarProducto, BorderLayout.WEST);

        campoProducto = new JTextField();
        campoProducto.setFont(new Font("Arial", Font.PLAIN, 16));
        campoProducto.setForeground(Color.DARK_GRAY);
        campoProducto.setPreferredSize(new Dimension(400, 36));
        TextPrompt textPromptProducto = new TextPrompt("Ingresar producto...", campoProducto);
        campoProducto.add(textPromptProducto);
        campoProducto.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        panelBuscarProducto.add(campoProducto, BorderLayout.CENTER);

        JPanel panelComponentes = new JPanel();
        panelComponentes.setBackground(Color.WHITE);
        panelBuscarProducto.add(panelComponentes, BorderLayout.EAST);

        agregarVenta = new JButton("Agregar venta");
        agregarVenta.setFont(new Font("Arial", Font.PLAIN, 16));
        agregarVenta.setForeground(Color.white);
        agregarVenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        agregarVenta.setBackground(new Color(147, 163, 188));
        agregarVenta.setPreferredSize(new Dimension(130, 36));
        panelComponentes.add(agregarVenta);

        editarVenta = new JButton("Editar venta");
        editarVenta.setFont(new Font("Arial", Font.PLAIN, 16));
        editarVenta.setForeground(Color.white);
        editarVenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editarVenta.setBackground(new Color(147, 163, 188));
        editarVenta.setPreferredSize(new Dimension(120, 36));
        panelComponentes.add(editarVenta);

        eliminarVenta = new JButton("Eliminar venta");
        eliminarVenta.setFont(new Font("Arial", Font.PLAIN, 16));
        eliminarVenta.setForeground(Color.white);
        eliminarVenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eliminarVenta.setBackground(new Color(147, 163, 188));
        eliminarVenta.setPreferredSize(new Dimension(135, 36));
        panelComponentes.add(eliminarVenta);

        panelComponentes.add(Box.createRigidArea(new Dimension(30,0)));

        bajarCantidad = new JButton("-");
        bajarCantidad.setFont(new Font("Arial", Font.PLAIN, 20));
        bajarCantidad.setForeground(Color.white);
        bajarCantidad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bajarCantidad.setBackground(new Color(147, 163, 188));
        bajarCantidad.setPreferredSize(new Dimension(30, 30));
        panelComponentes.add(bajarCantidad);

        campoCantidad = new JTextField("0");
        campoCantidad.setFont(new Font("Arial", Font.PLAIN, 20));
        campoCantidad.setForeground(Color.DARK_GRAY);
        campoCantidad.setPreferredSize(new Dimension(30, 30));
        campoCantidad.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        panelComponentes.add(campoCantidad);

        subirCantidad = new JButton("+");
        subirCantidad.setFont(new Font("Arial", Font.PLAIN, 20));
        subirCantidad.setForeground(Color.white);
        subirCantidad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        subirCantidad.setBackground(new Color(147, 163, 188));
        subirCantidad.setPreferredSize(new Dimension(30, 30));
        panelComponentes.add(subirCantidad);

        panelTablaVentas = new JPanel();
        panelTablaVentas.setLayout(new BorderLayout());
        panelTablaVentas.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelTablaVentas.setBackground(Color.WHITE);
        panelTablaVentas.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelVenta.add(panelTablaVentas, BorderLayout.CENTER);

        inicializarTabla();

        JPanel panelClientesVenta = new JPanel();
        panelClientesVenta.setLayout(new BoxLayout(panelClientesVenta, BoxLayout.Y_AXIS));
        panelClientesVenta.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        panelClientesVenta.setBackground(new Color(223, 223, 223));
        //panelClientesVenta.setPreferredSize(new Dimension(530, 0));
        add(panelClientesVenta, BorderLayout.EAST);

        JPanel panelBuscarClienteVenta = new JPanel();
        panelBuscarClienteVenta.setLayout(new BoxLayout(panelBuscarClienteVenta, BoxLayout.Y_AXIS));
        panelBuscarClienteVenta.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelBuscarClienteVenta.setBackground(Color.WHITE);
        panelBuscarClienteVenta.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelBuscarClienteVenta.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelClientesVenta.add(panelBuscarClienteVenta);

        clienteVenta = new JLabel("Cliente de mostrador");
        clienteVenta.setFont(new Font("Arial", Font.PLAIN, 16));
        clienteVenta.setForeground(Color.BLACK);
        clienteVenta.setAlignmentX(clienteVenta.CENTER_ALIGNMENT);
        panelBuscarClienteVenta.add(clienteVenta);

        panelBuscarClienteVenta.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel panelComponentesClienteVenta = new JPanel();
        panelComponentesClienteVenta.setLayout(new BoxLayout(panelComponentesClienteVenta, BoxLayout.X_AXIS));
        panelComponentesClienteVenta.setBackground(Color.WHITE);
        panelBuscarClienteVenta.add(panelComponentesClienteVenta);

        //panelComponentesClienteVenta.add(Box.createRigidArea(new Dimension(100,0)));

        buscarCliente = agregarImagenBoton(30, 30, "iconoCliente.png");
        buscarCliente.setBackground(new Color(147, 163, 188));
        buscarCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buscarCliente.setMaximumSize(buscarCliente.getPreferredSize());
        panelComponentesClienteVenta.add(buscarCliente);

        campoClienteVenta = new JTextField();
        campoClienteVenta.setPreferredSize(new Dimension(320, 36));
        campoClienteVenta.setFont(new Font("Arial", Font.PLAIN, 16));
        campoClienteVenta.setForeground(Color.DARK_GRAY);
        TextPrompt textPromptCliente = new TextPrompt("Ingresar cliente...", campoClienteVenta);
        campoClienteVenta.add(textPromptCliente);
        campoClienteVenta.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        campoClienteVenta.setMaximumSize(campoClienteVenta.getPreferredSize());
        panelComponentesClienteVenta.add(campoClienteVenta);

        panelClientesVenta.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel panelTotales = new JPanel();
        panelTotales.setLayout(new BoxLayout(panelTotales, BoxLayout.Y_AXIS));
        panelTotales.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        panelTotales.setBackground(Color.WHITE);
        panelTotales.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelTotales.setAlignmentX(panelTotales.LEFT_ALIGNMENT);
        panelClientesVenta.add(panelTotales);

        JPanel panelSubtotal = new JPanel();
        panelSubtotal.setLayout(new BoxLayout(panelSubtotal, BoxLayout.X_AXIS));
        panelSubtotal.setBackground(Color.WHITE);
        panelTotales.add(panelSubtotal);

        etiquetaSubtotal = new JLabel("Subtotal:");
        etiquetaSubtotal.setFont(new Font("Arial", Font.PLAIN, 20));
        etiquetaSubtotal.setForeground(Color.BLACK);
        panelSubtotal.add(etiquetaSubtotal);

        panelSubtotal.add(Box.createRigidArea(new Dimension(200,0)));

        cantidadSubtotal = new JLabel("$0.00");
        cantidadSubtotal.setFont(new Font("Arial", Font.PLAIN, 20));
        cantidadSubtotal.setForeground(Color.BLACK);
        panelSubtotal.add(cantidadSubtotal);

        panelTotales.add(Box.createRigidArea(new Dimension(0,20)));

        JPanel panelDescuento = new JPanel();
        panelDescuento.setLayout(new BoxLayout(panelDescuento, BoxLayout.X_AXIS));
        panelDescuento.setBackground(Color.WHITE);
        panelTotales.add(panelDescuento);

        etiquetaDescuento = new JLabel("Descuento:");
        etiquetaDescuento.setFont(new Font("Arial", Font.PLAIN, 20));
        etiquetaDescuento.setForeground(Color.BLACK);
        panelDescuento.add(etiquetaDescuento);

        panelDescuento.add(Box.createRigidArea(new Dimension(200,0)));

        cantidadDescuento = new JLabel("0%");
        cantidadDescuento.setFont(new Font("Arial", Font.PLAIN, 20));
        cantidadDescuento.setForeground(Color.BLACK);
        panelDescuento.add(cantidadDescuento);

        panelTotales.add(Box.createRigidArea(new Dimension(0,20)));

        JPanel panelTotal = new JPanel();
        panelTotal.setLayout(new BoxLayout(panelTotal, BoxLayout.Y_AXIS));
        panelTotal.setBackground(Color.WHITE);
        panelTotales.add(panelTotal);

        JPanel panelEtiquetaTotales = new JPanel();
        panelEtiquetaTotales.setLayout(new BoxLayout(panelEtiquetaTotales, BoxLayout.X_AXIS));
        panelEtiquetaTotales.setBackground(Color.WHITE);
        panelTotal.add(panelEtiquetaTotales);

        etiquetaTotal = new JLabel("Total");
        etiquetaTotal.setFont(new Font("Arial", Font.PLAIN, 12));
        etiquetaTotal.setForeground(Color.BLACK);
        panelEtiquetaTotales.add(etiquetaTotal);

        panelEtiquetaTotales.add(Box.createRigidArea(new Dimension(175,0)));

        etiquetaTotalPagar = new JLabel("Total a pagar");
        etiquetaTotalPagar.setFont(new Font("Arial", Font.PLAIN, 12));
        etiquetaTotalPagar.setForeground(Color.BLACK);
        panelEtiquetaTotales.add(etiquetaTotalPagar);

        panelTotal.add(Box.createRigidArea(new Dimension(0,5)));

        JPanel panelCantidadesTotales = new JPanel();
        panelCantidadesTotales.setLayout(new BoxLayout(panelCantidadesTotales, BoxLayout.X_AXIS));
        panelCantidadesTotales.setBackground(Color.WHITE);
        panelTotal.add(panelCantidadesTotales);

        panelEtiquetaTotales.add(Box.createRigidArea(new Dimension(50,0)));

        cantidadTotal = new JLabel("$0.00");
        cantidadTotal.setFont(new Font("Arial", Font.PLAIN, 28));
        cantidadTotal.setForeground(Color.BLACK);
        panelCantidadesTotales.add(cantidadTotal);

        panelCantidadesTotales.add(Box.createRigidArea(new Dimension(165,0)));

        cantidadTotalPagar = new JLabel("$0.00");
        cantidadTotalPagar.setFont(new Font("Arial", Font.PLAIN, 28));
        cantidadTotalPagar.setForeground(Color.BLACK);
        panelCantidadesTotales.add(cantidadTotalPagar);

        panelTotales.add(Box.createRigidArea(new Dimension(0,25)));

        realizarCompra = agregarImagenBoton(40, 40, "iconoCompra.png");
        realizarCompra.setText("Realizar compra");
        realizarCompra.setFont(new Font("Arial", Font.PLAIN, 16));
        realizarCompra.setForeground(Color.white);
        realizarCompra.setCursor(new Cursor(Cursor.HAND_CURSOR));
        realizarCompra.setBackground(new Color(127, 141, 172));
        realizarCompra.setPreferredSize(new Dimension(240, 60));
        realizarCompra.setAlignmentX(realizarCompra.CENTER_ALIGNMENT);
        panelTotales.add(realizarCompra);

        panelClientesVenta.add(Box.createRigidArea(new Dimension(0,200)));

    }

    public void setListeners(ActionListener listener) {
        buscarProducto.addActionListener(listener);
        buscarCliente.addActionListener(listener);
        agregarVenta.addActionListener(listener);
        editarVenta.addActionListener(listener);
        eliminarVenta.addActionListener(listener);
        bajarCantidad.addActionListener(listener);
        subirCantidad.addActionListener(listener);
        realizarCompra.addActionListener(listener);
    }

    public void asignarJTextFieldListener(KeyListener listener) {
        clienteVenta.addKeyListener(listener);
    }

    public void inicializarTabla() {
        modeloTablaVenta = new VentasTableModel();

        tablaVentas = new JTable(modeloTablaVenta);
        tablaVentas.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(tablaVentas);
        panelTablaVentas.add(scroll, BorderLayout.CENTER);

        JTableHeader header = tablaVentas.getTableHeader();
        header.setForeground(Color.BLACK);
        header.setBackground(Color.LIGHT_GRAY);
        header.setFont(new Font("Arial", Font.BOLD, 12));

        ColorCellRenderer renderer = new ColorCellRenderer();
        for(int i = 0; i < tablaVentas.getColumnCount(); i++) {
            tablaVentas.getColumnModel().getColumn(i).setCellRenderer(renderer);
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

    public JTextField getCampoProducto() {
        return campoProducto;
    }

    public void setCampoProducto(JTextField campoProducto) {
        this.campoProducto = campoProducto;
    }

    public JTextField getCampoCantidad() {
        return campoCantidad;
    }

    public void setCampoCantidad(JTextField campoCantidad) {
        this.campoCantidad = campoCantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JTable getTablaVentas() {
        return tablaVentas;
    }

    public void setTablaVentas(JTable tablaVentas) {
        this.tablaVentas = tablaVentas;
    }

    public VentasTableModel getModeloTablaVenta() {
        return modeloTablaVenta;
    }

    public JLabel getClienteVenta() {
        return clienteVenta;
    }

    public void setClienteVenta(String nombreCliente) {
        clienteVenta.setText(nombreCliente);
    }
}
