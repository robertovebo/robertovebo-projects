package vistas;

import com.formdev.flatlaf.FlatClientProperties;
import lib.TextPrompt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class VistaLogin extends JFrame {
    private Image logo;
    private JLabel etiquetaUsuario, picLabel;
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JButton botonLogin, botonSalir;

    public VistaLogin(){
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(150, 223, 155));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30,140,30,140));
        add(panelPrincipal);

        JPanel panelImg = new JPanel();
        panelImg.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelImg.setBackground(new Color(150, 223, 155));
        panelPrincipal.add(panelImg, BorderLayout.NORTH);

        try {
            //El logo se hizo con un editor de logos - https://es.cooltext.com/
            logo = ImageIO.read(new File("src/img/logoQuetzal.png"));
            JLabel logoLabel = new JLabel(new ImageIcon(logo));
            panelImg.add(logoLabel);
        } catch (IOException e) {
            System.out.println("Imagenes no encontrada");
        }

        //La imagen se encontró en una página de uso libre - https://www.pngwing.com/es/free-png-bbojv
        panelImg.add(agregarImagen( 57, 109, "src/img/Quetzal.png"));

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new BorderLayout());
        panelCampos.setBackground(new Color(147, 163, 188));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panelCampos.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelPrincipal.add(panelCampos, BorderLayout.CENTER);

        JPanel panelEtiqueta = new JPanel();
        panelEtiqueta.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelEtiqueta.setBackground(new Color(147, 163, 188));
        panelEtiqueta.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelCampos.add(panelEtiqueta, BorderLayout.NORTH);

        etiquetaUsuario = new JLabel("Login de usuario");
        etiquetaUsuario.setPreferredSize(new Dimension(120, 20));
        etiquetaUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        etiquetaUsuario.setForeground(Color.WHITE);
        panelEtiqueta.add(etiquetaUsuario);

        JPanel panelLlenarCampos = new JPanel();
        panelLlenarCampos.setLayout(new BorderLayout());
        panelLlenarCampos.setBackground(Color.WHITE);
        panelLlenarCampos.setBorder(BorderFactory.createEmptyBorder(30,5,30,5));
        panelLlenarCampos.setPreferredSize(new Dimension(350, 350));
        panelLlenarCampos.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelCampos.add(panelLlenarCampos, BorderLayout.CENTER);

        JPanel panelUsuario = new JPanel();
        panelUsuario.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelUsuario.setBackground(Color.WHITE);
        panelUsuario.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelLlenarCampos.add(panelUsuario, BorderLayout.NORTH);

        panelUsuario.add(agregarImagen( 30, 30, "src/img/iconoUsuario.png"));

        campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        campoUsuario.setForeground(Color.DARK_GRAY);
        campoUsuario.setPreferredSize(new Dimension(250, 30));
        TextPrompt textPromptUsuario = new TextPrompt("Usuario", campoUsuario);
        campoUsuario.add(textPromptUsuario);
        campoUsuario.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        campoUsuario.putClientProperty( "JComponent.roundRect", true );
        panelUsuario.add(campoUsuario);

        JPanel panelContrasena = new JPanel();
        panelContrasena.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelContrasena.setBackground(Color.WHITE);
        panelContrasena.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelLlenarCampos.add(panelContrasena, BorderLayout.CENTER);

        panelContrasena.add(agregarImagen( 30, 30, "src/img/iconoContrasena.png"));

        campoContrasena = new JPasswordField();
        campoContrasena.setFont(new Font("Arial", Font.PLAIN, 16));
        campoContrasena.setForeground(Color.DARK_GRAY);
        campoContrasena.setPreferredSize(new Dimension(250, 30));
        TextPrompt textPromptContrasena = new TextPrompt("Contraseña", campoContrasena);
        campoContrasena.add(textPromptContrasena);
        campoContrasena.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        campoContrasena.putClientProperty( "JComponent.roundRect", true );
        panelContrasena.add(campoContrasena);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        panelLlenarCampos.add(panelBotones, BorderLayout.SOUTH);

        botonLogin = new JButton("Login");
        botonLogin.setBackground(new Color(147, 163, 188));
        botonLogin.setForeground(Color.white);
        botonLogin.setPreferredSize(new Dimension(90, 30));
        botonLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        UIManager.put("botonLogin.focusWidth", 2);
        botonLogin.putClientProperty( "JButton.buttonType", "roundRect" );
        panelBotones.add(botonLogin);

        botonSalir = new JButton("Salir");
        botonSalir.setBackground(new Color(147, 163, 188));
        botonSalir.setForeground(Color.white);
        botonSalir.setPreferredSize(new Dimension(90, 30));
        botonSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonSalir.putClientProperty( "JButton.buttonType", "roundRect" );
        panelBotones.add(botonSalir);


    }

    public void setListeners(ActionListener listener) {
        botonLogin.addActionListener(listener);
        botonSalir.addActionListener(listener);
    }

    public JLabel agregarImagen(int ancho, int largo, String rutaImagen){
        try {
            Image imagen = ImageIO.read(new File(rutaImagen));
            imagen = imagen.getScaledInstance(ancho, largo, Image.SCALE_SMOOTH);
            picLabel = new JLabel(new ImageIcon(imagen));
            return picLabel;
        } catch (IOException e) {
            System.out.println("Imagen no encontrada");
            return null;
        }
    }

    public JTextField getCampoUsuario() {
        return campoUsuario;
    }

    public JPasswordField getCampoContrasena() {
        return campoContrasena;
    }
}
