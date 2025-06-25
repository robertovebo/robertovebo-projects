package modelos;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ClientesTableModel extends AbstractTableModel {
    private ArrayList<Cliente> datos = new ArrayList<Cliente>();
    private String[] nombresColumnas =
            {"Nombre(s)", "Apellido", "Rfc", "Correo electrónico"};

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
        return String.class;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Cliente cliente = datos.get(rowIndex);

        try {
            switch(columnIndex) {
                case 0:
                    cliente.setNombre(String.valueOf(value));
                    break;
                case 1:
                    cliente.setApellido(String.valueOf(value));
                    break;
                case 2:
                    cliente.setRfc(String.valueOf(value));
                    break;
                case 3:
                    cliente.setCorreo(String.valueOf(value));
                    break;

            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Cliente cliente = datos.get(rowIndex);

        switch(columnIndex) {
            case 0:
                return cliente.getNombre();
            case 1:
                return cliente.getApellido();
            case 2:
                return cliente.getRfc();
            case 3:
                return cliente.getCorreo();
        }

        return null;
    }

    public void addRow(Cliente cliente) {
        datos.add(cliente);
        fireTableRowsInserted(datos.size() - 1, datos.size() - 1);
    }

    public void removeRow(Cliente cliente){
        datos.remove(cliente);
        fireTableRowsInserted(datos.size() - 1, datos.size() - 1);
    }

    public Cliente getRowData(int row) {
        return datos.get(row);
    }

    public void setRowData(int id, Cliente v) {
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
