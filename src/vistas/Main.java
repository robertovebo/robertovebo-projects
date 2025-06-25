package vistas;

import com.formdev.flatlaf.FlatLightLaf;
import controladores.ControladorLogin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        UIManager.put("Component.arc", 999);
        UIManager.put("ProgressBar.arc", 999);

        VistaLogin vistaLogin = new VistaLogin();
        vistaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vistaLogin.setTitle("Login de usuarios");
        vistaLogin.setSize(800, 450);
        vistaLogin.setLocationRelativeTo(null);
        vistaLogin.setResizable(false);

        try {
            BufferedImage iconoQuetzal = ImageIO.read(new File("src/img/iconoQuetzal.png"));
            vistaLogin.setIconImage(iconoQuetzal);
        } catch (IOException e) {
            System.out.println("Icono no encontrado");
        }

        ControladorLogin controladorLogin = new ControladorLogin(vistaLogin);

        vistaLogin.setVisible(true);


    }
}