package de.borowski.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    private List<Coordinate> pixels = new ArrayList<>();

    private int x_size, y_size;
    public DrawPanel(int x_size, int y_size) {
        this.x_size = x_size;
        this.y_size = y_size;
    }

    public void paintComponent(Graphics g) {
        // draw grid
        Dimension d = getSize();
        // System.out.println(d.width + " " + d.height);
        float x_width = ((float)d.width) / x_size;
        for (float x = 0; x<d.width; x += x_width) {
            g.drawLine((int)x, 0, (int)x, d.height);
        }
        float y_height = ((float) d.height) / y_size;
        for (float y = 0; y<d.height; y += y_height) {
            g.drawLine(0, (int)y, d.width, (int)y);
        }
        // draw pixels
        for (Coordinate c : pixels) {
            g.fillRect((int) (c.x * x_width), (int) (c.y * y_height), (int)x_width + 1, (int)y_height + 1);
        }
    }

    public void drawPixel(int x, int y) {
        pixels.add(new Coordinate(x + x_size/2, y + y_size/2));
        repaint();
    }
}
