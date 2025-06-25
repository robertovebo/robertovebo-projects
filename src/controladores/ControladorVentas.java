package controladores;

import modelos.*;
import vistas.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ControladorVentas implements ActionListener {
    private VistaPrincipal vistaPrincipal;
    private VistaVentas vistaVentas;
    private VistaInventario vistaInventario;
    private VistaClientes vistaClientes;
    private VistaAjustes vistaAjustes;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Venta> ventas;
    private ArrayList<Producto> productos;
    private ArrayList<Cliente> clientes;
    private VentasTableModel modeloTablaVenta;
    private RegistroVentasTableModel modeloTablaRegistroVenta;
    private ProductosTableModel modeloTablaProducto;
    private ClientesTableModel modeloTablaCliente;
    private TableRowSorter<TableModel> rowSorter;

    public ControladorVentas(VistaVentas vistaVentas) {
        this.vistaVentas = vistaVentas;
        vistaVentas.setListeners(this);

        modeloTablaVenta = vistaVentas.getModeloTablaVenta();
        modeloTablaRegistroVenta = new RegistroVentasTableModel();

        ventas = new ArrayList<Venta>();

        productos = new ArrayList<Producto>();
        productos = Producto.obtenerProducto();

        clientes = new ArrayList<Cliente>();
        clientes = Cliente.obtenerClientes();

        vistaVentas.asignarJTextFieldListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                for (int i = 0; i < clientes.size(); i++) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER && vistaVentas.getClienteVenta().getText().equalsIgnoreCase(clientes.get(i).getNombre())) {
                        vistaVentas.repaint();
                        vistaVentas.revalidate();
                        vistaVentas.setClienteVenta(clientes.get(i).getNombre());
                    }
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch(command) {
            case "Agregar venta":
                comprobarProducto();
                break;

            case "Editar venta":
                if (vistaVentas.getTablaVentas().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(vistaVentas, "Favor de seleccionar una fila");
                    break;
                }

                editarVenta();
                break;

            case "Eliminar venta":
                if (vistaVentas.getTablaVentas().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(vistaVentas, "Favor de seleccionar una fila");
                    break;
                }

                borrarVenta();
                break;

            case "-":
                if (Integer.parseInt(vistaVentas.getCampoCantidad().getText()) > 0) {
                    vistaVentas.getCampoCantidad().setText(String.valueOf((Integer.parseInt(vistaVentas.getCampoCantidad().getText())) - 1));
                }
                break;

            case "+":
                vistaVentas.getCampoCantidad().setText(String.valueOf((Integer.parseInt(vistaVentas.getCampoCantidad().getText())) + 1));
                break;

            case "Realizar compra":
                realizarCompra();
                break;
        }
    }

    public void comprobarProducto(){
        for (int i = 0; i < productos.size(); i++) {
            if ((productos.get(i).getNombre().equalsIgnoreCase(vistaVentas.getCampoProducto().getText()) || String.valueOf(productos.get(i).getId()).equals(vistaVentas.getCampoProducto().getText())) && ((Integer.parseInt(vistaVentas.getCampoCantidad().getText())) <= productos.get(i).getCantidad())){
                agregarVentaDesdeProducto(productos.get(i));
                return;
            } else if ((i + 1) == productos.size()) {
                JOptionPane.showMessageDialog(vistaVentas, "El nombre es incorrecto o las unidades excenden la cantidad disponible", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void agregarVentaDesdeProducto(Producto producto){
        Venta venta = new Venta(producto.getNombre(), (Integer.parseInt(vistaVentas.getCampoCantidad().getText())), producto.getPrecio(), 0, vistaVentas.getId());
        Venta.agregarVenta(venta);
        ventas.add(venta);
        modeloTablaVenta.addRow(venta);

        limpiarCampos();
    }

    public void editarVenta(){
        Venta venta = modeloTablaVenta.getRowData(vistaVentas.getTablaVentas().getSelectedRow());
        vistaVentas.getCampoProducto().setText(venta.getNombre());
        vistaVentas.getCampoCantidad().setText(String.valueOf(venta.getUnidades()));
        Venta.borrarVenta(venta.getId());
        modeloTablaVenta.removeRow(venta);
    }

    public void borrarVenta(){
        Venta ventaEliminada = modeloTablaVenta.getRowData(vistaVentas.getTablaVentas().getSelectedRow());
        Venta.borrarVenta(ventaEliminada.getId());
        modeloTablaVenta.removeRow(ventaEliminada);
    }

    public void realizarCompra(){
        if (!ventas.isEmpty()){
            for (int i = 0; i < ventas.size(); i++) {
                Venta.agregarRegistroVenta(ventas.get(i));
                modeloTablaRegistroVenta.addRow(ventas.get(i));

                for (int j = 0; j < productos.size(); j++) {
                    if ((productos.get(j).getNombre().equalsIgnoreCase(ventas.get(i).getNombre()))) {
                        Producto producto = new Producto(productos.get(j).getId(), productos.get(j).getNombre(), productos.get(j).getMarca(),
                                productos.get(j).getPrecio(), (productos.get(j).getCantidad() - ventas.get(i).getUnidades()));

                        Producto.actualizarProducto(producto);
                    }
                }

                ventas.remove(i);
            }
            Venta.borrarDatosTablaVenta();
            modeloTablaVenta.clearTable();
            JOptionPane.showMessageDialog(vistaVentas, "Gracias por su compra", "Compra realizada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vistaVentas, "Favor de agregar como mínimo un producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarCampos(){
        vistaVentas.getCampoProducto().setText("");
        vistaVentas.getCampoCantidad().setText("0");
    }
}
