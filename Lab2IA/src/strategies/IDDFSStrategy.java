package strategies;

import org.jetbrains.annotations.NotNull;
import state.State;
import state.StateNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Toma-Florin Ungureanu
 */
public class IDDFSStrategy implements IStrategy
{
    private List<State> allStates = new ArrayList<>();
    private List<StateNode> allNodes = new ArrayList<>();
    private ArrayList<State> solution = new ArrayList<>();

    public ArrayList<Integer> applyStrategy(State state,  ArrayList<State> states)
    {
        int missionaries = 0, cannibals = 0;
        StateNode finalNode = new StateNode(new State(state.getBoatCapacity(), 0, 0, 2,
                state.getCannibals(), state.getMissionaries()), null, null);

        StateNode rootNode = new StateNode(state, null);
        constructTree(rootNode);
        for(int depth = 0;; depth++)
        {
            if(IDDFS(rootNode, finalNode, depth))
            {
                break;
            }
            else if(depth == 100)
            {
                break;
            }
        }
        //apply strategy
        states.addAll(this.solution);
        ArrayList<Integer> returnArray = new ArrayList<>();
        returnArray.add(missionaries);
        returnArray.add(cannibals);

        return returnArray;
    }

    private boolean IDDFS(StateNode node, StateNode finalState, int maxDepth)
    {
        for(int depth = 0; depth < maxDepth; depth++)
        {
            if(DLS(node, finalState, depth))
            {
                return true;
            }
        }
        return false;
    }

    private boolean DLS(@NotNull StateNode node, @NotNull StateNode finalState, int depth)
    {
        if(node.getCurrentState().equals(finalState.getCurrentState()))
        {
            while(node.getParentNode() != null)
            {
                this.solution.add(node.getCurrentState());
                node = node.getParentNode();
            }
            return true;
        }

        if(depth == 0)
        {
            return false;
        }

        for(StateNode stateNode: node.getChildNodes())
        {
            if(DLS(stateNode, finalState, depth - 1))
            {
                return true;
            }
        }
        return false;
    }

    private void constructTree(@NotNull StateNode rootNode)
    {
        this.allStates.add(rootNode.getCurrentState());
        this.allNodes.add(rootNode);
        recurse(rootNode);
    }

    private boolean recurse(@NotNull StateNode node)
    {
        BKTStrategy bktStrategy = new BKTStrategy();
        List<State> possibilities = bktStrategy.getNextPossibleStates(node.getCurrentState());
        for(State possibility: possibilities)
        {
            if(!allStates.contains(possibility))
            {
                StateNode newNode = new StateNode(possibility, node);
                node.getChildNodes().add(newNode);
                allStates.add(possibility);
                allNodes.add(newNode);
                recurse(newNode);
            }
        }
        return true;
    }
}
