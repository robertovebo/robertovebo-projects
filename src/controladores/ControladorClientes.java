package controladores;

import modelos.Cliente;
import modelos.ClientesTableModel;
import vistas.VistaClientes;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorClientes implements ActionListener {
    private ArrayList<Cliente> clientes;
    private VistaClientes vistaClientes;
    private ClientesTableModel modeloTablaCliente;
    private TableRowSorter<TableModel> rowSorter;

    public ControladorClientes(VistaClientes vistaClientes) {
        this.vistaClientes = vistaClientes;
        vistaClientes.setListeners(this);

        modeloTablaCliente = vistaClientes.getModeloTablaCliente();

        clientes = new ArrayList<Cliente>();
        cargarClientes();

        //RowSorter y documentListener de java swing para filtrar busquedas
        rowSorter = new TableRowSorter<>(vistaClientes.getTablaClientes().getModel());
        vistaClientes.getTablaClientes().setRowSorter(rowSorter);

        vistaClientes.getCampoBuscarCliente().getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = vistaClientes.getCampoBuscarCliente().getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = vistaClientes.getCampoBuscarCliente().getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch(command){
            case "Agregar cliente":
                agregarCliente();
                break;

            case "Eliminar cliente":
                if (vistaClientes.getTablaClientes().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(vistaClientes, "Favor de seleccionar una fila");
                    break;
                }

                Cliente cliente = modeloTablaCliente.getRowData(vistaClientes.getTablaClientes().getSelectedRow());
                Cliente.borrarCliente(cliente.getId());
                modeloTablaCliente.removeRow(cliente);
                break;

            case "Editar cliente":
                if (vistaClientes.getTablaClientes().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(vistaClientes, "Favor de seleccionar una fila");
                    break;
                }

                vistaClientes.getEtiquetaOperacionCliente().setText("Editar cliente");
                vistaClientes.getOperacionCliente().setText("Guardar cambios");

                llenarCamposCliente();
                break;

            case "Guardar cambios":
                editarCliente();
                vistaClientes.getEtiquetaOperacionCliente().setText("Alta de clientes");
                vistaClientes.getOperacionCliente().setText("Agregar cliente");
                break;
        }
    }

    public void agregarCliente(){
        try {
            Cliente cliente = crearClienteDesdeCampos();
            Cliente.agregarCliente(cliente);
            clientes.add(cliente);
            modeloTablaCliente.addRow(cliente);

            JOptionPane.showMessageDialog(vistaClientes, "Cliente agregado con éxito", "Alta de clientes", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(vistaClientes, "Ingrese los datos correctamente",
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Cliente crearClienteDesdeCampos(){
        Cliente cliente = new Cliente(vistaClientes.getCampoNombreCliente().getText(),
                vistaClientes.getCampoApellido().getText(), vistaClientes.getCampoRfc().getText(),
                (vistaClientes.getCampoCorreo().getText()), vistaClientes.getId());

        return cliente;
    }

    public void cargarClientes() {
        clientes = Cliente.obtenerClientes();

        mostrarClientes();
    }

    public void mostrarClientes() {
        modeloTablaCliente.clearTable();

        for (Cliente v : clientes) {
            modeloTablaCliente.addRow(v);
        }
    }

    public void llenarCamposCliente() {
        Cliente cliente = modeloTablaCliente.getRowData(vistaClientes.getTablaClientes().getSelectedRow());

        vistaClientes.setId(cliente.getId());
        vistaClientes.setCampoNombreCliente(cliente.getNombre());
        vistaClientes.setCampoApellido(cliente.getApellido());
        vistaClientes.setCampoRfc(cliente.getRfc());
        vistaClientes.setCampoCorreo(cliente.getCorreo());

    }

    public void editarCliente() {
        try {
            Cliente cliente = crearClienteDesdeCampos();

            if(Cliente.actualizarCliente(cliente)) {
                modeloTablaCliente.setRowData(cliente.getId(), cliente);
                JOptionPane.showMessageDialog(vistaClientes, "Cliente editado con éxito", "Edición", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            }else {
                JOptionPane.showMessageDialog(vistaClientes, "No se pudo editar el cliente");
                limpiarCampos();
            }
        }catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaClientes, "Ingrese los datos correctamente",
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarCampos(){
        vistaClientes.getCampoNombreCliente().setText("");
        vistaClientes.getCampoApellido().setText("");
        vistaClientes.getCampoRfc().setText("");
        vistaClientes.getCampoCorreo().setText("");
    }
}
