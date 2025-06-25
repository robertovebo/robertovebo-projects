package controladores;

import modelos.Usuario;
import vistas.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorLogin implements ActionListener {
    private VistaLogin vistaLogin;
    private VistaPrincipal vistaPrincipal;
    private VistaVentas vistaVentas;
    private ArrayList<Usuario> usuarios;

    public ControladorLogin(VistaLogin vistaLogin) {
        this.vistaLogin = vistaLogin;
        vistaLogin.setListeners(this);

        usuarios = Usuario.obtenerUsuario();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch(command) {
            case "Login":
                if (loginUsuario()){
                    vistaLogin.dispose();
                    abrirVentas();
                } else {
                    JOptionPane.showMessageDialog(vistaLogin, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "Salir":
                System.exit(0);
                break;
        }
    }

    public boolean loginUsuario(){
        for (int i = 0; i < usuarios.size(); i++) {
            if ((usuarios.get(i).getNombreUsuario().equals(vistaLogin.getCampoUsuario().getText())) && (usuarios.get(i).getContrasena().equals(vistaLogin.getCampoContrasena().getText()))){
                return true;
            }
        }

        return false;
    }

    public void abrirVentas(){
        vistaPrincipal = new VistaPrincipal();
        vistaPrincipal.setListeners(this);
        vistaPrincipal.setLocationRelativeTo(null);
        vistaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vistaPrincipal.setTitle("Ventas");
        vistaPrincipal.setExtendedState(Frame.MAXIMIZED_BOTH);
        vistaPrincipal.setVisible(true);

        VistaVentas vistaVentas = new VistaVentas();

        ControladorVentas controladorVentas = new ControladorVentas(vistaVentas);
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal(vistaPrincipal);
    }
}
