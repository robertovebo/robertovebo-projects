package controladores;

import modelos.*;
import vistas.VistaInventario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorInventario implements ActionListener {
    private ArrayList<Producto> productos;
    private VistaInventario vistaInventario;
    private ProductosTableModel modeloTablaProducto;
    private TableRowSorter<TableModel> rowSorter;

    public ControladorInventario(VistaInventario vistaInventario) {
        this.vistaInventario = vistaInventario;
        vistaInventario.setListeners(this);

        modeloTablaProducto = vistaInventario.getModeloTablaProducto();

        productos = new ArrayList<Producto>();
        cargarProductos();

        //RowSorter y documentListener de java swing para filtrar busquedas
        rowSorter = new TableRowSorter<>(vistaInventario.getTablaProductos().getModel());
        vistaInventario.getTablaProductos().setRowSorter(rowSorter);

        vistaInventario.getCampoBuscarProducto().getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = vistaInventario.getCampoBuscarProducto().getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = vistaInventario.getCampoBuscarProducto().getText();

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
            case "Agregar producto":
                agregarProducto();
                break;

            case "Eliminar producto":
                if (vistaInventario.getTablaProductos().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(vistaInventario, "Favor de seleccionar una fila");
                    break;
                }

                Producto producto = modeloTablaProducto.getRowData(vistaInventario.getTablaProductos().getSelectedRow());
                Producto.borrarProducto(producto.getId());
                modeloTablaProducto.removeRow(producto);
                break;

            case "Editar producto":
                if (vistaInventario.getTablaProductos().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(vistaInventario, "Favor de seleccionar una fila");
                    break;
                }

                vistaInventario.getEtiquetaOperacionProducto().setText("Editar producto");
                vistaInventario.getOperacionProducto().setText("Guardar cambios");

                llenarCamposProducto();
                break;

            case "Guardar cambios":
                editarProducto();
                vistaInventario.getEtiquetaOperacionProducto().setText("Alta de productos");
                vistaInventario.getOperacionProducto().setText("Agregar producto");
                break;
        }
    }

    public void agregarProducto(){
        try {
            Producto producto = crearProductoDesdeCampos();
            Producto.agregarProducto(producto);
            productos.add(producto);
            modeloTablaProducto.addRow(producto);

            JOptionPane.showMessageDialog(vistaInventario, "Producto agregado con éxito", "Alta de productos", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(vistaInventario, "Ingrese los datos correctamente",
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Producto crearProductoDesdeCampos(){
        Producto producto = new Producto((vistaInventario.getId()), vistaInventario.getCampoNombreProducto().getText(),
                vistaInventario.getCampoMarca().getText(), (Integer.parseInt(vistaInventario.getCampoPrecio().getText())),
                (Integer.parseInt(vistaInventario.getCampoCantidad().getText())));

        return producto;
    }

    public void cargarProductos() {
        productos = Producto.obtenerProducto();

        mostrarProductos();
    }

    public void mostrarProductos() {
        modeloTablaProducto.clearTable();

        for (Producto v : productos) {
            modeloTablaProducto.addRow(v);
        }
    }

    public void llenarCamposProducto() {
        Producto producto = modeloTablaProducto.getRowData(vistaInventario.getTablaProductos().getSelectedRow());

        vistaInventario.setId(producto.getId());
        vistaInventario.setCampoNombreProducto(producto.getNombre());
        vistaInventario.setCampoMarca(producto.getMarca());
        vistaInventario.setCampoPrecio(String.valueOf(producto.getPrecio()));
        vistaInventario.setCampoCantidad(String.valueOf(producto.getCantidad()));

    }

    public void editarProducto() {
        try {
            Producto producto = crearProductoDesdeCampos();

            if(Producto.actualizarProducto(producto)) {
                modeloTablaProducto.setRowData(producto.getId(), producto);
                JOptionPane.showMessageDialog(vistaInventario, "Producto editado con éxito", "Edición", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            }else {
                JOptionPane.showMessageDialog(vistaInventario, "No se pudo editar el producto");
                limpiarCampos();
            }
        }catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaInventario, "Ingrese los datos correctamente",
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarCampos(){
        vistaInventario.getCampoNombreProducto().setText("");
        vistaInventario.getCampoMarca().setText("");
        vistaInventario.getCampoPrecio().setText("");
        vistaInventario.getCampoCantidad().setText("");
    }
}
