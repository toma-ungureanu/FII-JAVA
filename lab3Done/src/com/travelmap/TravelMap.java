package com.travelmap;

import com.graphrelated.Edge;
import com.graphrelated.Node;
import com.interfaces.IPayable;
import com.interfaces.IVisitable;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Toma-Florin Ungureanu
 */
public class TravelMap
{
    private List<Edge> edges = new ArrayList<>();
    private List<Node> nodes = new ArrayList<>();

    /**
     * Function to get the edges in the map in a pretty fashion
     *
     * @return the string of edges
     */
    public List<Edge> getEdges()
    {
        Edge[] edgeArray = new Edge[this.edges.size()];
        edges.toArray(edgeArray);

        return edges;
    }

    /**
     * Method to set the edges of the travel map
     *
     * @param edges of the travel map
     */
    public void setEdges(List<Edge> edges)
    {
        this.edges = edges;
    }

    /**
     * Function to get the nodes of the travel map in a pretty fashion.
     * Here we use the Collections class to sort the the nodes by their names.
     *
     * @return the string of nodes
     */
    public List<Node> getNodes()
    {
        Node[] nodeArray = new Node[this.nodes.size()];
        nodes.sort(Comparator.comparing(Node::getName));
        nodes.toArray(nodeArray);
        return nodes;
    }

    /**
     * Method to set the nodes of the travel map
     *
     * @param nodes of the map
     */
    public void setNodes(List<Node> nodes)
    {
        this.nodes = nodes;
    }

    /**
     * Method to add a node to the travel map
     *
     * @param node to add
     */
    public void addNode(Node node)
    {
        this.nodes.add(node);
    }

    /**
     * Method to add an edge in the travel map
     * This is the default method, as the edge is bi-directional
     *
     * @param node1 the starting node
     * @param node2 the ending node
     * @param cost  the cost of the edge
     */
    public void addEdge(Node node1, Node node2, int cost)
    {
        Edge edge = new Edge(node1, node2, cost);
        this.edges.add(edge);
    }

    /**
     * Method to add an edge in the travel map.
     * The edge is UNI-directional!
     *
     * @param node1  the starting node
     * @param node2  the ending node
     * @param cost   the cost of the edge
     * @param uniWay boolean value to mark that the edge is uni-directional
     */
    public void addEdge(Node node1, Node node2, int cost, boolean uniWay)
    {
        Edge edge = new Edge(node1, node2, cost, uniWay);
        this.edges.add(edge);
    }

    /**
     * Method to display the visitable and not payable objects
     * sorted by their opening hour
     * 1. We filter the nodes by the visitable instance type
     * 2. We filter the nodes by not being payable type
     * 3. We collect the results into a list
     */
    public void displayVisitableNotPayable()
    {
        List<Node> sortedVisitableNotPayable = nodes.stream().filter(e -> e instanceof IVisitable)
                .filter(e -> !(e instanceof IPayable))
                .sorted(Comparator.comparing(e -> ((IVisitable) e).getOpeningHour()))
                .collect(Collectors.toList());

        System.out.println('\n' + sortedVisitableNotPayable.toString().replace("[", "")
                .replace("]", "")
                .replace(", ", "\n")
                .trim());
    }

    /**
     * Function to print the average fee of the payable locations
     * @return
     */
    public double displayAveragePayable()
    {
        double average = nodes.stream()
                .filter(e -> e instanceof IPayable)
                .mapToInt(e -> ((IPayable) e).getFee())
                .average()
                .getAsDouble();

        System.out.println(average);
        return average;
    }

    @Override
    public String toString()
    {
        return "##### EDGES #####\n" + this.getEdges() +
                "\n\n##### NODES #####\n" + this.getNodes();
    }


}
