package graph;

import drawings.DrawingFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Toma-Florin Ungureanu
 */
public class Graph
{
    private final DrawingFrame frame;
    List<Vertex> vertices = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();
    public Graph(DrawingFrame frame)
    {
        this.frame = frame;
    }

    public void addVertex(Vertex vertex)
    {
        vertices.add(vertex);
    }

    public void addEdge(Vertex vertex1, Vertex vertex2)
    {
        Edge edge = new Edge(vertex1, vertex2);
        edges.add(edge);
    }

    public Graph(DrawingFrame frame, List<Vertex> vertices, List<Edge> edges)
    {
        this.frame = frame;
        this.vertices = vertices;
        this.edges = edges;
    }

    public void setGraph(List<Vertex> vertices, List<Edge> edges)
    {
        this.vertices = vertices;
        this.edges = edges;
    }

    public List<Vertex> getVertices()
    {
        return this.vertices;
    }

    public List<Edge> getEdges()
    {
        return this.edges;
    }
}
