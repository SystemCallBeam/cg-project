import java.awt.*;
import javax.swing.*;

public class MyPicture extends JPanel {
    public static void main(String[] args) {
        // create frame?
        MyPicture g = new MyPicture();
        JFrame f = new JFrame("My Picture");
        f.setSize(600, 600);
        f.add(g);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        g.drawString("Hello", 296, 100);
        g.setColor(Color.gray);
        g.drawRect(295, 295, 60, 60);
        g.drawOval(300, 300, 50, 50);
        
        g.setColor(Color.blue);
        g.drawLine(0, 0, 600, 600);
        g.setColor(Color.red);
        g.drawLine(0, 0, 600, 600);
        
        g.setColor(Color.magenta);
        g.fillArc(300, 300, 50, 50, 90, 90);
        g.setColor(Color.green);
        g.fillArc(300, 300, 50, 50, 270, 90);
    }
}