import java.awt.*;
import java.awt.color.ColorSpace;
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
    public static int r[][] = new int[WIDTH][HEIGHT];
    public static int randomXY[][];

    public static void main(String[] args) {
        MyPicture g = new MyPicture();
        JFrame f = new JFrame("My Picture");
        // for (int i = 0; i < 10; i++) {
        // randomColor[i] = new Color(rand.nextInt(256), rand.nextInt(256),
        // rand.nextInt(256));
        // }
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[0].length; j++) {
                r[i][j] = rand.nextInt(25);
            }
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

        gradient = generateGradient(Color.BLACK, new Color(173, 216, 230), 380);

        int x, y, temp;
        int i = 0, size = 1;

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
            { // star
                star(g2, 250, 40, i);
                star(g2, 190, 24, i);
                star(g2, 130, 48, i);
                star(g2, 220, 50, i);
                star(g2, 230, 80, i);
                star(g2, 20, 40, i);
                star(g2, 30, 90, i);
                star(g2, 150, 130, i);
                star(g2, 50, 150, i);
                star(g2, 180, 110, i);
                star(g2, 330, 80, i);
                star(g2, 440, 40, i);
                star(g2, 300, 90, i);
                star(g2, 550, 130, i);
            }

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

        Color colorA = Color.white;
        { // cloud
            g2.setColor(colorA);
            cloud(g2, 250, 100, 10);
            cloud(g2, 100, 200, 14);
            cloud(g2, 430, 290, 14);
            cloud(g2, 500, 150, 12);
            heart(g2, 300, 180, 19);
            floodFillAll(buffer, 250, 100, colorA);
            floodFillAll(buffer, 100, 200, colorA);
            floodFillAll(buffer, 430, 290, colorA);
            floodFillAll(buffer, 500, 150, colorA);
            floodFillAll(buffer, 300, 182, colorA);

            colorA = new Color(230, 230, 230);
            g2.setColor(colorA);
            cloud(g2, 250, 104, 7);
            cloud(g2, 100, 206, 10);
            cloud(g2, 430, 299, 10);
            cloud(g2, 500, 156, 8);
            heart(g2, 300, 183, 12);
            floodFillAll(buffer, 250, 100, colorA);
            floodFillAll(buffer, 100, 200, colorA);
            floodFillAll(buffer, 430, 299, colorA);
            floodFillAll(buffer, 500, 156, colorA);
            floodFillAll(buffer, 300, 185, colorA);
        }

        colorA = new Color(255, 250, 200);
        g2.setColor(colorA);
        midpointCircle(g2, 80, 70, 34);
        buffer = floodFillAll(buffer, 80, 70, colorA);
        i = 0;
        size = 8;
        gradient = generateGradient(new Color(255, 255, 255), new Color(200, 200, 200), size);
        for (Color color : gradient) {
            g2.setColor(color);
            midpointCircle(g2, 80, 70, 34 + i);
            i++;
        }

        // line(g2, 0, 380, 600, 380, 1);
        // line(g2, 300, 0, 300, 600, 1);

        { // wave
            g2.setColor(new Color(188, 239, 248));
            wave2(g2, 80, 390, 3, 2);
            wave2(g2, 84, 401, 3, 2);
            wave2(g2, 283, 399, 3, 2);
            wave2(g2, 104, 421, 5, 2);
            wave2(g2, 174, 431, 5, 2);
            wave2(g2, 84, 441, 5, 2);
            wave2(g2, 224, 391, 3, 2);
            wave2(g2, 40, 428, 6, 2);
            wave2(g2, 254, 441, 6, 2);
            line(g2, 20, 395, 40, 395, 2);
            line(g2, 140, 397, 160, 397, 2);
            line(g2, 120, 410, 150, 410, 2);
            line(g2, 200, 405, 230, 405, 2);

            x = 290;
            wave2(g2, 80 + x, 390, 3, 3);
            wave2(g2, 84 + x, 401, 3, 3);
            wave2(g2, 283 + x, 399, 3, 3);
            wave2(g2, 104 + x, 421, 5, 3);
            wave2(g2, 174 + x, 431, 5, 3);
            wave2(g2, 84 + x, 441, 5, 3);
            wave2(g2, 224 + x, 391, 3, 3);
            wave2(g2, 40 + x, 428, 6, 3);
            wave2(g2, 254 + x, 441, 6, 3);
            line(g2, 20 + x, 395, 40 + x, 395, 2);
            line(g2, 140 + x, 397, 160 + x, 397, 2);
            line(g2, 120 + x, 410, 150 + x, 410, 2);
            line(g2, 200 + x, 405, 230 + x, 405, 2);
        }

        g2.setColor(Color.black);
        bird(g2, 270, 300, 9);
        bird(g2, 280, 314, 9);
        bird(g2, 255, 333, 9);

        firework(g2, 110, 330, 30);
        firework(g2, 190, 270, 60);
        firework(g2, 320, 300, 50);
        firework(g2, 450, 130, 80);

        { // ground
            int ty = 1;
            Color green_1 = new Color(50, 87, 48);
            g2.setColor(green_1);
            curve(g2, 0, 450, 180, 430, 250, 450, 380, 510);
            line(g2, 0, 450, 0, 550, 1);

            // cat
            Color green_2 = new Color(80, 120, 45);
            g2.setColor(green_2);
            line(g2, 0, 551, 0, 600, 1);
            line(g2, 0, 600 - 1, 600, 600 - 1, 1);
            line(g2, 600 - 1, 475, 600 - 1, 600, 2);
            curve(g2, 0, 550, 70, 510, 170, 510, 200, 535);
            curve(g2, 200, 535, 230, 560, 310, 560, 380, 510);
            curve(g2, 380, 510, 450, 465, 550, 465, 600, 475);

            ty = 10;
            curve(g2, 98, 510 + ty, 107, 510 + ty, 113, 492 + ty, 116, 492 + ty);
            curve(g2, 116, 492 + ty, 119, 492 + ty, 122, 501 + ty, 125, 501 + ty);
            curve(g2, 125, 501 + ty, 128, 501 + ty, 131, 492 + ty, 134, 492 + ty);
            curve(g2, 134, 492 + ty, 137, 492 + ty, 143, 510 + ty, 152, 510 + ty);

            // fill color
            floodFillAll(buffer, 300, 598, green_2);
            floodFill(buffer, 115, 505, Color.white, green_2);
            plot(g2, 115, 506, 1);

            g2.setColor(green_1);
            floodFill(buffer, 200, 500, Color.white, green_1);
            floodFillAll(buffer, 200, 455, green_1);
        }

        { // grass
            g2.setColor(new Color(150, 170, 96));
            grass(g2, 120, 445, 60);
            grass(g2, 160, 445, 20);
            grass(g2, 20, 450, 20);
            grass(g2, 40, 450, 20);
            grass(g2, 60, 450, 20);
            grass(g2, 80, 450, 20);
            grass(g2, 100, 450, 20);
            grass(g2, 120, 450, 20);
            grass(g2, 140, 450, 20);
            grass(g2, 160, 450, 20);
            grass(g2, 180, 450, 20);
            grass(g2, 120, 460, 130);
            grass(g2, 260, 470, 30);
            grass(g2, 280, 480, 20);
            grass(g2, 150, 470, 140);
            grass(g2, 150, 480, 150);
            grass(g2, 60, 490, 75);
            grass(g2, 205, 490, 75);
            grass(g2, 150, 500, 150);
            grass(g2, 45, 510, 50);
            grass(g2, 225, 510, 60);
            grass(g2, 15, 520, 30);
            grass(g2, 250, 520, 50);
            grass(g2, 10, 530, 10);
            grass(g2, 260, 530, 40);
            grass(g2, 265, 536, 30);

            g2.setColor(new Color(170, 190, 126));
            grass(g2, 75, 520, 30);
            grass(g2, 160, 520, 20);
            grass(g2, 120, 535, 80);
            grass(g2, 120, 545, 100);
            grass(g2, 150, 550, 150);
            grass(g2, 150, 560, 150);
            grass(g2, 150, 570, 150);
            grass(g2, 150, 580, 150);
            grass(g2, 150, 590, 150);
        }

        g2.setColor(Color.black);
        flower(buffer, g2, 200, 455, 5);
        flower(buffer, g2, 174, 480, 5);
        flower(buffer, g2, 124, 492, 4);
        flower(buffer, g2, 134, 472, 5);
        flower(buffer, g2, 84, 482, 4);
        flower(buffer, g2, 264, 492, 6);
        flower(buffer, g2, 230, 499, 4);
        flower(buffer, g2, 291, 512, 5);

        x = 0;
        y = 14;
        size = 6;
        g2.setColor(Color.black);
        midpointCircle(g2, 116, 515, 3);
        midpointCircle(g2, 133, 515, 3);
        floodFillAll(buffer, 116, 515, Color.black);
        floodFillAll(buffer, 133, 515, Color.black);
        curve(g2, 125 + x, 510 + y, 125 + x, 510 + y + 1 * size, 125 + x - 1 * size, 510 + y + 1 * size,
                125 + x - 1 * size, 510 + y);
        curve(g2, 125 + x, 510 + y, 125 + x, 510 + y + 1 * size, 125 + x + 1 * size, 510 + y + 1 * size,
                125 + x + 1 * size, 510 + y);

        temp = 5;
        for (int j = 0; j < 600 / temp; j++) {
            for (int k = 0; k < 300 / temp; k++) {
                colorA = new Color(buffer.getRGB(300 + temp * k, 0 + temp * j) - r[j][k]);
                g2.setColor(colorA);
                plot(g2, 300 + temp * k, 0 + temp * j, temp);
            }
        }

        for (int j = 0; j < 600; j++) {
            for (int k = 0; k < 600; k++) {
                colorA = new Color(buffer.getRGB(j, k) - r[j][k]);
                g2.setColor(colorA);
                // plot(g2, j, k);
            }
        }

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

    public void grass(Graphics g, int x, int y, int r) {
        grass(g, x, y, r, 1);
    }

    public void grass(Graphics g, int x, int y, int r, int size) {
        int ry[] = new int[r];
        int rx[] = new int[r];
        for (int i = 0; i < r; i++) {
            rx[i] = rand.nextInt(-r, r);
            ry[i] = rand.nextInt(-5, 5);
        }
        for (int i = 0; i < r; i++) {
            line(g, x + rx[i], y + ry[i], x + rx[i], y + 6 + ry[i], size);
        }

    }

    public void firework(Graphics g, int x, int y, int r) {
        int n = r;
        randomXY = new int[2][n];
        randomColor = new Color[r];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                randomXY[i][j] = rand.nextInt(-r, r);
            }
        }

        for (int i = 0; i < r; i++) {
            randomColor[i] = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        }

        g.setColor(Color.white);
        plot(g, x - r / 20, y - r / 20, r / 10);
        for (int i = 0; i < n; i++) {
            g.setColor(randomColor[i % r]);
            int tx = x + randomXY[0][i], ty = y + randomXY[1][i];
            if (randomXY[0][i] * randomXY[0][i] + randomXY[1][i] * randomXY[1][i] < r * r) {
                plot(g, tx, ty, 4);
            }
        }
    }

    public void flower(BufferedImage m, Graphics g, int x, int y, int size) {
        Color c;
        g.setColor(Color.black);
        line(g, x, y, x, y + 4 * size, 1);

        c = Color.white;
        g.setColor(c);
        curve(g, x - 1 * size, y - 1 * size, x - 1 * size, y - 2 * size, x + 1 * size, y - 2 * size, x + 1 * size,
                y - 1 * size);
        curve(g, x - 1 * size, y + 1 * size, x - 1 * size, y + 2 * size, x + 1 * size, y + 2 * size, x + 1 * size,
                y + 1 * size);
        curve(g, x - 1 * size, y - 1 * size, x - 2 * size, y - 1 * size, x - 2 * size, y + 1 * size, x - 1 * size,
                y + 1 * size);
        curve(g, x + 1 * size, y - 1 * size, x + 2 * size, y - 1 * size, x + 2 * size, y + 1 * size, x + 1 * size,
                y + 1 * size);
        floodFillAll(m, x + 1 * size + 1, y, c);
        floodFillAll(m, x - 1 * size - 1, y, c);
        floodFillAll(m, x, y + 1 * size + 1, c);
        floodFillAll(m, x, y - 1 * size - 1, c);
        c = new Color(255, 226, 0);
        // c = Color.yellow;
        g.setColor(c);
        midpointCircle(g, x, y, size);
        floodFillAll(m, x, y, c);
    }

    public void wave2(Graphics g, int x, int y, int size, int scale) {
        curve(g, x - 4 * size, y, x - 1 * size, y - 1 * size, x + 1 * size, y - 1 * size, x + 4 * size, y, scale);
    }

    public void wave2(Graphics g, int x, int y, int size) {
        wave2(g, x, y, size, 1);
    }

    public void bird(Graphics g, int x, int y, int size) {
        curve(g, x, y, x, y - 1 * size, x - 1 * size, y - 1 * size, x - 1 * size, y);
        curve(g, x, y, x, y - 1 * size, x + 1 * size, y - 1 * size, x + 1 * size, y);
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

    public void curve(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int size) {
        bezier(g, x1, y1, x2, y2, x3, y3, x4, y4, size);
    }

    public void curve(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        bezier(g, x1, y1, x2, y2, x3, y3, x4, y4);
    }

    public void bezier(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        bezier(g, x1, y1, x2, y2, x3, y3, x4, y4, 1);
    }

    public void bezier(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int size) {
        float n = 1200;
        float t = 0;
        for (; t < 1; t += (1 / n)) {
            double x = (Math.pow(1 - t, 3) * x1) + (3 * t * Math.pow(1 - t, 2) * x2)
                    + (3 * Math.pow(t, 2) * (1 - t) * x3) + (Math.pow(t, 3) * x4);
            double y = (Math.pow(1 - t, 3) * y1) + (3 * t * Math.pow(1 - t, 2) * y2)
                    + (3 * Math.pow(t, 2) * (1 - t) * y3) + (Math.pow(t, 3) * y4);
            plot(g, (int) x, (int) y, size);
        }
    }

    public BufferedImage floodFillAll(BufferedImage m, int x, int y,
            Color target_colour) {
        Graphics2D g2 = m.createGraphics();
        Queue<Point> q = new LinkedList<>();

        if (m.getRGB(x, y) != target_colour.getRGB()) {
            g2.setColor(target_colour);
            plot(g2, x, y, 1);
            q.add(new Point(x, y));
        }

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.y < 600 && m.getRGB(p.x, p.y + 1) != target_colour.getRGB()) {
                g2.setColor(target_colour);
                plot(g2, p.x, p.y + 1, 1);
                q.add(new Point(p.x, p.y + 1));
            }
            if (p.y > 0 && m.getRGB(p.x, p.y - 1) != target_colour.getRGB()) {
                g2.setColor(target_colour);
                plot(g2, p.x, p.y - 1, 1);
                q.add(new Point(p.x, p.y - 1));
            }
            if (p.x < 600 && m.getRGB(p.x + 1, p.y) != target_colour.getRGB()) {
                g2.setColor(target_colour);
                plot(g2, p.x + 1, p.y, 1);
                q.add(new Point(p.x + 1, p.y));
            }
            if (p.y > 0 && m.getRGB(p.x - 1, p.y) != target_colour.getRGB()) {
                g2.setColor(target_colour);
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