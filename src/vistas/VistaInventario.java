package vistas;

import com.formdev.flatlaf.FlatClientProperties;
import lib.TextPrompt;
import modelos.ColorCellRenderer;
import modelos.ProductosTableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class VistaInventario extends JPanel {
    private ProductosTableModel modeloTablaProducto;
    private JTable tablaProductos;
    private final JButton buscarProducto, editarProducto, eliminarProducto, operacionProducto;
    private final JPanel panelTablaProductos;
    private final JTextField campoBuscarProducto, campoNombreProducto, campoMarca, campoPrecio, campoCantidad;
    private final JLabel etiquetaOperacionProducto;
    private int id;

    public VistaInventario(){
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setBackground(new Color(223, 223, 223));
        setLayout(new BorderLayout());

        JPanel panelProducto = new JPanel();
        panelProducto.setLayout(new BorderLayout(0, 10));
        panelProducto.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        panelProducto.setBackground(new Color(223, 223, 223));
        add(panelProducto, BorderLayout.CENTER);

        JPanel panelBuscarProducto = new JPanel();
        panelBuscarProducto.setLayout(new FlowLayout());
        panelBuscarProducto.setBorder(BorderFactory.createEmptyBorder(15,50,15,50));
        panelBuscarProducto.setBackground(Color.WHITE);
        panelBuscarProducto.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelProducto.add(panelBuscarProducto, BorderLayout.NORTH);

        buscarProducto = agregarImagenBoton(30, 30, "iconoLupa.png");
        buscarProducto.setBackground(new Color(147, 163, 188));
        buscarProducto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBuscarProducto.add(buscarProducto, BorderLayout.WEST);

        campoBuscarProducto = new JTextField();
        campoBuscarProducto.setFont(new Font("Arial", Font.PLAIN, 16));
        campoBuscarProducto.setForeground(Color.DARK_GRAY);
        campoBuscarProducto.setPreferredSize(new Dimension(700, 36));
        TextPrompt textPromptBuscarProducto = new TextPrompt("Buscar producto...", campoBuscarProducto);
        campoBuscarProducto.add(textPromptBuscarProducto);
        campoBuscarProducto.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        panelBuscarProducto.add(campoBuscarProducto, BorderLayout.CENTER);

        panelTablaProductos = new JPanel();
        panelTablaProductos.setLayout(new BorderLayout());
        panelTablaProductos.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelTablaProductos.setBackground(Color.WHITE);
        panelTablaProductos.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelProducto.add(panelTablaProductos, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.WHITE);
        panelBuscarProducto.add(panelBotones, BorderLayout.EAST);

        editarProducto = new JButton("Editar producto");
        editarProducto.setFont(new Font("Arial", Font.PLAIN, 16));
        editarProducto.setForeground(Color.white);
        editarProducto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editarProducto.setBackground(new Color(147, 163, 188));
        editarProducto.setPreferredSize(new Dimension(150, 36));
        panelBotones.add(editarProducto);

        eliminarProducto = new JButton("Eliminar producto");
        eliminarProducto.setFont(new Font("Arial", Font.PLAIN, 16));
        eliminarProducto.setForeground(Color.white);
        eliminarProducto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eliminarProducto.setBackground(new Color(147, 163, 188));
        eliminarProducto.setPreferredSize(new Dimension(165, 36));
        panelBotones.add(eliminarProducto);

        inicializarTabla();

        JPanel panelOperacionProducto = new JPanel();
        panelOperacionProducto.setLayout(new BoxLayout(panelOperacionProducto, BoxLayout.Y_AXIS));
        panelOperacionProducto.setBorder(BorderFactory.createEmptyBorder(25,25,550,25));
        panelOperacionProducto.setBackground(new Color(223, 223, 223));
        add(panelOperacionProducto, BorderLayout.WEST);

        JPanel panelCamposProducto = new JPanel();
        panelCamposProducto.setLayout(new BoxLayout(panelCamposProducto, BoxLayout.Y_AXIS));
        panelCamposProducto.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelCamposProducto.setBackground(Color.WHITE);
        panelCamposProducto.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelCamposProducto.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelOperacionProducto.add(panelCamposProducto);

        etiquetaOperacionProducto = new JLabel("Alta de productos");
        etiquetaOperacionProducto.setFont(new Font("Arial", Font.PLAIN, 16));
        etiquetaOperacionProducto.setForeground(Color.BLACK);
        etiquetaOperacionProducto.setAlignmentX(CENTER_ALIGNMENT);
        panelCamposProducto.add(etiquetaOperacionProducto);

        panelCamposProducto.add(Box.createRigidArea(new Dimension(0,20)));

        JPanel panelNombre = new JPanel();
        panelNombre.setLayout(new BoxLayout(panelNombre, BoxLayout.X_AXIS));
        panelNombre.setBackground(Color.WHITE);
        panelCamposProducto.add(panelNombre);

        panelNombre.add(agregarImagen(30, 30, "iconoNombreProducto.png"));

        panelNombre.add(Box.createRigidArea(new Dimension(5,0)));

        campoNombreProducto = new JTextField();
        campoNombreProducto.setPreferredSize(new Dimension(450, 36));
        campoNombreProducto.setFont(new Font("Arial", Font.PLAIN, 16));
        campoNombreProducto.setForeground(Color.DARK_GRAY);
        TextPrompt textPromptProducto = new TextPrompt("Nombre del producto", campoNombreProducto);
        campoNombreProducto.add(textPromptProducto);
        campoNombreProducto.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        campoNombreProducto.setMaximumSize(campoNombreProducto.getPreferredSize());
        panelNombre.add(campoNombreProducto);

        panelCamposProducto.add(Box.createRigidArea(new Dimension(0,20)));

        JPanel panelMarca = new JPanel();
        panelMarca.setLayout(new BoxLayout(panelMarca, BoxLayout.X_AXIS));
        panelMarca.setBackground(Color.WHITE);
        panelCamposProducto.add(panelMarca);

        panelMarca.add(agregarImagen(30, 30, "iconoMarca.png"));

        panelMarca.add(Box.createRigidArea(new Dimension(5,0)));

        campoMarca = new JTextField();
        campoMarca.setPreferredSize(new Dimension(450, 36));
        campoMarca.setFont(new Font("Arial", Font.PLAIN, 16));
        campoMarca.setForeground(Color.DARK_GRAY);
        TextPrompt textPromptMarca = new TextPrompt("Marca del producto", campoMarca);
        campoMarca.add(textPromptMarca);
        campoMarca.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        campoMarca.setMaximumSize(campoMarca.getPreferredSize());
        panelMarca.add(campoMarca);

        panelCamposProducto.add(Box.createRigidArea(new Dimension(0,20)));

        JPanel panelPrecio = new JPanel();
        panelPrecio.setLayout(new BoxLayout(panelPrecio, BoxLayout.X_AXIS));
        panelPrecio.setBackground(Color.WHITE);
        panelCamposProducto.add(panelPrecio);

        panelPrecio.add(agregarImagen(30, 30, "iconoPrecio.png"));

        panelPrecio.add(Box.createRigidArea(new Dimension(5,0)));

        campoPrecio = new JTextField();
        campoPrecio.setPreferredSize(new Dimension(450, 36));
        campoPrecio.setFont(new Font("Arial", Font.PLAIN, 16));
        campoPrecio.setForeground(Color.DARK_GRAY);
        TextPrompt textPromptPrecio = new TextPrompt("Precio del producto", campoPrecio);
        campoPrecio.add(textPromptPrecio);
        campoPrecio.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        campoPrecio.setMaximumSize(campoPrecio.getPreferredSize());
        panelPrecio.add(campoPrecio);

        panelCamposProducto.add(Box.createRigidArea(new Dimension(0,20)));

        JPanel panelCantidad = new JPanel();
        panelCantidad.setLayout(new BoxLayout(panelCantidad, BoxLayout.X_AXIS));
        panelCantidad.setBackground(Color.WHITE);
        panelCamposProducto.add(panelCantidad);

        panelCantidad.add(agregarImagen(30, 30, "iconoCantidad.png"));

        panelCantidad.add(Box.createRigidArea(new Dimension(5,0)));

        campoCantidad = new JTextField();
        campoCantidad.setPreferredSize(new Dimension(450, 36));
        campoCantidad.setFont(new Font("Arial", Font.PLAIN, 16));
        campoCantidad.setForeground(Color.DARK_GRAY);
        TextPrompt textPromptCantidad = new TextPrompt("Cantidad de producto", campoCantidad);
        campoCantidad.add(textPromptCantidad);
        campoCantidad.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        campoCantidad.setMaximumSize(campoCantidad.getPreferredSize());
        panelCantidad.add(campoCantidad);

        panelCamposProducto.add(Box.createRigidArea(new Dimension(0,40)));

        JPanel panelBotonesProducto = new JPanel();
        panelBotonesProducto.setBackground(Color.WHITE);
        panelCamposProducto.add(panelBotonesProducto);

        operacionProducto = new JButton("Agregar producto");
        operacionProducto.setFont(new Font("Arial", Font.PLAIN, 16));
        operacionProducto.setForeground(Color.white);
        operacionProducto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        operacionProducto.setBackground(new Color(147, 163, 188));
        operacionProducto.setPreferredSize(new Dimension(180, 36));
        panelBotonesProducto.add(operacionProducto);
    }

    public void setListeners(ActionListener listener) {
        buscarProducto.addActionListener(listener);
        editarProducto.addActionListener(listener);
        eliminarProducto.addActionListener(listener);
        operacionProducto.addActionListener(listener);
    }

    public void inicializarTabla() {
        modeloTablaProducto = new ProductosTableModel();

        tablaProductos = new JTable(modeloTablaProducto);
        tablaProductos.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(tablaProductos);
        panelTablaProductos.add(scroll, BorderLayout.CENTER);

        JTableHeader header = tablaProductos.getTableHeader();
        header.setForeground(Color.BLACK);
        header.setBackground(Color.LIGHT_GRAY);
        header.setFont(new Font("Arial", Font.BOLD, 12));

        ColorCellRenderer renderer = new ColorCellRenderer();
        for(int i = 0; i < tablaProductos.getColumnCount(); i++) {
            tablaProductos.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    public JLabel agregarImagen(int ancho, int largo, String nombreArchivo){
        try {
            Image imagen = ImageIO.read(new File("src/img/" + nombreArchivo));
            imagen = imagen.getScaledInstance(ancho, largo, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(imagen));
        } catch (IOException e) {
            System.out.println("Imagen no encontrada");
            return null;
        }
    }

    public JButton agregarImagenBoton(int ancho, int largo, String nombreArchivo){
        try {
            Image imagen = ImageIO.read(new File("src/img/" + nombreArchivo));
            imagen = imagen.getScaledInstance(ancho, largo, Image.SCALE_SMOOTH);
            return new JButton(new ImageIcon(imagen));
        } catch (IOException e) {
            System.out.println("Imagen no encontrada");
            return null;
        }
    }

    public ProductosTableModel getModeloTablaProducto() {
        return modeloTablaProducto;
    }

    public JTable getTablaProductos() {
        return tablaProductos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JTextField getCampoBuscarProducto() {
        return campoBuscarProducto;
    }

    public JTextField getCampoNombreProducto() {
        return campoNombreProducto;
    }

    public void setCampoNombreProducto(String nombre) { campoNombreProducto.setText(nombre); }

    public JTextField getCampoMarca() {
        return campoMarca;
    }

    public void setCampoMarca(String marca) {
        campoMarca.setText(marca);
    }

    public JTextField getCampoPrecio() {
        return campoPrecio;
    }

    public void setCampoPrecio(String precio) {
        campoPrecio.setText(precio);
    }

    public JTextField getCampoCantidad() {
        return campoCantidad;
    }

    public void setCampoCantidad(String cantidad) {
        campoCantidad.setText(cantidad);
    }

    public JButton getOperacionProducto() {
        return operacionProducto;
    }

    public JLabel getEtiquetaOperacionProducto() {
        return etiquetaOperacionProducto;
    }
}
