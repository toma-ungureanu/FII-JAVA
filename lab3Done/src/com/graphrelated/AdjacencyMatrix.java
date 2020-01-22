package com.graphrelated;

import com.travelmap.TravelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Toma-Florin Ungureanu
 */
public class AdjacencyMatrix
{
    private TravelMap travelMap = new TravelMap();
    private List<List<Integer>> matrix = new ArrayList<>();
    private int matrixSize;


    /**
     * The default constructor for the adjacency matrix class
     *
     * @param travelMap to be converted
     */
    public AdjacencyMatrix(TravelMap travelMap)
    {
        Objects.requireNonNull(travelMap);
        this.travelMap = travelMap;
        matrixSize = travelMap.getNodes().size();
        matrix = convertToCostMatrix();

    }

    /**
     * Helper function to search and set an element in a matrix.
     *
     * @param matrix      in which we set th element
     * @param firstNode   the first node in the entry of the matrix
     * @param secondNode  the second node in the entry of the matrix
     * @param edgeToMatch edge formed from the first and second node
     */
    private void setElementInMatrix(List<List<Integer>> matrix, int firstNode, int secondNode, Edge edgeToMatch)
    {
        matrix.get(firstNode).set(secondNode,
                travelMap.getEdges().get(travelMap.getEdges().indexOf(edgeToMatch)).getCost());
    }

    /**
     * Helper function to initailize a matrix.
     *
     * @param matrix to be intialized
     */
    private void initializeMatrix(List<List<Integer>> matrix)
    {
        for (int row = 0; row < matrixSize; row++)
        {
            matrix.add(new ArrayList<>(matrixSize));
            for (int column = 0; column < matrixSize; column++)
            {
                matrix.get(row).add(1000000);
                if(row == column)
                {
                    matrix.get(row).set(column,0);
                }
            }
        }
    }

    /**
     * Function to convert the edge list in a cost matrix.
     * This algorithm searches for all the possible edges
     * in the edge list and sees if the array contains the
     * respective edge, it sets the cost for both entries
     * in the matrix if the edge is bidirectional else
     * it sets the cost for just one entry
     *
     * @return the cost matrix
     */
    private List<List<Integer>> convertToCostMatrix()
    {
        List<List<Integer>> matrix = new ArrayList<>(matrixSize);
        Edge edgeToMatch;
        int cost = 0;

        initializeMatrix(matrix);

        for (int firstNode = 0; firstNode < matrixSize; firstNode++)
        {
            for (int secondNode = 0; secondNode < matrixSize; secondNode++)
            {
                edgeToMatch = new Edge(travelMap.getNodes().get(firstNode), travelMap.getNodes().get(secondNode),
                        cost, true);
                if (travelMap.getEdges().contains(edgeToMatch))
                {
                    setElementInMatrix(matrix, firstNode, secondNode, edgeToMatch);
                    setElementInMatrix(matrix, secondNode, firstNode, edgeToMatch);
                } else
                {
                    edgeToMatch = new Edge(travelMap.getNodes().get(firstNode), travelMap.getNodes().get(secondNode),
                            cost, false);
                    if (travelMap.getEdges().contains(edgeToMatch))
                    {
                        setElementInMatrix(matrix, firstNode, secondNode, edgeToMatch);
                    }
                }
            }
        }
        return matrix;
    }

    /**
     * Helper function to compute the minimum distance value
     * from the not yet included set of vertices
     *
     * @param dist   the output array
     * @param sptSet sptSet.get(i) will be true if vertex i is included in shortest
     *               path tree or shortest distance from src to i is finalized
     * @return the minimum distance
     */
    private int minDistance(List<Integer> dist, List<Boolean> sptSet)
    {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int visited = 0; visited < matrixSize; visited++)
            if (!sptSet.get(visited) && dist.get(visited) <= min)
            {
                min = dist.get(visited);
                minIndex = visited;
            }

        return minIndex;
    }

    /**
     * Helper method to prin the solution to the dijkstra algorithm
     *
     * @param dist        the cost vector
     * @param startVertex the starting point
     * @param endVertex   the ending point
     */
    private void printSolution(List<Integer> dist, int startVertex, int endVertex)
    {
        System.out.println("Cost from " + travelMap.getNodes().get(startVertex).getName()
                + " to " + travelMap.getNodes().get(endVertex).getName() + " is: " + dist.get(endVertex));
    }

    /**
     * 1. Mark all nodes unvisited. Create a set of all the unvisited nodes called the unvisited set.
     * 2. Assign to every node a tentative distance value: set it to zero for our initial node and to infinity for all
     * other nodes. Set the initial node as current.
     * 3. For the current node, consider all of its unvisited neighbors and calculate their tentative distances through
     * the current node. Compare the newly calculated tentative distance to the current assigned value and assign the
     * smaller one.
     * 4. When we are done considering all of the unvisited neighbors of the current node, mark the current node as
     * visited and remove it from the unvisited set. A visited node will never be checked again.
     * 5. If the destination node has been marked visited (when planning a route between two specific nodes) or if the
     * smallest tentative distance among the nodes in the unvisited set is infinity (when planning a complete
     * traversal; occurs when there is no connection between the initial node and remaining unvisited nodes), then
     * stop. The algorithm has finished.
     * 6. Otherwise, select the unvisited node that is marked with the smallest tentative distance, set it as the new
     * "current node", and go back to step 3.
     *
     * @param graph             the matrix from which we take te costs
     * @param startVertex       the starting vertex
     * @param destinationVertex the ending vertex
     */
    private void dijkstra(List<List<Integer>> graph, int startVertex, int destinationVertex)
    {
        List<Integer> dist = new ArrayList<>(matrixSize);
        List<Boolean> sptSet = new ArrayList<>(graph.size());

        for (int i = 0; i < graph.size(); i++)
        {
            dist.add(Integer.MAX_VALUE);
            sptSet.add(false);
        }

        dist.set(startVertex, graph.get(startVertex).get(startVertex));

        for (int count = 0; count < graph.size() - 1; count++)
        {

            int u = minDistance(dist, sptSet);
            sptSet.set(u, true);
            for (int v = 0; v < graph.size(); v++)
            {
                if (!sptSet.get(v) && graph.get(u).get(v) != 0 &&
                        dist.get(u) != Integer.MAX_VALUE &&
                        dist.get(u) + graph.get(u).get(v) < dist.get(v))
                {
                    dist.set(v, dist.get(u) + graph.get(u).get(v));
                }
            }
        }
        printSolution(dist, startVertex, destinationVertex);
    }

    /**
     * Method to compute the shortest path between two nodes
     *
     * @param startingNode the node from which we start
     * @param endingNode   the destination vertex
     */
    public void shortestPathTwoDistinct(int startingNode, int endingNode)
    {
        dijkstra(matrix, startingNode, endingNode);
    }

    /**
     * Method to compute the shortest optimal path to every distinct pair
     * of nodes in the travel map
     */
    public void shortestPathEveryDistinctPair()
    {
        for(int node1 = 0; node1 < matrix.size() - 1; node1++)
        {
            for(int node2 = node1 + 1; node2 < matrix.size(); node2++)
            {
                dijkstra(matrix,node1,node2);
                System.out.println();
            }
        }
    }
}
