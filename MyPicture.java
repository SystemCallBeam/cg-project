import java.awt.*;
import javax.swing.*;

public class MyPicture extends JPanel {
    public static final int WIDTH = 600, HEIGHT = 600;
    public static void main(String[] args) {
        // create frame?
        MyPicture g = new MyPicture();
        JFrame f = new JFrame("My Picture");
        f.setSize(WIDTH, HEIGHT);
        f.add(g);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        // plot(g, 2, 2);?
        int x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int y[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        draw(g, 100, 10, 100, 200);
    }

    public void plot(Graphics g, int x, int y) {
        int s = 1;
        g.fillRect(x, y, s, s);
    }

    public void draw(Graphics g, int x0, int y0, int x1, int y1) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                double a = distance(x0, y0, j, i), b = distance(j, i, x1, y1),
                c = distance(x0, y0, x1, y1);
                if (Math.abs(a + b - c) < 1) {
                    plot(g, j, i);
                }
            }
        }
    }

    public double distance(int x0, int y0, int x1, int y1) {
        return Math.sqrt((Math.pow(x1-x0, 2))+(Math.pow(y1-y0, 2)));
    }
}