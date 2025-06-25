package controladores;

import modelos.Cliente;
import modelos.Usuario;
import vistas.VistaAjustes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAjustes implements ActionListener {
    private VistaAjustes vistaAjustes;

    public ControladorAjustes(VistaAjustes vistaAjustes) {
        this.vistaAjustes = vistaAjustes;
        vistaAjustes.setListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Agregar usuario":
                Usuario usuario = new Usuario(vistaAjustes.getCampoUsuario().getText(), vistaAjustes.getCampoContrasena().getText(), vistaAjustes.getId());
                Usuario.agregarUsuario(usuario);

                JOptionPane.showMessageDialog(vistaAjustes, "Usuario agregado con éxito", "Alta de usuarios", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                break;
        }
    }

    public void limpiarCampos(){
        vistaAjustes.getCampoUsuario().setText("");
        vistaAjustes.getCampoContrasena().setText("");
        vistaAjustes.getCampoConfirmarContra().setText("");
    }
}
