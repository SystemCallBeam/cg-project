import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.*;

public class MyPicture extends JPanel {
    public static final int WIDTH = 600, HEIGHT = 600;

    public static Random rand = new Random();
    public static Color randomColor[] = new Color[10];

    public static void main(String[] args) {
        MyPicture g = new MyPicture();
        JFrame f = new JFrame("My Picture");
        for (int i = 0; i < 10; i++) {
            randomColor[i] = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        }
        f.setSize(WIDTH, HEIGHT);
        f.add(g);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    // (Fill, Draw) (Rect, Oval, Line, Arc)
    public void paintComponent(Graphics g) {
        BufferedImage buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = buffer.createGraphics();

        int x[] = { 590, 250, 1, 450, 299, 299, 448, 48, 150, 598 };
        int y[] = { 200, 250, 1, 150, 299, 450, 448, 330, 510, 598 };

        for (int i = 0; i < 10; i++) {

            g2.setColor(randomColor[i]);
            // plot(g, x[i], y[i]);
            // line(g, x[i], y[i], x[(i + 1) % 10], y[(i + 1) % 10]);
            bezier(g2, x[i], y[i], x[(i + 1) % 10], y[(i + 1) % 10], x[(i + 2) % 10],
                    y[(i + 2) % 10], x[(i + 3) % 10], y[(i + 3) % 10]);
        }

        buffer = floodFill(buffer, 100, 50, Color.white, Color.red);
        buffer = floodFill(buffer, 100, 100, Color.white, Color.blue);

        g.drawImage(buffer, 0, 0, null);

    }

    public void curve(Graphics g, int x, int y, int width, int height, int sttang, int arcang) {
        g.drawArc(x, y, width, height, sttang, arcang);
        // drawArc(g, x, y, width, height, sttang, arcang)
    }

    public void bezier(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        float n = 2000;
        float t = 0;
        for (; t < 1; t += (1 / n)) {
            double x = (Math.pow(1 - t, 3) * x1) + (3 * t * Math.pow(1 - t, 2) * x2)
                    + (3 * Math.pow(t, 2) * (1 - t) * x3) + (Math.pow(t, 3) * x4);
            double y = (Math.pow(1 - t, 3) * y1) + (3 * t * Math.pow(1 - t, 2) * y2)
                    + (3 * Math.pow(t, 2) * (1 - t) * y3) + (Math.pow(t, 3) * y4);
            plot(g, (int) x, (int) y);
        }
    }

    public BufferedImage floodFill(BufferedImage m, int x, int y,
            Color target_colour, Color replacement_colour) {
        Graphics2D g2 = m.createGraphics();
        Queue<Point> q = new LinkedList<>();

        if (m.getRGB(x, y) == target_colour.getRGB()) {
            g2.setColor(replacement_colour);
            plot(g2, x, y, 1);
            q.add(new Point(x, y));
        }

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.y < HEIGHT && m.getRGB(p.x, p.y + 1) == target_colour.getRGB()) {
                g2.setColor(replacement_colour);
                plot(g2, p.x, p.y + 1, 1);
                q.add(new Point(p.x, p.y + 1));
            }
            if (p.y > 0 && m.getRGB(p.x, p.y - 1) == target_colour.getRGB()) {
                g2.setColor(replacement_colour);
                plot(g2, p.x, p.y - 1, 1);
                q.add(new Point(p.x, p.y - 1));
            }
            if (p.x < WIDTH && m.getRGB(p.x + 1, p.y) == target_colour.getRGB()) {
                g2.setColor(replacement_colour);
                plot(g2, p.x + 1, p.y, 1);
                q.add(new Point(p.x + 1, p.y));
            }
            if (p.y > 0 && m.getRGB(p.x - 1, p.y) == target_colour.getRGB()) {
                g2.setColor(replacement_colour);
                plot(g2, p.x - 1, p.y, 1);
                q.add(new Point(p.x - 1, p.y));
            }
        }
        return m;
    }

    public void drawArc(Graphics g, int x, int y, int width, int height, int sttang, int arcang) {
        plot(g, x, y);
    }

    public void line(Graphics g, int x1, int y1, int x2, int y2) {
        // g.drawLine(x1, y1, x2, y2);
        // dda(g, x1, y1, x2, y2);
        bresenham(g, x1, y1, x2, y2);
    }

    public void plot(Graphics g, int x, int y) {
        plot(g, x, y, 1);
    }

    public void plot(Graphics g, int x, int y, int s) {
        g.fillRect(x, y, s, s);
    }

    public void dda(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int step = Math.max(Math.abs(dx), Math.abs(dy));
        float x = x1;
        float y = y1;
        float xinc = dx / (float) step;
        float yinc = dy / (float) step;

        for (int i = 0; i < step; i++) {
            plot(g, (int) x, (int) y);
            x += xinc;
            y += yinc;
        }
    }

    public void bresenham(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;
        boolean isSwap = false;

        if (dy > dx) {
            int tmp = dx;
            dx = dy;
            dy = tmp;
            isSwap = true;
        }
        int d = 2 * dy - dx;
        int x = x1;
        int y = y1;

        for (int i = 0; i < dx; i++) {
            plot(g, x, y);

            if (d >= 0) {
                if (isSwap)
                    x += sx;
                else
                    y += sy;

                d -= 2 * dx;
            }
            if (isSwap)
                y += sy;
            else
                x += sx;

            d += 2 * dy;
        }
    }
}