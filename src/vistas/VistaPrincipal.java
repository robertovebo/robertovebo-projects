package vistas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class VistaPrincipal extends JFrame {
    private JLabel picLabel, tituloOperacion, infoCajero, numeroCajero;
    private JButton botonVentas, botonProductos, botonClientes, botonInformes, botonConfig;
    private JFileChooser fileChooserPDF;
    private JPanel panelPrincipal, panelOperaciones;
    private VistaVentas vistaVentas;

    public VistaPrincipal(){
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        add(panelPrincipal);

        panelOperaciones = new JPanel();
        panelOperaciones.setLayout(new BorderLayout());
        panelPrincipal.add(panelOperaciones, BorderLayout.CENTER);

        JPanel panelInfo = new JPanel();
        panelInfo.setBackground(new Color(150, 223, 155));
        panelInfo.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelInfo.setPreferredSize(new Dimension(1200, 70));
        panelPrincipal.add(panelInfo, BorderLayout.NORTH);

        panelInfo.add(Box.createRigidArea(new Dimension(15,30)));

        panelInfo.add(agregarImagen(60, 60, "iconoQuetzal.png"));

        panelInfo.add(Box.createRigidArea(new Dimension(25,30)));

        tituloOperacion = new JLabel("Ventas");
        tituloOperacion.setFont(new Font("Arial", Font.PLAIN, 24));
        tituloOperacion.setForeground(Color.WHITE);
        panelInfo.add(tituloOperacion);

        panelInfo.add(Box.createRigidArea(new Dimension(1200,30)));

        numeroCajero = new JLabel("Número de cajero");
        numeroCajero.setFont(new Font("Arial", Font.PLAIN, 24));
        numeroCajero.setForeground(Color.WHITE);
        panelInfo.add(numeroCajero);

        JLabel separacion = new JLabel("  |  ");
        separacion.setFont(new Font("Arial", Font.PLAIN, 24));
        separacion.setForeground(Color.WHITE);
        panelInfo.add(separacion);

        infoCajero = new JLabel("Nombre por default");
        infoCajero.setFont(new Font("Arial", Font.PLAIN, 24));
        infoCajero.setForeground(Color.WHITE);
        panelInfo.add(infoCajero);

        JPanel panelOpciones = new JPanel();
        panelOpciones.setBackground(new Color(147, 163, 188));
        panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.Y_AXIS));
        panelOpciones.setPreferredSize(new Dimension(110, 1200));
        panelOpciones.setBorder(BorderFactory.createEmptyBorder(7,0,0,0));
        panelPrincipal.add(panelOpciones, BorderLayout.WEST);

        botonVentas = agregarImagenBoton("Ventas", 60, 60, "iconoCajero.png");
        botonVentas.setFont(new Font("Arial", Font.PLAIN, 16));
        botonVentas.setHorizontalTextPosition(JButton.CENTER);
        botonVentas.setVerticalTextPosition(JButton.BOTTOM);
        botonVentas.setForeground(Color.white);
        botonVentas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonVentas.setBackground(new Color(147, 163, 188));
        botonVentas.setOpaque(false);
        botonVentas.setBorderPainted(false);
        panelOpciones.add(botonVentas);

        panelOpciones.add(Box.createRigidArea(new Dimension(20,15)));

        botonProductos = agregarImagenBoton("Inventario", 50, 50, "iconoProducto.png");
        botonProductos.setFont(new Font("Arial", Font.PLAIN, 14));
        botonProductos.setHorizontalTextPosition(JButton.CENTER);
        botonProductos.setVerticalTextPosition(JButton.BOTTOM);
        botonProductos.setForeground(Color.white);
        botonProductos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonProductos.setBackground(new Color(147, 163, 188));
        botonProductos.setOpaque(false);
        botonProductos.setBorderPainted(false);
        panelOpciones.add(botonProductos);

        panelOpciones.add(Box.createRigidArea(new Dimension(0,15)));

        botonClientes = agregarImagenBoton("Clientes", 55, 55, "iconoClientes.png");
        botonClientes.setFont(new Font("Arial", Font.PLAIN, 15));
        botonClientes.setHorizontalTextPosition(JButton.CENTER);
        botonClientes.setVerticalTextPosition(JButton.BOTTOM);
        botonClientes.setForeground(Color.white);
        botonClientes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonClientes.setBackground(new Color(147, 163, 188));
        botonClientes.setOpaque(false);
        botonClientes.setBorderPainted(false);
        panelOpciones.add(botonClientes);

        panelOpciones.add(Box.createRigidArea(new Dimension(20,15)));

        botonInformes = agregarImagenBoton("Informes", 55, 55, "iconoInformes.png");
        botonInformes.setFont(new Font("Arial", Font.PLAIN, 15));
        botonInformes.setHorizontalTextPosition(JButton.CENTER);
        botonInformes.setVerticalTextPosition(JButton.BOTTOM);
        botonInformes.setForeground(Color.white);
        botonInformes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonInformes.setBackground(new Color(147, 163, 188));
        botonInformes.setOpaque(false);
        botonInformes.setBorderPainted(false);
        panelOpciones.add(botonInformes);

        panelOpciones.add(Box.createRigidArea(new Dimension(20,500)), BorderLayout.SOUTH);

        botonConfig = agregarImagenBoton("Ajustes", 60, 60, "iconoConfig.png");
        botonConfig.setFont(new Font("Arial", Font.PLAIN, 15));
        botonConfig.setHorizontalTextPosition(JButton.CENTER);
        botonConfig.setVerticalTextPosition(JButton.BOTTOM);
        botonConfig.setForeground(Color.white);
        botonConfig.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonConfig.setBackground(new Color(147, 163, 188));
        botonConfig.setOpaque(false);
        botonConfig.setBorderPainted(false);
        panelOpciones.add(botonConfig);

        vistaVentas = new VistaVentas();
        panelOperaciones.add(vistaVentas);

        UIManager.put("FileChooser.cancelButtonText", "Cancelar");

        fileChooserPDF = new JFileChooser();
        fileChooserPDF.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooserPDF.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter pdfs = new FileNameExtensionFilter("Documentos PDF","pdf");
        fileChooserPDF.addChoosableFileFilter(pdfs);
        fileChooserPDF.setFileFilter(pdfs);
    }

    public void setListeners(ActionListener listener) {
        botonVentas.addActionListener(listener);
        botonProductos.addActionListener(listener);
        botonClientes.addActionListener(listener);
        botonInformes.addActionListener(listener);
        botonConfig.addActionListener(listener);
    }

    public void agregarPanel(JPanel panel){
        panelOperaciones.removeAll();
        panelOperaciones.add(panel, BorderLayout.CENTER);

        panelOperaciones.revalidate();
        panelOperaciones.repaint();
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

    public JButton agregarImagenBoton(String textoBoton, int ancho, int largo, String nombreArchivo){
        try {
            Image imagen = ImageIO.read(new File("src/img/" + nombreArchivo));
            imagen = imagen.getScaledInstance(ancho, largo, Image.SCALE_SMOOTH);
            JButton boton = new JButton(textoBoton, new ImageIcon(imagen));
            return boton;
        } catch (IOException e) {
            System.out.println("Imagen no encontrada");
            return null;
        }
    }

    public JPanel getPanelOperaciones() {
        return panelOperaciones;
    }

    public void setPanelOperaciones(JPanel panelOperaciones) {
        this.panelOperaciones = panelOperaciones;
    }

    public void setTituloOperacion(JLabel tituloOperacion) {
        this.tituloOperacion = tituloOperacion;
    }

    public JLabel getTituloOperacion() {
        return tituloOperacion;
    }

    public JLabel getInfoCajero() {
        return infoCajero;
    }

    public void setInfoCajero(JLabel infoCajero) {
        this.infoCajero = infoCajero;
    }

    public JFileChooser getFileChooserPDF() {
        return fileChooserPDF;
    }
}
