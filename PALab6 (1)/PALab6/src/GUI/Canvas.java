package GUI;

import Graph.Edge;
import Graph.Graph;
import Graph.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
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
    Graph graph;

    Point pointStart = null;
    Point pointEnd = null;

    boolean deleteMode = false;

    public Canvas(DrawingFrame frame)
    {
        this.frame = frame;
        graph = new Graph();

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
                if (deleteMode == false)
                {
                    addNode(e.getX(), e.getY());
                } else
                {
                    if (graph.getNodes().size() > 0)
                        removeNode(findClosestNode(e.getX(), e.getY()));
                }
            }
        });


        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if (!deleteMode)
                {
                    pointStart = e.getPoint();
                }
            }

            public void mouseReleased(MouseEvent e)
            {
                if (!deleteMode)
                {
                    pointEnd = e.getPoint();
                    if (graph.getNodes().size() >= 2)
                    {
                        addEdge(findClosestNode(pointStart.getX(), pointStart.getY()),
                                findClosestNode(pointEnd.getX(), pointEnd.getY()));
                        repaint();
                    } else
                    {
                        System.out.println("Not enough nodes to add edge");
                    }
                }
            }
        });
    }

    private void drawNode(int x, int y, Color color, int size)
    {
        graphics.setColor(color);
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

        graph.addNode(node);

        this.repaint();
    }

    public void removeNode(int index)
    {
        graph.removeNode(index);

        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.repaint();
    }

    public void addEdge(int from, int to)
    {
        //add an edge between nodes i and j
        graph.addEdge(from, to);
        this.repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (Node node : graph.getNodes())
        {
            drawNode(node.getX(), node.getY(), node.getColor(), node.getSize());
        }

        for (Edge e : graph.getEdges())
        {
            graphics.setColor(Color.black);
            graphics.drawLine(graph.getNodes().get(e.getFrom()).getX(), graph.getNodes().get(e.getFrom()).getY(),
                    graph.getNodes().get(e.getTo()).getX(), graph.getNodes().get(e.getTo()).getY());
        }
        image.flush();
        g.drawImage(image, 0, 0, this);
    }

    public void clearCanvas()
{
    graph = new Graph();
    image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
    graphics = image.createGraphics();
    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    this.repaint();
}

    public int findClosestNode(double x, double y)
    {
        return graph.findClosestNode(x, y);
    }

    public void pretty()
    {
        graph.pretty();

        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.repaint();
    }

    public void setDeleteMode(boolean mode)
    {
        deleteMode = mode;
    }
}