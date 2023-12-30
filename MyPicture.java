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
    public static Color[] gradient;

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
        line(g2, 0, 0, 0, HEIGHT, 1);
        line(g2, 0, 0, WIDTH, 0, 1);
        line(g2, HEIGHT - 1, 0, WIDTH - 1, HEIGHT, 1);
        line(g2, 0, HEIGHT - 1, WIDTH, HEIGHT - 1, 1);

        int x[] = { 590, 250, 1, 450, 299, 299, 448, 48, 150, 598 };
        int y[] = { 200, 250, 1, 150, 299, 450, 448, 330, 510, 598 };

        gradient = generateGradient(Color.BLACK, new Color(173, 216, 230), 380);

        int i = 0;
        int size = 1;

        { // sky and sea
          // night-sky
            for (Color color : gradient) {
                g2.setColor(color);
                line(g2, 0, 0 + (int) (size * i), 300, 0 + (int) (size * i), (int) size);
                i++;
            }

            // day-sky
            i = 0;
            gradient = generateGradient(new Color(5, 130, 200), new Color(220, 220, 255), 380);
            for (Color color : gradient) {
                g2.setColor(color);
                line(g2, 300, 0 + (int) (size * i), 600, 0 + (int) (size * i), (int) size);
                i++;
            }

            // day-sea
            i = 380;
            gradient = generateGradient(new Color(3, 80, 124), new Color(202, 240, 248), 130);
            for (Color color : gradient) {
                g2.setColor(color);
                line(g2, 300, 0 + (int) (size * i), 600, 0 + (int) (size * i), (int) size);
                i++;
            }

            // night-sea
            i = 380;
            gradient = generateGradient(new Color(5, 50, 80), new Color(20, 120, 168), 100);
            for (Color color : gradient) {
                g2.setColor(color);
                line(g2, 0, 0 + (int) (size * i), 300, 0 + (int) (size * i), (int) size);
                i++;
            }
        }

        // random-star
        i = 0;
        size = 3;
        gradient = generateGradient(new Color(255, 255, 255), new Color(200, 200, 200), size);
        for (Color color : gradient) {
            g2.setColor(color);
            star(g2, 250, 40, i);
            { // drago
                star(g2, 450, 100, i);
                star(g2, 415, 130, i);
                star(g2, 373, 200, i);
                star(g2, 341, 253, i);
                star(g2, 310, 257, i);
                star(g2, 293, 250, i);
                star(g2, 260, 230, i);
                star(g2, 225, 180, i);
                star(g2, 150, 175, i);
                star(g2, 155, 197, i);
                star(g2, 175, 270, i);
                star(g2, 200, 275, i);
                star(g2, 202, 294, i);
                star(g2, 171, 298, i);
            }
            i++;
        }

        // star-link
        {
            line(g2, 450, 100, 415, 130, 1);
            line(g2, 415, 130, 373, 200, 1);
            line(g2, 373, 200, 341, 253, 1);
            line(g2, 341, 253, 310, 257, 1);
            line(g2, 310, 257, 293, 250, 1);
            line(g2, 293, 250, 260, 230, 1);
            line(g2, 260, 230, 225, 180, 1);
            line(g2, 225, 180, 150, 175, 1);
            line(g2, 150, 175, 155, 197, 1);
            line(g2, 155, 197, 175, 270, 1);
            line(g2, 175, 270, 200, 275, 1);
            line(g2, 200, 275, 202, 294, 1);
            line(g2, 202, 294, 171, 298, 1);
            line(g2, 171, 298, 175, 270, 1);
        }

        g2.setColor(Color.PINK);

        cloud(g2, 100, 200, 14);

        // circle(g2, 100, 310, 40);
        midpointCircle(g2, 80, 70, 34);
        buffer = floodFillAll(buffer, 80, 70, Color.pink, Color.pink);

        // line(g2, 0, 380, 600, 380, 1);
        // line(g2, 300, 0, 300, 600, 1);

        bird(g2, 270, 300, 9);
        bird(g2, 280, 314, 9);
        bird(g2, 255, 333, 9);

        wave1(g2, 120, 390, 6);
        wave2(g2, 80, 390, 3);
        wave2(g2, 80, 395, 3);
        wave2(g2, 84, 401, 6);

        curve(g2, 0, 550, 70, 510, 170, 510, 200, 535);
        // curve(g2, 102, 500, 161, 510, 170, 510, 200, 535);
        curve(g2, 200, 535, 230, 560, 310, 560, 380, 510);
        curve(g2, 380, 510, 450, 465, 550, 465, 600, 475);

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

    public static Color[] generateGradient(Color startColor, Color endColor, int numSteps) {
        Color[] gradient = new Color[numSteps];

        for (int i = 0; i < numSteps; i++) {
            float ratio = (float) i / (float) (numSteps - 1);
            int red = (int) (startColor.getRed() + ratio * (endColor.getRed() - startColor.getRed()));
            int green = (int) (startColor.getGreen() + ratio * (endColor.getGreen() - startColor.getGreen()));
            int blue = (int) (startColor.getBlue() + ratio * (endColor.getBlue() - startColor.getBlue()));

            gradient[i] = new Color(red, green, blue);
        }

        return gradient;
    }

    public void wave1(Graphics g, int x, int y, int size) {
        curve(g, x-2*size, y, x-1*size, y-1*size, x+1*size, y+1*size, x+2*size, y);
    }

    public void wave2(Graphics g, int x, int y, int size) {
        curve(g, x-4*size, y, x-1*size, y-1*size, x+1*size, y-1*size, x+4*size, y);
    }

    public void bird(Graphics g, int x, int y, int size) {
        curve(g, x, y, x, y-1*size, x-1*size, y-1*size, x-1*size, y);
        curve(g, x, y, x, y-1*size, x+1*size, y-1*size, x+1*size, y);
    }

    public void cloud(Graphics g, int x, int y, int size) {
        curve(g, x - 3 * size, y + 1 * size, x - 6 * size, y + 1 * size, x - 6 * size, y + 2 * size, x, y + 2 * size);
        curve(g, x + 3 * size, y + 1 * size, x + 6 * size, y + 1 * size, x + 6 * size, y + 2 * size, x, y + 2 * size);
        curve(g, x - 3 * size, y + 1 * size, x - 4 * size, y, x - 3 * size, y - 1 * size, x - 2 * size, y);
        curve(g, x + 3 * size, y + 1 * size, x + 4 * size, y, x + 3 * size, y - 1 * size, x + 2 * size, y);
        curve(g, x - 2 * size, y, x - 3 * size, y - 2 * size, x + 3 * size, y - 2 * size, x + 2 * size, y);
    }

    public void star(Graphics g, int x, int y, int size) {
        curve(g, x, y - 2 * size, x, y - 1 * size, x - 1 * size, y, x - 2 * size, y);
        curve(g, x, y - 2 * size, x, y - 1 * size, x + 1 * size, y, x + 2 * size, y);
        curve(g, x, y + 2 * size, x, y + 1 * size, x - 1 * size, y, x - 2 * size, y);
        curve(g, x, y + 2 * size, x, y + 1 * size, x + 1 * size, y, x + 2 * size, y);
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
        float n = 1500;
        float t = 0;
        for (; t < 1; t += (1 / n)) {
            double x = (Math.pow(1 - t, 3) * x1) + (3 * t * Math.pow(1 - t, 2) * x2)
                    + (3 * Math.pow(t, 2) * (1 - t) * x3) + (Math.pow(t, 3) * x4);
            double y = (Math.pow(1 - t, 3) * y1) + (3 * t * Math.pow(1 - t, 2) * y2)
                    + (3 * Math.pow(t, 2) * (1 - t) * y3) + (Math.pow(t, 3) * y4);
            plot(g, (int) x, (int) y, 1);
        }
    }

    public BufferedImage floodFillAll(BufferedImage m, int x, int y,
            Color target_colour, Color replacement_colour) {
        Graphics2D g2 = m.createGraphics();
        Queue<Point> q = new LinkedList<>();

        if (m.getRGB(x, y) != target_colour.getRGB()) {
            g2.setColor(replacement_colour);
            plot(g2, x, y, 1);
            q.add(new Point(x, y));
        }

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.y < 600 && m.getRGB(p.x, p.y + 1) != target_colour.getRGB()) {
                g2.setColor(replacement_colour);
                plot(g2, p.x, p.y + 1, 1);
                q.add(new Point(p.x, p.y + 1));
            }
            if (p.y > 0 && m.getRGB(p.x, p.y - 1) != target_colour.getRGB()) {
                g2.setColor(replacement_colour);
                plot(g2, p.x, p.y - 1, 1);
                q.add(new Point(p.x, p.y - 1));
            }
            if (p.x < 600 && m.getRGB(p.x + 1, p.y) != target_colour.getRGB()) {
                g2.setColor(replacement_colour);
                plot(g2, p.x + 1, p.y, 1);
                q.add(new Point(p.x + 1, p.y));
            }
            if (p.y > 0 && m.getRGB(p.x - 1, p.y) != target_colour.getRGB()) {
                g2.setColor(replacement_colour);
                plot(g2, p.x - 1, p.y, 1);
                q.add(new Point(p.x - 1, p.y));
            }
        }
        return m;
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

    public void line(Graphics g, int x1, int y1, int x2, int y2, int size) {
        // g.drawLine(x1, y1, x2, y2);
        // dda(g, x1, y1, x2, y2);
        bresenham(g, x1, y1, x2, y2, size);
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

    public void bresenham(Graphics g, int x1, int y1, int x2, int y2, int size) {
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
            plot(g, x, y, size);

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