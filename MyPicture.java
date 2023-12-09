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
        for (int i = 0; i < 10; i++) {

            g.setColor(randomColor[i]);
            plot(g, x[i], y[i]);
        }
        curve(g, FRAMEBITS, ERROR, WIDTH, HEIGHT, ALLBITS, ABORT);
    }

    public void curve(Graphics g, int x, int y, int width, int height, int sttang, int arcang) {
        g.drawArc(x, y, width, height, sttang, arcang);
        // drawArc(g, x, y, width, height, sttang, arcang)
    }

    public void drawArc(Graphics g, int x, int y, int width, int height, int sttang, int arcang) {
        plot(g, x, y);
    }

    public void line(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);
        // drawLine(g, x1, y1, x2, y2);
    }

    public void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        plot(g, x2, y2);
    }

    public void plot(Graphics g, int x, int y) {
        plot(g, x, y, 8);
    }

    public void plot(Graphics g, int x, int y, int s) {
        g.fillRect(x, y, s, s);
    }

    public void dda(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int step = Math.max(Math.abs(dx), Math.abs(dy));
        int x = x1;
        int y = y1;
        int xinc = dx/step;
        int yinc = dy/step;

        for (int i = 0; i < step; i++) {
            plot(g, x, y);
            x += xinc;
            y += yinc;
        }
    }
}