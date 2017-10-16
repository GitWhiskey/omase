package com.mkhabrat.omase.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by Maxon on 15.10.2017.
 */
public class AreaTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    @SuppressWarnings("all")
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        //Cells are by default rendered as a JLabel.
        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        //Get the status for the current row.
        AreaTableModel tableModel = (AreaTableModel) table.getModel();
        //List<DomainObject> dos = (List<DomainObject>) tableModel.getValueAt(row, col);
        if (tableModel.cellContainsTrailSegment(row, col)) {
            l.setBackground(Color.MAGENTA);
            l.setText("");
        }
        if (tableModel.cellContainsResources(row, col)) {
            l.setBackground(Color.GREEN);
            String resoureCount = Integer.toString(tableModel.getResourceCountAtCell(row, col));
            l.setText(resoureCount);
        }
        if (tableModel.cellContainsAgent(row, col)) {
            l.setBackground(Color.RED);
            String agentId = Integer.toString(tableModel.getAgentIdAtCell(row, col));
            l.setText(agentId);
        }
        if (
            !tableModel.cellContainsTrailSegment(row, col)
            && !tableModel.cellContainsResources(row, col)
            && !tableModel.cellContainsAgent(row, col)) {
            l.setBackground(Color.WHITE);
        }

        //Return the JLabel which renders the cell.
        return l;

    }
}
