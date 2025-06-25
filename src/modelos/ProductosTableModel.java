package modelos;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProductosTableModel extends AbstractTableModel {
    private ArrayList<Producto> datos = new ArrayList<Producto>();
    private String[] nombresColumnas =
            {"Id del producto", "Nombre del producto", "Marca", "Precio", "Cantidad"};

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return nombresColumnas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch(columnIndex) {
            case 0:
                return Integer.class;
            case 2:
            case 3:
        }

        return String.class;
    }

    /*@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 4) {
            return true;
        }

        return false;
    }*/

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Producto producto = datos.get(rowIndex);

        try {
            switch(columnIndex) {
                case 0:
                    int id = Integer.parseInt(value.toString());
                    producto.setId(id);
                case 1:
                    producto.setNombre(String.valueOf(value));
                    break;
                case 2:
                    producto.setMarca(String.valueOf(value));
                    break;
                case 3:
                    int precio = Integer.parseInt(value.toString());
                    if(precio > 0) {
                        producto.setPrecio(precio);
                    }else {
                        throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
                    }
                    break;
                case 4:
                    int cantidad = Integer.parseInt(value.toString());
                    if(cantidad > 0) {
                        producto.setPrecio(cantidad);
                    }else {
                        throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
                    }
                    break;

            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Producto producto = datos.get(rowIndex);

        switch(columnIndex) {
            case 0:
                return producto.getId();
            case 1:
                return producto.getNombre();
            case 2:
                return producto.getMarca();
            case 3:
                return producto.getPrecio();
            case 4:
                return producto.getCantidad();
        }

        return null;
    }

    public void addRow(Producto producto) {
        datos.add(producto);
        fireTableRowsInserted(datos.size() - 1, datos.size() - 1);
    }

    public void removeRow(Producto producto){
        datos.remove(producto);
        fireTableRowsInserted(datos.size() - 1, datos.size() - 1);
    }

    public Producto getRowData(int row) {
        return datos.get(row);
    }

    public void setRowData(int id, Producto v) {
        int index = getRowById(id);
        if(index == -1) {
            return;
        }
        datos.set(index, v);
        fireTableRowsUpdated(index, index);
    }

    public int getRowById(int id) {
        for(int i = 0; i < datos.size(); i++) {
            if(datos.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void clearTable() {
        datos.clear();
    }

}
