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

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, WIDTH, HEIGHT);

        g2.setColor(Color.BLACK);
        line(g2, 0, 0, 0, HEIGHT);
        line(g2, 0, 0, WIDTH, 0);
        line(g2, HEIGHT - 1, 0, WIDTH - 1, HEIGHT);
        line(g2, 0, HEIGHT - 1, WIDTH, HEIGHT - 1);

        int x[] = { 590, 250, 1, 450, 299, 299, 448, 48, 150, 598 };
        int y[] = { 200, 250, 1, 150, 299, 450, 448, 330, 510, 598 };

        // for (int i = 0; i < 10; i++) {
        //
        // g2.setColor(randomColor[i]);
        // // plot(g, x[i], y[i]);
        // // line(g, x[i], y[i], x[(i + 1) % 10], y[(i + 1) % 10]);
        // bezier(g2, x[i], y[i], x[(i + 1) % 10], y[(i + 1) % 10], x[(i + 2) % 10],
        // y[(i + 2) % 10], x[(i + 3) % 10], y[(i + 3) % 10]);
        // }
        //
        g2.setColor(Color.PINK);
        heart(g2, 100, 100, 10);

        // circle(g2, 100, 310, 40);
        midpointCircle(g2, 80, 70, 40);

        star(g2, 430, 130, 7);

        // curve(g2, 0, 550, 70, 510, 100, 510, 102, 500);
        // curve(g2, 102, 500, 104, 490, 112, 490, 113, 492);
        // curve(g2, 113, 492, 114, 495, 122, 495, 123, 492);
        // curve(g2, 123, 492, 124, 490, 130, 490, 132, 500);
        // curve(g2, 132, 500, 134, 510, 170, 510, 200, 535);

        line(g2, 0, 370, 600, 370);
        line(g2, 300, 0, 300, 600);

        curve(g2, 0, 550, 70, 510, 170, 510, 200, 535);
        // curve(g2, 102, 500, 161, 510, 170, 510, 200, 535);
        curve(g2, 200, 535, 230, 560, 310, 560, 380, 510);
        curve(g2, 380, 510, 450, 460, 550, 460, 600, 480);

        int ty = 10;
        curve(g2, 98, 510 + ty, 107, 510 + ty, 113, 492 + ty, 116, 492 + ty);
        curve(g2, 116, 492 + ty, 119, 492 + ty, 122, 501 + ty, 125, 501 + ty);
        curve(g2, 125, 501 + ty, 128, 501 + ty, 131, 492 + ty, 134, 492 + ty);
        curve(g2, 134, 492 + ty, 137, 492 + ty, 143, 510 + ty, 152, 510 + ty);

        curve(g2, 0, 450, 180, 430, 250, 450, 380, 510);

        // buffer = floodFill(buffer, 100, 50, Color.white, Color.red);
        // buffer = floodFill(buffer, 500, 500, Color.white, Color.blue);

        g.drawImage(buffer, 0, 0, null);

    }

    public void star(Graphics g, int x, int y, int size) {
        curve(g, x, y-2*size, x, y-1*size, x-1*size, y, x-2*size, y);
        curve(g, x, y-2*size, x, y-1*size, x+1*size, y, x+2*size, y);
        curve(g, x, y+2*size, x, y+1*size, x-1*size, y, x-2*size, y);
        curve(g, x, y+2*size, x, y+1*size, x+1*size, y, x+2*size, y);
    }

    public void heart(Graphics g, int x, int y, int size) {
        curve(g, x, y, x, y - 1 * size, x - 2 * size, y - 1 * size, x - 2 * size, y);
        curve(g, x, y, x, y - 1 * size, x + 2 * size, y - 1 * size, x + 2 * size, y);
        curve(g, x, y + 2 * size, x, y + 1 * size, x + 2 * size, y + 1 * size, x + 2 * size, y);
        curve(g, x, y + 2 * size, x, y + 1 * size, x - 2 * size, y + 1 * size, x - 2 * size, y);
    }

    public void midpointCircle(Graphics g, int xc, int yc, int r) {
        int x = 0;
        int y = r;
        int d = 1 - r;

        while (x <= y) {
            plot(g, x + xc, y + yc, 1);
            plot(g, -x + xc, y + yc, 1);
            plot(g, x + xc, -y + yc, 1);
            plot(g, -x + xc, -y + yc, 1);
            plot(g, y + xc, x + yc, 1);
            plot(g, -y + xc, x + yc, 1);
            plot(g, y + xc, -x + yc, 1);
            plot(g, -y + xc, -x + yc, 1);

            x++;
            d = d + 2 * x + 1;
            if (d >= 0) {
                y--;
                d = d - 2 * y;
            }
        }
    }

    public void midpointEllipse(Graphics g, int xc, int yc, int a, int b) {
        int a2 = a * a, b2 = b * b;
        int twoA2 = 2 * a2, twoB2 = 2 * b2;

        // region 1
        int x = 0;
        int y = b;
        int D = (int) (b2 - a2 * b + a2 / 4);
        int Dx = 0, Dy = twoA2 * y;

        while (Dx <= Dy) {
            plot(g, x + xc, y + yc, 1);
            plot(g, -x + xc, y + yc, 1);
            plot(g, x + xc, -y + yc, 1);
            plot(g, -x + xc, -y + yc, 1);

            x++;
            Dx = Dx + twoB2;
            D = D + Dx + b2;

            if (D >= 0) {
                y--;
                Dy = Dy - twoA2;
                D = D - Dy;
            }
        }

        // region 2
        x = a;
        y = 0;
        D = (int) (a2 - b2 * a + b2 / 4);
        Dx = twoB2 * x;
        Dy = 0;

        while (Dx >= Dy) {
            plot(g, x + xc, y + yc, 1);
            plot(g, -x + xc, y + yc, 1);
            plot(g, x + xc, -y + yc, 1);
            plot(g, -x + xc, -y + yc, 1);

            y++;
            Dy += twoA2;
            D = D + Dy - a2;

            if (D >= 0) {
                x--;
                Dx -= twoB2;
                D -= Dx;
            }
        }
    }

    public void curve(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        bezier(g, x1, y1, x2, y2, x3, y3, x4, y4);
    }

    public void bezier(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        float n = 2000;
        float t = 0;
        for (; t < 1; t += (1 / n)) {
            double x = (Math.pow(1 - t, 3) * x1) + (3 * t * Math.pow(1 - t, 2) * x2)
                    + (3 * Math.pow(t, 2) * (1 - t) * x3) + (Math.pow(t, 3) * x4);
            double y = (Math.pow(1 - t, 3) * y1) + (3 * t * Math.pow(1 - t, 2) * y2)
                    + (3 * Math.pow(t, 2) * (1 - t) * y3) + (Math.pow(t, 3) * y4);
            plot(g, (int) x, (int) y, 1);
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

            if (p.y < 600 && m.getRGB(p.x, p.y + 1) == target_colour.getRGB()) {
                g2.setColor(replacement_colour);
                plot(g2, p.x, p.y + 1, 1);
                q.add(new Point(p.x, p.y + 1));
            }
            if (p.y > 0 && m.getRGB(p.x, p.y - 1) == target_colour.getRGB()) {
                g2.setColor(replacement_colour);
                plot(g2, p.x, p.y - 1, 1);
                q.add(new Point(p.x, p.y - 1));
            }
            if (p.x < 600 && m.getRGB(p.x + 1, p.y) == target_colour.getRGB()) {
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