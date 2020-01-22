package Graph;

import java.util.ArrayList;

public class Graph
{
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    public Graph()
    {
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
    }

    public ArrayList<Node> getNodes()
    {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes)
    {
        this.nodes = nodes;
    }

    public ArrayList<Edge> getEdges()
    {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges)
    {
        this.edges = edges;
    }

    public void addNode(Node node)
    {
        nodes.add(node);
    }

    public void removeNode(int index)
    {
        ArrayList<Edge> toDelete = new ArrayList<Edge>();

        for (Edge edge : edges)
        {
            if (edge.getFrom() == index || edge.getTo() == index)
                toDelete.add(edge);
        }

        edges.removeAll(toDelete);

        for (Edge edge : edges)
        {
            if (edge.getFrom() > index)
                edge.setFrom(edge.getFrom() - 1);
            if (edge.getTo() > index)
                edge.setTo(edge.getTo() - 1);
        }

        nodes.remove(index);
    }

    public void addEdge(int from, int to)
    {
        if (from != to && from < nodes.size() && to < nodes.size())
        {
            edges.add(new Edge(from, to));
        }
    }

    public void pretty()
    {
        int noNodes = nodes.size();
        int x, y;
        int centerx = 400, centery = 300;
        int raza = 250;
        int contorNoduri = 0;

        for (Node node : nodes)
        {

            x = (int) (raza * Math.cos((contorNoduri * 2 * Math.PI) / noNodes)) + centerx;
            y = (int) (raza * Math.sin((contorNoduri * 2 * Math.PI) / noNodes)) + centery;
            node.setX(x);
            node.setY(y);
            contorNoduri++;
        }
    }

    public int findClosestNode(double x, double y)
    {
        double minDist = 10000;
        int index = -1;
        for (Node node : nodes)
        {
            double dist = Math.sqrt((node.getX() - x) * (node.getX() - x) + (node.getY() - y) * (node.getY() - y));
            if (dist < minDist)
            {
                minDist = dist;
                index = nodes.indexOf(node);
            }
        }
        return index;
    }

}
