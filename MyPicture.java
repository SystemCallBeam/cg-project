import java.awt.*;
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
        int x[] = { 590, 250, 1, 450, 299, 299, 448, 48, 150, 598 };
        int y[] = { 200, 250, 1, 150, 299, 450, 448, 330, 510, 598 };
        /* for (int i = 0; i < 10; i++) {

            g.setColor(randomColor[i]);
            plot(g, x[i], y[i]);
            line(g, x[i], y[i], x[(i + 1) % 10], y[(i + 1) % 10]);
        } */
        line(g, 100, 100, 400, 200);
        line(g, 400, 300, 100, 200);
        line(g, 100, 100, 200, 400);
    }

    public void curve(Graphics g, int x, int y, int width, int height, int sttang, int arcang) {
        g.drawArc(x, y, width, height, sttang, arcang);
        // drawArc(g, x, y, width, height, sttang, arcang)
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