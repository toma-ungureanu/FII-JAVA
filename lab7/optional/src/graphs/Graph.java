package graphs;

import java.util.*;

/**
 * @author Toma-Florin Ungureanu
 */
public class Graph extends LinkedList
{
    private int numberOfVertices;
    private int kCliqueNo;
    private ArrayList<LinkedList<Integer>> adj;

    public Graph(int numberOfVertices, int kCliqueNo)
    {
        this.numberOfVertices = numberOfVertices;
        this.kCliqueNo = kCliqueNo;
    }

    public ArrayList<LinkedList<Integer>> getAdj()
    {
        return adj;
    }

    public int getNumberOfVertices()
    {
        return numberOfVertices;
    }

    /**
     * A recursive function that uses visited and parent
     * to detect cycle in subgraph reachable from vertex v.
     *
     * @param v       the current node
     * @param visited the visited nodes array
     * @param parent  the parent of the current node
     * @return true if a cycle exists
     */
    private boolean isCyclicUtil(int v, List<Boolean> visited, int parent)
    {
        // Mark the current node as visited
        visited.set(v, true);
        Integer i;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> it = adj.get(v).iterator();
        while (it.hasNext())
        {
            i = it.next();

            // If an adjacent is not visited, then recur for
            // that adjacent
            if (!visited.get(i))
            {
                if (isCyclicUtil(i, visited, v))
                    return true;
            }

            // If an adjacent is visited and not parent of
            // current vertex, then there is a cycle.
            else if (i != parent)
                return true;
        }
        return false;
    }

    /**
     * @return true if the graph is a tree, else false.
     */
    private boolean isTree()
    {
        // Mark all the vertices as not visited and not part
        // of recursion stack
        List<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++)
            visited.add(false);

        // The call to isCyclicUtil serves multiple purposes
        // It returns true if graph reachable from vertex 0
        // is cyclcic. It also marks all vertices reachable
        // from 0.
        if (isCyclicUtil(0, visited, -1))
            return false;

        // If we find a vertex which is not reachable from 0
        // (not marked by isCyclicUtil(), then we return false
        for (int u = 0; u < numberOfVertices; u++)
            if (!visited.get(u))
                return false;

        return true;
    }

    private boolean conditionConnectivity(Set<Integer> setString)
    {
        //if it does not contain all the vertices
        if (setString.size() != numberOfVertices)
        {
            return false;
        }

        //if the graph does not respect the tree property
        if (this.size() != numberOfVertices - 1)
        {
            return false;
        }

        return true;
    }

    private boolean conditionClique()
    {
        if (this.size() != (kCliqueNo * (kCliqueNo - 1)) / 2)
        {
            return false;
        }

        return false;
    }

    private void addAdjList()
    {
        adj = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; ++i)
        {
            adj.add(new LinkedList<>());
        }

        //sort the list to add it to an adjacency list
        Collections.sort(this, Comparator.comparing(Edge::getNumber1));

        if (this.size() > 2)
        {
            for (int i = 0; i < this.size(); i++)
            {
                //add both sides as the graph is undirected
                adj.get(((Edge) this.get(i)).getNumber1()).add(((Edge) this.get(i)).getNumber2());
                adj.get(((Edge) this.get(i)).getNumber2()).add(((Edge) this.get(i)).getNumber1());
            }
        }
    }

    /**
     * The function is used to check whether the graph is a spanning tree or not
     *
     * @return true if the graph is a spanning tree, false otherwise
     */
    public boolean isSpanningTree()
    {
        Set<Integer> setString = new LinkedHashSet<>();
        for (Object object : this)
        {
            setString.add(((Edge) object).getNumber1());
            setString.add(((Edge) object).getNumber2());
        }

        if(!conditionConnectivity(setString))
        {
            return false;
        }

        addAdjList();

        return isTree();
    }

    public boolean isClique()
    {
        if(!conditionClique())
        {
            return false;
        }

        addAdjList();

        for(int i = 0; i < adj.size(); i++)
        {
            for(int j = 0; j < adj.size(); j++)
            {
                if(i != j)
                {
                    if(adj.get(i).get(j) != 1)
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
