import catalog.Catalog;
import command.ReportCommand;
import graph.Graph;
import shell.Shell;


public class Main
{
    public static void main(String[] args)
    {
       Catalog catalog = new Catalog("C:\\Users\\thomi\\IdeaProjects\\lab4\\graphs");

        catalog.add (new Graph("K4",
                "C:\\Users\\thomi\\IdeaProjects\\lab4\\graphs\\tgf\\k4.tgf",
                "C:\\Users\\thomi\\IdeaProjects\\lab4\\graphs\\images\\K4.svg"));
        catalog.add (new Graph("Petersen",
                "C:\\Users\\thomi\\IdeaProjects\\lab4\\graphs\\tgf\\petersen.tgf",
                "C:\\Users\\thomi\\IdeaProjects\\lab4\\graphs\\pdfs\\Petersen.pdf"));
        catalog.add (new Graph("K3",
                "C:\\Users\\thomi\\IdeaProjects\\lab4\\graphs\\tgf\\K3.tgf",
                "C:\\Users\\thomi\\IdeaProjects\\lab4\\graphs\\images\\K3.svg"));
        catalog.open("Petersen");
        catalog.open("K3");
        catalog.save("catalog.dat");
        catalog.load("catalog.dat");
        catalog.list();

        Shell shell = new Shell();
        shell.openShell();
    }
}
