import exercitiul1.Lab1ex1;
import exercitiul1.helloWorld.HelloWorld;
import exercitiul2.Lab1ex2;

/**
 * @author Toma-Florin Ungureanu
 */

public class Main
{
    public static void main(String[] args)
    {
        //first exercise
        HelloWorld.print();
        System.out.println();
        Lab1ex1 ex1 = new Lab1ex1();
        ex1.method();
        System.out.println();

        //second exercise
        int retVal = 0;
        Lab1ex2 ex2 = new Lab1ex2();
        if (args[0].equals("GraphGenerator"))
        {
            retVal = ex2.createGraph(args);
        }

        if(args[0].equals("TreeGenerator"))
        {
            retVal = ex2.createTree(args);
        }

        if (retVal == 0)
        {
            System.out.println("Task successful");
        }

    }
}
