//import package.Bug;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class Main extends JPanel {


    private static int maxWidth;
    private static int maxHeight;

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        g2d.setBackground(new Color(0, 128, 128));
        g2d.clearRect(0, 0, maxWidth, maxHeight);


//        BORDER
        g2d.setColor(new Color(4, 196, 68));
        BasicStroke bs1 = new BasicStroke(10, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs1);
        int interval = 10;
        g2d.drawRect(interval, interval, maxWidth - interval * 2, maxHeight - interval * 2);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("lab2");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);


        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
        frame.add(new Main());
//        frame.add(new Bug(maxWidth, maxHeight));

        frame.setResizable(false);
        frame.setVisible(true);


        System.out.println(maxWidth);
        System.out.println(maxHeight);
    }
}
