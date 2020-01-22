package shell;

import catalog.Catalog;
import command.*;
import exceptions.InvalidCommand;
import graph.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Toma-Florin Ungureanu
 */
public class Shell
{
    enum SupportedComm
    {
        LOAD("LOAD"),
        SAVE("SAVE"),
        LIST("LIST"),
        OPEN("OPEN"),
        ADD("ADD"),
        REPORT("REPORT");

        private String comm;

        SupportedComm(String name)
        {
            this.comm = name;
        }

        public String getType()
        {
            return comm;
        }
    }

    private Catalog catalog;

    /**
     * @return
     */
    public void openShell()
    {
        List<String> wholeInput = new ArrayList<String>();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        try
        {
            isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            System.out.println("Please enter the path for the graph or press ENTER to use the default one");
            String path = br.readLine().trim();
            if (path.isEmpty())
            {
                path = "C:\\Users\\thomi\\IdeaProjects\\lab4\\graphs";
            }
            this.catalog = new Catalog(path);

        } catch (IOException e)
        {
            System.out.println(e.getMessage() + " " + e.getCause());
            System.exit(1);
        }

        System.out.println("Enter Command:\n");
        try
        {
            String line = null;
            while (!(line = br.readLine()).trim().equals(""))
            {
                wholeInput.add(line);
                parseCommands(line);
            }

        } catch (IOException e)
        {
            System.out.println(e.getMessage() + " " + e.getCause());
            System.exit(1);
        }
    }

    /**
     * @param commandToCheck
     * @return
     * @throws InvalidCommand
     */
    private SupportedComm checkValidCommand(String commandToCheck) throws InvalidCommand
    {
        String normalizedCommand = commandToCheck.toUpperCase();
        if (normalizedCommand.equals(SupportedComm.ADD.toString()))
        {
            return SupportedComm.ADD;
        }
        if (normalizedCommand.equals(SupportedComm.LOAD.toString()))
        {
            return SupportedComm.LOAD;
        }
        if (normalizedCommand.equals(SupportedComm.LIST.toString()))
        {
            return SupportedComm.LIST;
        }
        if (normalizedCommand.equals(SupportedComm.SAVE.toString()))
        {
            return SupportedComm.SAVE;
        }
        if (normalizedCommand.equals(SupportedComm.OPEN.toString()))
        {
            return SupportedComm.OPEN;
        }
        if(normalizedCommand.equals(SupportedComm.REPORT.toString()))
        {
            return  SupportedComm.REPORT;
        }
        else
        {
            throw new InvalidCommand("Command not supported");
        }
    }


    /**
     * @param command
     */
    public void parseCommands(String command)
    {
        StringTokenizer tokenizer = new StringTokenizer(command, ",");
        List<String> tokens = new ArrayList<>();
        while (tokenizer.hasMoreTokens())
        {
            tokens.add(tokenizer.nextToken());
        }

        if (tokens.isEmpty())
        {
            throw new InvalidCommand("Command not defined properly");
        }
        tokenizer = new StringTokenizer(tokens.get(0), "\"");
        String strippedCommand = tokenizer.nextToken().trim();
        try
        {
            SupportedComm typeOfCommand = checkValidCommand(strippedCommand);
            Command generiCommand;
            switch (typeOfCommand)
            {
                case ADD:
                {
                    String nameOfGraph = tokenizer.nextToken().trim();
                    Graph graph = new Graph(nameOfGraph, tokens.get(2).replace("\"", "").trim(),
                            tokens.get(3).replace("\"", "").trim());
                    generiCommand = new AddCommand(this.catalog,graph);
                    break;

                }
                case LIST:
                {
                    generiCommand = new ListCommand(this.catalog);
                    break;
                }
                case LOAD:
                {
                    generiCommand = new LoadCommand();
                    this.catalog = ((LoadCommand) generiCommand).loadCatalog();
                    break;
                }
                case OPEN:
                {
                    try
                    {
                        InputStreamReader isr = new InputStreamReader(System.in);
                        BufferedReader br = new BufferedReader(isr);
                        String pathOfGrph;
                        System.out.println("Enter the name of the graph you want to open:\n");
                        pathOfGrph = br.readLine().trim();
                        generiCommand = new OpenCommand(this.catalog,pathOfGrph);
                    }
                    catch (IOException e)
                    {
                        System.out.println(e.getMessage() + " " + e.getCause());
                        System.exit(1);
                    }
                    break;
                }
                case SAVE:
                {
                    try
                    {
                        InputStreamReader isr = new InputStreamReader(System.in);
                        BufferedReader br = new BufferedReader(isr);
                        String filePath;
                        System.out.println("Enter the name of the file you want to save your catalog in:\n");
                        filePath = br.readLine().trim();
                        generiCommand = new SaveCommand(this.catalog, filePath);
                    }
                     catch (IOException e)
                    {
                        System.out.println(e.getMessage() + " " + e.getCause());
                        System.exit(1);
                    }
                    break;
                }
                case REPORT:
                {
                    generiCommand = new ReportCommand(this.catalog);
                }
            }
        } catch (InvalidCommand excp)
        {
            System.out.println(excp.getMessage());
            System.exit(1);
        }
    }
}
