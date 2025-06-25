package vistas;

import com.formdev.flatlaf.FlatClientProperties;
import lib.TextPrompt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class VistaAjustes extends JPanel {
    private JLabel picLabel;
    private JTextField campoUsuario, campoContrasena, campoConfirmarContra;
    private JButton botonAgregarUsuario;
    private int id;

    public VistaAjustes(){
        setBorder(BorderFactory.createEmptyBorder(50,600,450,600));
        setBackground(new Color(223, 223, 223));
        setLayout(new BorderLayout());

        JPanel panelUsuario = new JPanel();
        panelUsuario.setLayout(new BoxLayout(panelUsuario, BoxLayout.Y_AXIS));
        panelUsuario.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        panelUsuario.setBackground(Color.WHITE);
        panelUsuario.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(panelUsuario, BorderLayout.CENTER);

        panelUsuario.add(agregarImagen(150,150,"iconoCajero.png"));

        panelUsuario.add(Box.createRigidArea(new Dimension(0,20)));

        JPanel panelCampoUsuario = new JPanel();
        panelCampoUsuario.setLayout(new BoxLayout(panelCampoUsuario, BoxLayout.X_AXIS));
        panelCampoUsuario.setBackground(Color.WHITE);
        panelUsuario.add(panelCampoUsuario);

        panelCampoUsuario.add(agregarImagen(30, 30, "iconoUsuario.png"));

        panelCampoUsuario.add(Box.createRigidArea(new Dimension(5,0)));

        campoUsuario = new JTextField();
        campoUsuario.setPreferredSize(new Dimension(450, 36));
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        campoUsuario.setForeground(Color.DARK_GRAY);
        TextPrompt textPromptUsuario = new TextPrompt("Usuario", campoUsuario);
        campoUsuario.add(textPromptUsuario);
        campoUsuario.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        campoUsuario.setMaximumSize(campoUsuario.getPreferredSize());
        panelCampoUsuario.add(campoUsuario);

        panelUsuario.add(Box.createRigidArea(new Dimension(0,20)));

        JPanel panelContrasena = new JPanel();
        panelContrasena.setLayout(new BoxLayout(panelContrasena, BoxLayout.X_AXIS));
        panelContrasena.setBackground(Color.WHITE);
        panelUsuario.add(panelContrasena);

        panelContrasena.add(agregarImagen(30, 30, "iconoContrasena.png"));

        panelContrasena.add(Box.createRigidArea(new Dimension(5,0)));

        campoContrasena = new JTextField();
        campoContrasena.setPreferredSize(new Dimension(450, 36));
        campoContrasena.setFont(new Font("Arial", Font.PLAIN, 16));
        campoContrasena.setForeground(Color.DARK_GRAY);
        TextPrompt textPromptContrasena = new TextPrompt("Contraseña", campoContrasena);
        campoContrasena.add(textPromptContrasena);
        campoContrasena.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        campoContrasena.setMaximumSize(campoContrasena.getPreferredSize());
        panelContrasena.add(campoContrasena);

        panelUsuario.add(Box.createRigidArea(new Dimension(0,20)));

        JPanel panelConfirmarContra = new JPanel();
        panelConfirmarContra.setLayout(new BoxLayout(panelConfirmarContra, BoxLayout.X_AXIS));
        panelConfirmarContra.setBackground(Color.WHITE);
        panelUsuario.add(panelConfirmarContra);

        panelConfirmarContra.add(agregarImagen(30, 30, "iconoContrasena.png"));

        panelConfirmarContra.add(Box.createRigidArea(new Dimension(5,0)));

        campoConfirmarContra = new JTextField();
        campoConfirmarContra.setPreferredSize(new Dimension(450, 36));
        campoConfirmarContra.setFont(new Font("Arial", Font.PLAIN, 16));
        campoConfirmarContra.setForeground(Color.DARK_GRAY);
        TextPrompt textPromptConfirmarContra = new TextPrompt("Confirmar contraseña", campoConfirmarContra);
        campoConfirmarContra.add(textPromptConfirmarContra);
        campoConfirmarContra.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        campoConfirmarContra.setMaximumSize(campoConfirmarContra.getPreferredSize());
        panelConfirmarContra.add(campoConfirmarContra);

        panelUsuario.add(Box.createRigidArea(new Dimension(0,40)));

        JPanel panelBotonesUsuario = new JPanel();
        panelBotonesUsuario.setBackground(Color.WHITE);
        panelUsuario.add(panelBotonesUsuario);

        botonAgregarUsuario = new JButton("Agregar usuario");
        botonAgregarUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        botonAgregarUsuario.setForeground(Color.white);
        botonAgregarUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonAgregarUsuario.setBackground(new Color(147, 163, 188));
        botonAgregarUsuario.setPreferredSize(new Dimension(180, 36));
        panelBotonesUsuario.add(botonAgregarUsuario);
    }

    public void setListeners(ActionListener listener) {
        botonAgregarUsuario.addActionListener(listener);
    }

    public JLabel agregarImagen(int ancho, int largo, String nombreArchivo){
        try {
            Image imagen = ImageIO.read(new File("src/img/" + nombreArchivo));
            imagen = imagen.getScaledInstance(ancho, largo, Image.SCALE_SMOOTH);
            picLabel = new JLabel(new ImageIcon(imagen));
            picLabel.setAlignmentX(picLabel.CENTER_ALIGNMENT);
            return picLabel;
        } catch (IOException e) {
            System.out.println("Imagen no encontrada");
            return null;
        }
    }

    public JTextField getCampoUsuario() {
        return campoUsuario;
    }

    public JTextField getCampoContrasena() {
        return campoContrasena;
    }

    public JTextField getCampoConfirmarContra() {
        return campoConfirmarContra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
