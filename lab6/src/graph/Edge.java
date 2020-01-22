package graph;

/**
 * @author Toma-Florin Ungureanu
 */
public class Edge
{
    private Vertex begin;
    private Vertex end;

    Edge(Vertex begin, Vertex end)
    {
        this.begin = begin;
        this.end = end;
    }

    public void setEdge(Vertex begin, Vertex end)
    {
        this.begin = begin;
        this.end = end;
    }

    public Vertex getBegin()
    {
        return this.begin;
    }

    public  Vertex getEnd()
    {
        return this.end;
    }
}
