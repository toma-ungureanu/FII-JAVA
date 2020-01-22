package exercitiul2;

import java.text.DecimalFormat;

import exercitiul2.graphs.CompleteGraph;
import exercitiul2.graphs.CycleGraph;
import exercitiul2.graphs.GenericGraph;
import exercitiul2.graphs.RandomGraph;
import exercitiul2.graphs.RandomTree;

/**
 * @author Toma-Florin Ungureanu
 */

public class Lab1ex2
{
    public int createGraph(String[] args)
    {
        //we mark the start of the program
        double startTime = System.nanoTime();

        try
        {
            int vertices = Integer.parseInt(args[1]);
            String input = args[2];
            if (!(input.equals("complete") ||
                    input.equals("random") ||
                    input.equals("cycle")))
            {
                System.out.println("THIRD argument is incorrect!");
                return 1;
            }

            //we mark it as null, otherwise it is not initialized => error
            GenericGraph genericGraph = null;
            boolean retVal = true;

            //specialize the graph
            switch (input)
            {
                case "complete":
                {
                    genericGraph = new CompleteGraph();
                    retVal = ((CompleteGraph) genericGraph).create(vertices);
                    break;
                }

                case "random":
                {
                    genericGraph = new RandomGraph();
                    retVal = ((RandomGraph) genericGraph).create(vertices);
                    if (!retVal)
                    {
                        return -1;
                    }

                    System.out.println("Number of connected components: " +
                            ((RandomGraph) genericGraph).nrOfConnectedComponents());
                    System.out.println();
                    retVal = ((RandomGraph) genericGraph).checkConnected();
                    if (retVal)
                    {
                        System.out.println("The graph is connected!");
                    } else
                    {
                        System.out.println("The graph is NOT connected");
                    }

                    System.out.println();
                    break;
                }

                case "cycle":
                {
                    genericGraph = new CycleGraph();
                    retVal = ((CycleGraph) genericGraph).create(vertices);
                    if (!retVal)
                    {
                        return -1;
                    }
                    break;
                }

                default:
                {
                    break;
                }
            }

            //check the dimensions to see if we print it
            if (genericGraph.getDim() < 100)
            {
                genericGraph.displayGraph();
            }

            //display the number of edges
            genericGraph.displayNrOfEdges();

            //if the maximum degree = minimum degree
            //the graph is regular
            long degMax = genericGraph.maxDegree();
            long degMin = genericGraph.minDegree();
            if (degMax == degMin)
            {
                System.out.println("The graph is regular");
                System.out.println();
            } else
            {
                System.out.println("The graph is NOT regular");
                System.out.println();
            }

            //we check for the property of sum of degrees equals 2 * edges
            if (genericGraph.checkSumDegrees())
            {
                System.out.println("The graph matches the property");
                System.out.println();
            } else
            {
                System.out.println("The graph does not match the property");
                System.out.println();
            }

            //if we have more than 100 vertices
            if (genericGraph.getDim() > 100)
            {
                //we mark the endpoint
                double endTime = System.nanoTime();

                //we convert it into seconds from milliseconds
                double totalTime = (endTime - startTime) / 1000000000;

                //we take 5 decimals precision
                DecimalFormat df = new DecimalFormat("#.#####");
                System.out.println("Time taken: " + df.format(totalTime) + "s");
            }

        } catch (NumberFormatException excp)
        {
            System.out.println("SECOND argument is incorrect!");
            System.out.println("Exception caught: " + excp);
            return -1;
        }

        return 0;
    }

    public int createTree(String[] args)
    {
        GenericGraph tree = new RandomTree();
        tree.create(Integer.parseInt(args[1]));
        //check the dimensions to see if we print it
        if (tree.getDim() < 100)
        {
            tree.displayGraph();
        }
        return 0;
    }
}
