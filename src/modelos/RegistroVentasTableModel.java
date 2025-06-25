package modelos;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class RegistroVentasTableModel extends AbstractTableModel {
    private ArrayList<Venta> datos = new ArrayList<Venta>();
    private String[] nombresColumnas =
            {"Nombre del producto", "Unidades", "Precio", "Total"};

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
            case 1:
                return Integer.class;
            case 2:
            case 3:
                return Double.class;
        }

        return String.class;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Venta venta = datos.get(rowIndex);

        try {
            switch(columnIndex) {
                case 0:
                    venta.setNombre(String.valueOf(value));
                    break;
                case 1:
                    int unidades = Integer.parseInt(value.toString());
                    if(unidades > 0) {
                        venta.setUnidades(unidades);
                    }else {
                        throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
                    }
                    break;
                case 2:
                    int precio = Integer.parseInt(value.toString());
                    if(precio > 0) {
                        venta.setPrecio(precio);
                    }else {
                        throw new IllegalArgumentException("El precio debe ser mayor a 0");
                    }
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Venta venta = datos.get(rowIndex);

        switch(columnIndex) {
            case 0:
                return venta.getNombre();
            case 1:
                return venta.getUnidades();
            case 2:
                return venta.getPrecio();
            case 3:
                return venta.getUnidades() * venta.getPrecio();
        }

        return null;
    }

    public void addRow(Venta venta) {
        datos.add(venta);
        fireTableRowsInserted(datos.size() - 1, datos.size() - 1);
    }

    public void removeRow(Venta venta){
        datos.remove(venta);
        fireTableRowsInserted(datos.size() - 1, datos.size() - 1);
    }

    public Venta getRowData(int row) {
        return datos.get(row);
    }

    public void setRowData(int id, Venta v) {
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
