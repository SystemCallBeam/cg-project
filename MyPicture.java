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

    public void paintComponent(Graphics g) {
        int x[] = { 590, 250, 1, 450, 299, 299, 448, 48, 150, 598 };
        int y[] = { 200, 250, 1, 150, 299, 450, 448, 330, 510, 598 };
        for (int i = 0; i < 10; i++) {

            g.setColor(randomColor[i]);
            plot(g, x[i], y[i]);
        }
    }

    public void plot(Graphics g, int x, int y) {
        plot(g, x, y, 8);
    }

    public void plot(Graphics g, int x, int y, int s) {
        g.fillRect(x, y, s, s);
    }
}