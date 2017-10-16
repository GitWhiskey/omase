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

//    private void initGraphics() {
//        boolean graphicsInitialized = GLFW.glfwInit();
//        if (!graphicsInitialized) {
//            log.error("Graphics module no initialized. Aborting.");
//            throw new RuntimeException();
//        }
//
//        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_TRUE);
//        int wWidth = area.getWidth() * Settings.PIXELS_PER_MAP_TILE;
//        int wHeight = area.getHeight() * Settings.PIXELS_PER_MAP_TILE;
//        window = GLFW.glfwCreateWindow(wWidth, wHeight, "Agents", MemoryUtil.NULL, MemoryUtil.NULL);
//
//        long primaryMonitor = GLFW.glfwGetPrimaryMonitor();
//        GLFWVidMode vidmode = GLFW.glfwGetVideoMode(primaryMonitor);
//        int windowXPos = (vidmode.width() - wWidth) / 2;
//        int windowYPos = (vidmode.height() - wHeight) / 2;
//        GLFW.glfwSetWindowPos(window, windowXPos, windowYPos);
//
//        GLFW.glfwMakeContextCurrent(window);
//        GLFW.glfwShowWindow(window);
//
//        GL.createCapabilities();
//
//        // init OpenGL
//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadIdentity();
//        GL11.glOrtho(0, wWidth, 0, wHeight, 1, -1);
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);
//    }

    private void initGraphics() {
        int wWidth = area.getWidth() * Settings.PIXELS_PER_MAP_TILE;
        int wHeight = area.getHeight() * Settings.PIXELS_PER_MAP_TILE;

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
//        // clear the screen and depth buffer
//        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
//        // Отрисовываем квадраты карты
//        for (int i = 0; i < area.getWidth(); i++) {
//            for (int j = 0; j < area.getHeight(); j++) {
//
//                if (area.positionHasResources(new Position(i, j))) {
//                    // set the color of the quad (R,G,B,A)
//                    GL11.glColor3i(1, 1, 1);
//                } else {
//                    // set the color of the quad (R,G,B,A)
//                    GL11.glColor3i(0, 0, 0);
//                }
//
//                int tileSize = Settings.PIXELS_PER_MAP_TILE;
//                int drawingStartX = i * tileSize;
//                int drawingStartY = j * tileSize;
//
//                GL11.glBegin(GL11.GL_QUADS);
//                GL11.glVertex2d(drawingStartX, drawingStartY);
//                GL11.glVertex2d(drawingStartX, drawingStartY + tileSize);
//                GL11.glVertex2d(drawingStartX + tileSize, drawingStartY + tileSize);
//                GL11.glVertex2d(drawingStartX + tileSize, drawingStartY);
//                GL11.glEnd();
//            }
//        }
//        // Обновляем картинку в окне
//        GLFW.glfwSwapBuffers(window);
    }

    public static void main(String[] args) {
        GraphicApp gApp = new GraphicApp();
        gApp.run();
    }
}
