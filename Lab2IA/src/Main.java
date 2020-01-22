import statgenerator.StatisticsGenerator;
import solver.Solver;
import state.State;
import strategies.*;
import utils.StopWatch;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

public class Main
{
    private static final int MAX_BOAT_CAPACITY = 5;
    private static final int MIN_BOAT_CAPACITY = 2;
    private static final int MAX_EACH_TYPE = 15;
    private static final int MIN_EACH_TYPE = 3;

    public static void main(String[] args) throws Exception
    {
        Random random = new Random();
        IStrategy randomStrat;
        IStrategy bktStrat;
        IStrategy iddfsStrat;
        IStrategy aStarStrat;
        State state;
        Solver randomSolver, bktSolver, iddfsSolver, aStarSolver;
        int boatCapacity, missionaries, cannibals;
        ArrayList<Duration> randomTimeStats = new ArrayList<>();
        ArrayList<Duration> bktTimeStats = new ArrayList<>();
        ArrayList<Duration> iddfsTimeStats = new ArrayList<>();
        ArrayList<Duration> aStarTimeStats = new ArrayList<>();

        ArrayList<Integer> randomTransitionStats = new ArrayList<>();
        ArrayList<Integer> bktTransitionStats = new ArrayList<>();
        ArrayList<Integer> iddfsTransitionStats = new ArrayList<>();
        ArrayList<Integer> aStarTransitionStats = new ArrayList<>();

        int instances = 1000;
        for (int i = 0; i < instances; i++)
        {
            boatCapacity = random.nextInt(MAX_BOAT_CAPACITY - MIN_BOAT_CAPACITY) + MIN_BOAT_CAPACITY;
            missionaries = random.nextInt(MAX_EACH_TYPE - MIN_EACH_TYPE) + MIN_EACH_TYPE;
            cannibals = random.nextInt(MAX_EACH_TYPE - MIN_EACH_TYPE) + MIN_EACH_TYPE;
            while (cannibals > missionaries)
            {
                cannibals = random.nextInt(MAX_EACH_TYPE - MIN_EACH_TYPE) + MIN_EACH_TYPE;
            }
            state = new State(boatCapacity, missionaries, cannibals, 1, 0, 0);

            randomStrat = new RandomStrategy();
            bktStrat = new BKTStrategy();
            iddfsStrat = new IDDFSStrategy();
            aStarStrat = new AStarStrategy();

            //random solve
            StopWatch.start();
            randomSolver = new Solver(state, randomStrat);
            randomTransitionStats.add(randomSolver.solve().size());
            randomTimeStats.add(StopWatch.stop());

            //bkt solve
            StopWatch.start();
            bktSolver = new Solver(state, bktStrat);
            bktTransitionStats.add(bktSolver.solve().size());
            bktTimeStats.add(StopWatch.stop());

            //iddfs solve
            StopWatch.start();
            iddfsSolver = new Solver(state, iddfsStrat);
            iddfsTransitionStats.add(iddfsSolver.solve().size());
            iddfsTimeStats.add(StopWatch.stop());

            //A* solve
            StopWatch.start();
            aStarSolver = new Solver(state, aStarStrat);
            aStarTransitionStats.add(aStarSolver.solve().size());
            aStarTimeStats.add(StopWatch.stop());
        }

        System.out.println("//////////////// TIME ////////////////");
        for (int i = 0; i < instances; i++)
        {
            System.out.println("\nInstance " + (i + 1) + ":");
            System.out.println("Random: " + randomTimeStats.get(i) + " || BKT: " + bktTimeStats.get(i) + " || IDDFS: "
                    + iddfsTimeStats.get(i) + " || A*: " + aStarTimeStats.get(i));
        }
        System.out.println();
        System.out.println("//////////////// TRANSITIONS ////////////////");
        for (int i = 0; i < instances; i++)
        {
            System.out.println("\nInstance " + (i + 1) + ":");
            System.out.println("Random: " + randomTransitionStats.get(i) + " || BKT: " + bktTransitionStats.get(i) +
                    " || IDDFS: " + iddfsTransitionStats.get(i) + " || A*: " + aStarTransitionStats.get(i));
        }

        System.out.println("\n//////////////// STATISTICS ////////////////");
        StatisticsGenerator statisticsGenerator = new StatisticsGenerator();
        statisticsGenerator.setInstances(instances);

        ArrayList<ArrayList<Duration>> allTimeStats = new ArrayList<>();
        allTimeStats.add(randomTimeStats);
        allTimeStats.add(bktTimeStats);
        allTimeStats.add(iddfsTimeStats);
        allTimeStats.add(aStarTimeStats);

        ArrayList<ArrayList<Integer>> allTransitionStats = new ArrayList<>();
        allTransitionStats.add(randomTransitionStats);
        allTransitionStats.add(bktTransitionStats);
        allTransitionStats.add(iddfsTransitionStats);
        allTransitionStats.add(aStarTransitionStats);

        for(int i = 0; i < 4; i++)
        {
            statisticsGenerator.setDurations(allTimeStats.get(i));
            statisticsGenerator.setTransitions(allTransitionStats.get(i));
            switch (i)
            {
                case 0:
                    System.out.print("Random: ");
                    break;
                case 1:
                    System.out.print("\nBKT: ");
                    break;
                case 2:
                    System.out.print("\nIDDFS: ");
                    break;
                case 3:
                    System.out.print("\nA*: ");
                    break;
                default:
                    break;
            }
            System.out.println(statisticsGenerator.generateTimeStatistics());
            System.out.println(statisticsGenerator.generateTransitionStatistics());
        }
    }
}
