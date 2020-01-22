package com.graphrelated;

/**
 * @author Toma-Florin Ungureanu
 */
public class Edge
{
    private Node node1;
    private Node node2;
    private boolean bothWays = true;
    private int cost;

    /**
     * The default constructor of the edge class
     *
     * @param node1 the beginning node
     * @param node2 the end node
     * @param cost  the cost of the edge
     */
    public Edge(Node node1, Node node2, int cost)
    {
        this.node1 = node1;
        this.node2 = node2;
        this.cost = cost;
    }

    /**
     * The second constructor of the edge class.
     * It constructs an uni-directional edge
     *
     * @param node1  the beggining node
     * @param node2  the end node
     * @param cost   the cost of the edge
     * @param uniWay to check whether the edge is uni-directional or not
     */
    public Edge(Node node1, Node node2, int cost, boolean uniWay)
    {
        this.node1 = node1;
        this.node2 = node2;
        this.cost = cost;
        this.bothWays = uniWay;
    }

    public int getCost()
    {
        return this.cost;
    }

    public boolean getBothWays()
    {
        return this.bothWays;
    }

    @Override
    public String toString()
    {
        return "\nEdge: \n" + node1 + "\n" + node2 + "Bidirectional: " + bothWays + "\nCost of edge: " + cost + "\n";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return bothWays == edge.bothWays &&
                node1.equals(edge.node1) &&
                node2.equals(edge.node2);
    }

}
