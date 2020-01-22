package drawings;

import graph.Graph;
import graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * @author Toma-Florin Ungureanu
 */
public class Canvas extends JPanel
{
    private final DrawingFrame frame;
    private Graph graph;
    double radius = 30;

    public Canvas(DrawingFrame frame, Graph graph)
    {
        this.frame = frame;
        this.graph = graph;
        this.add(graph);
        init();
    }

    private void init()
    {

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Random rand = new Random();
        graphics.setColor(new Color(rand.nextInt(0xFFFFFF))); //you may use a random color or the one specified in
        this.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                if(e.getY() > 25 && e.getY() < 662)
                {
                    graphics.fill(new Vertex(e.getX(),e.getY()));
                    System.out.println(e.getX() + " " + e.getY());
                }
            }
        });
        revalidate();
        repaint();
    }

    public void drawNode(double x, double y)
    {
       graph.addVertex(new Vertex(x,y));
       repaint();
       revalidate();
    }
}
