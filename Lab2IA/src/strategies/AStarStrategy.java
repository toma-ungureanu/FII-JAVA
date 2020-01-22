package strategies;

import org.jetbrains.annotations.NotNull;
import state.State;
import state.StateNodeHeuristic;
import utils.Heuristic;
import utils.Utils;

import java.util.*;

public class AStarStrategy implements IStrategy
{
    private List<State> allStates = new ArrayList<>();
    private List<StateNodeHeuristic> allNodes = new ArrayList<>();
    private State finalState;

    public List<StateNodeHeuristic> getAllNodes()
    {
        return this.allNodes;
    }

    @Override
    public ArrayList<Integer> applyStrategy(State state, ArrayList<State> states)
    {
        this.finalState = new State(state.getBoatCapacity(), 0, 0, 2, state.getCannibals(),
                state.getMissionaries());

        allStates.add(state);
        StateNodeHeuristic parentNode = new StateNodeHeuristic();
        allNodes.add(parentNode);
        parentNode.setCurrentState(state);
        recurse(parentNode);
        Heuristic heuristic = new Heuristic();
        heuristic.secondH(parentNode);

        aStar(parentNode, state);
        return null;
    }

    ArrayList<State> getNextPossibleStates(@NotNull State state)
    {
        int boatCapacity = state.getBoatCapacity();
        int shore = state.getShore();
        int missionaries, cannibals;

        if (shore == 1)
        {
            missionaries = state.getMissionaries();
            cannibals = state.getCannibals();
        }
        else
        {
            missionaries = state.getMissionariesAcross();
            cannibals = state.getCannibalsAcross();
        }

        ArrayList<State> possibilities = new ArrayList<>();
        Utils utils = new Utils();
        if (missionaries != 0)
        {
            for (int missionary = 0; missionary <= missionaries; missionary++)
            {
                utils.transitionHelper(missionary, cannibals, boatCapacity, shore, state, possibilities);
            }
        }
        else
        {
            utils.transitionHelper(0, cannibals, boatCapacity, shore, state, possibilities);
        }
        return possibilities;
    }

    private boolean recurse(StateNodeHeuristic parentNode)
    {
        if (this.allStates.contains(this.finalState))
        {
            return true;
        }

        List<State> possibilities = getNextPossibleStates(parentNode.getCurrentState());
        for (State possibility : possibilities)
        {
            if (!allStates.contains(possibility))
            {
                StateNodeHeuristic newNode = new StateNodeHeuristic(possibility, parentNode);
                parentNode.getChildNodes().add(newNode);
                allStates.add(possibility);
                allNodes.add(newNode);
                recurse(newNode);
            }
        }
        return true;
    }

    private void setNodes(StateNodeHeuristic rootNode)
    {
        allNodes.add(rootNode);
        for (StateNodeHeuristic each : rootNode.getChildNodes())
        {
            setNodes(each);
        }
    }

    private boolean aStar(StateNodeHeuristic rootNode, State startState)
    {
        double bestDistance = 0;

        //initialisation step
        allNodes.clear();
        setNodes(rootNode);

        //add all the nodes into a priority queue
        PriorityQueue<StateNodeHeuristic> priorityQueue = new PriorityQueue<>(1,
                Comparator.comparingLong((StateNodeHeuristic s) -> s.getDistance() + s.getValue()));
        priorityQueue.addAll(allNodes);

        while (!priorityQueue.isEmpty())
        {
            //we extract the top of the priority queue
            StateNodeHeuristic stateNode = priorityQueue.poll();
            for (StateNodeHeuristic stateNodeHeuristic : stateNode.getChildNodes())
            {
                if (stateNode.getDistance() <= stateNodeHeuristic.getDistance())
                {
                    stateNodeHeuristic.setDistance(stateNode.getDistance());
                }
            }
        }

        return true;
    }

}
