import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Toma-Florin Ungureanu
 */
public class Canvas extends JPanel
{
    private final DrawingFrame frame;
    BufferedImage image = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics = image.createGraphics();
    private double radius = 60;

    public Canvas(DrawingFrame frame)
    {
        this.frame = frame;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); //for "smooth" drawing
        init();
    }

    private void init()
    {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                drawNode(e.getX(), e.getY());
            }
        });
    }

    public void drawNode(double x, double y) {
        Random rand = new Random();
        graphics.setColor(new Color(rand.nextInt(0xFFFFFF))); //you may use a random color or the one specified in the toolbar
        graphics.fill(new NodeShape(x, y, radius));
        frame.getGraphics().drawImage(image,0,0,null);
        frame.getGraphics().dispose();
   }
}
