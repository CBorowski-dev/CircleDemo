package de.borowski.graphics;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Implementation of <a href="https://algo.rwth-aachen.de/~algorithmus/algo32.php">Kreise zeichnen mit Turbo</a>
 */
public class CircleDemo {

    public static final int X_RESOLUTION = 60;
    public static final int Y_RESOLUTION = 60;
    public static final int RADIUS = 28;

    private DrawPanel panel;

    public CircleDemo(DrawPanel panel){
        this.panel = panel;
        try {
            // drawCircleNaive();
            // drawCircleSymmetrie();
            drawCircleBresenham();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void drawCircleNaive() throws InterruptedException {
        int pixelNeeded = 7 * RADIUS;

        for (int j=0; j < pixelNeeded; j++) {
            // (x, y) = (R * cos(angle), R * sin(angle))
            double angleRad = 2.0 * Math.PI * j/pixelNeeded;
            int x = (int) (RADIUS * Math.cos(angleRad));
            int y = (int) (RADIUS * Math.sin(angleRad));

            panel.drawPixel(x, y);
            Thread.sleep(100);
        }
    }

    private void drawCircleSymmetrie() throws InterruptedException {
        int pixelNeeded = 7 * RADIUS;

        for (int j=0; j < pixelNeeded; j++) {
            // (x, y) = (R * cos(angle), R * sin(angle))
            double angleRad = 2.0 * Math.PI * j/pixelNeeded;
            int x = (int) (RADIUS * Math.cos(angleRad));
            int y = (int) (RADIUS * Math.sin(angleRad));

            panel.drawPixel(x, y); // 0°-45°
            panel.drawPixel(x, -y); // 360°-315°
            panel.drawPixel(-x, y); // 180°-135°
            panel.drawPixel(-x, -y); // 180°-225°

            panel.drawPixel(y, x); // ToDo
            panel.drawPixel(y, -x); // ToDo
            panel.drawPixel(-y, x); // ToDo
            panel.drawPixel(-y, -x); // ToDo
            Thread.sleep(100);
        }
    }

    private void drawCircleBresenham() throws InterruptedException {
        int y = RADIUS;
        int F = 1 - RADIUS;
        for (int x = 0; x < y; x++) {
            if (F < 0) {
                // Osten
                F += 2 * x - 1;
            } else {
                // Südosten
                F += 2 * (x - y);
                y--;
            }
            panel.drawPixel(x, y); // 0°-45°
            panel.drawPixel(x, -y); // 360°-315°
            panel.drawPixel(-x, y); // 180°-135°
            panel.drawPixel(-x, -y); // 180°-225°

            panel.drawPixel(y, x); // ToDo
            panel.drawPixel(y, -x); // ToDo
            panel.drawPixel(-y, x); // ToDo
            panel.drawPixel(-y, -x); // ToDo
            Thread.sleep(100);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kreis Demo");
        DrawPanel panel = new DrawPanel(CircleDemo.X_RESOLUTION, CircleDemo.Y_RESOLUTION);
        frame.add(panel);
        frame.setSize(820, 880);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);

        new CircleDemo(panel);
    }
}