package com.mkhabrat.omase.ui;

import com.mkhabrat.omase.domain.astar.DomainNode;
import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import com.mkhabrat.omase.domain.original.dos.Resource;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * Created by Maxon on 15.10.2017.
 */
public class AreaTableModel implements TableModel {

    private Area area;

    public AreaTableModel(Area area) {
        this.area = area;
    }

    @Override
    public int getRowCount() {
        return area.getHeight();
    }

    @Override
    public int getColumnCount() {
        return area.getWidth();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return DomainNode.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return area.getAllDomainObjectsAtPosition(new Position(columnIndex, rowIndex));
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("You cannot change values in this model.");
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    public boolean cellContainsAgent(int rowIndex, int columnIndex) {
        return area.positionHasAgent(new Position(columnIndex, rowIndex));
    }

    public int getAgentIdAtCell(int rowIndex, int columnIndex) {
        Agent r = (Agent) area.getDomainObjectAtPosition(new Position(columnIndex, rowIndex), Agent.class);
        return r.getId();
    }

    public boolean cellContainsResources(int rowIndex, int columnIndex) {
        return area.positionHasResources(new Position(columnIndex, rowIndex));
    }

    public int getResourceCountAtCell(int rowIndex, int columnIndex) {
        Resource r = (Resource) area.getDomainObjectAtPosition(new Position(columnIndex, rowIndex), Resource.class);
        return r.getQuantity();
    }

    public boolean cellContainsTrailSegment(int rowIndex, int columnIndex) {
        return area.positionHasTrailSegment(new Position(columnIndex, rowIndex));
    }

    public boolean cellContainsBase(int rowIndex, int columnIndex) {
        return area.positionHasBase(new Position(columnIndex, rowIndex));
    }
}
