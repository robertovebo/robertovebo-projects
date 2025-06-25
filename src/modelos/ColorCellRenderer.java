package modelos;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ColorCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if(column >= 3) {
            c.setBackground(new Color(202, 229, 255));
        }else {
            c.setBackground(Color.WHITE);
        }

        if(isSelected) {
            c.setBackground(table.getSelectionBackground());
            c.setForeground(table.getSelectionForeground());
        }else {
            c.setForeground(Color.BLACK);
        }

        return c;
    }

}
