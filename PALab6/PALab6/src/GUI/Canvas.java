package GUI;

import Graph.Edge;
import Graph.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Canvas extends JPanel
{
    private DrawingFrame frame;

    private BufferedImage image;
    private Graphics2D graphics;

    public void setChosenColor(Color chosenColor)
    {
        this.chosenColor = chosenColor;
    }

    private Color chosenColor = null;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    public Canvas(DrawingFrame frame)
    {
        this.frame = frame;
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();

        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        String title = "<html><i><font color='blue'>" + "Canvas" + "</font></i></html>";
        setBorder(BorderFactory.createTitledBorder(title));
        //setBackground(Color.WHITE);

        this.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                addNode(e.getX(), e.getY());
            }
        });
    }

    private void drawNode(int x, int y, Color color, int size)
    {
        System.out.println("DrawNode " + x + " " + y);
        graphics.setColor(color);
        //graphics.setStroke(new BasicStroke());
        graphics.fill(new NodeShape(x, y, size));
    }

    public void addNode(int x, int y)
    {
        //add a node at pixel (x,y)

        Node node = new Node(x, y);

        if (chosenColor == null)
        {
            Random rand = new Random();
            node.setColor(new Color(rand.nextInt(0xFFFFFF)));
        } else node.setColor(chosenColor);

        node.setSize(frame.toolbar.getNodeSize());
        nodes.add(node);
        this.repaint();
    }

    public void addEdge(int from, int to)
    {
        //add an edge between nodes i and j
        edges.add(new Edge(from, to));
        this.repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (Node node : nodes)
        {
            drawNode(node.getX(), node.getY(), node.getColor(), node.getSize());
        }

        for (Edge e : edges)
        {
            graphics.drawLine(nodes.get(e.getFrom()).getX(), nodes.get(e.getFrom()).getY(),
                    nodes.get(e.getTo()).getX(), nodes.get(e.getTo()).getY());
        }
        image.flush();
        g.drawImage(image, 0, 0, this);
    }

    public void clearCanvas()
    {
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.repaint();
    }
}