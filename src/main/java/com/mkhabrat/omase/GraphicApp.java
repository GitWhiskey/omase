package com.mkhabrat.omase;

import com.mkhabrat.omase.ui.AreaTableCellRenderer;
import com.mkhabrat.omase.ui.AreaTableModel;
import com.mkhabrat.omase.utils.EnumerationUtils;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;


/**
 * Created by Maxon on 14.10.2017.
 *
 * Расширение консольного App, добавляющее графику в приложение.
 */
@Slf4j
public class GraphicApp extends App {

    private JTable areaTable;

    @Override
    public void init() {
        super.init();
        initGraphics();
    }

    private void initGraphics() {
        int wWidth = area.getWidth() * (Settings.PIXELS_PER_MAP_TILE + 1);
        int wHeight = area.getHeight() * (Settings.PIXELS_PER_MAP_TILE + 4);

        JFrame window = new JFrame();
        window.setTitle("OMaSE Agents");
        window.setSize(wWidth, wHeight);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        areaTable = new JTable(new AreaTableModel(area));
        EnumerationUtils.forEachRemaining(
                areaTable.getColumnModel().getColumns(),
                tableColumn -> tableColumn.setCellRenderer(new AreaTableCellRenderer())
        );
        areaTable.setRowHeight(Settings.PIXELS_PER_MAP_TILE);
        window.add(areaTable);

        window.setVisible(true);
    }

    @Override
    protected void makeIteration() {
        super.makeIteration();
        redrawArea();
    }

    private void redrawArea() {
        areaTable.repaint();
    }

    public static void main(String[] args) {
        GraphicApp gApp = new GraphicApp();
        gApp.run();
    }
}
