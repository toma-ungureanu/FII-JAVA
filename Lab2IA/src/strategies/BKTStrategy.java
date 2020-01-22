package strategies;

import org.jetbrains.annotations.NotNull;
import state.State;
import state.StateNode;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Toma-Florin Ungureanu
 */
public class BKTStrategy implements IStrategy
{
    private List<State> allStates = new ArrayList<>();
    private List<StateNode> allNodes = new ArrayList<>();
    private State finalState;

    public ArrayList<Integer> applyStrategy(State state,  ArrayList<State> states)
    {
        this.finalState = new State(state.getBoatCapacity(), 0, 0, 2, state.getCannibals(),
                state.getMissionaries());

        allStates.add(state);
        StateNode parentNode = new StateNode();
        allNodes.add(parentNode);
        parentNode.setCurrentState(state);
        recurse(parentNode);

        StateNode lastNode = new StateNode();
        for(StateNode stateNode: allNodes)
        {
            if(stateNode.getCurrentState().equals(this.finalState))
            {
                lastNode = stateNode;
                break;
            }
        }

        if(!(lastNode.getCurrentState() == null || lastNode.getParentNode() == null))
        {
            while (lastNode.getParentNode() != null)
            {
                states.add(lastNode.getCurrentState());
                lastNode = lastNode.getParentNode();
            }
            states.add(parentNode.getCurrentState());
        }
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

    private boolean recurse(StateNode parentNode)
    {
        if(this.allStates.contains(this.finalState))
        {
            return true;
        }

        List<State> possibilities = getNextPossibleStates(parentNode.getCurrentState());
        for(State possibility: possibilities)
        {
            if(!allStates.contains(possibility))
            {
                StateNode newNode = new StateNode(possibility, parentNode);
                parentNode.getChildNodes().add(newNode);
                allStates.add(possibility);
                allNodes.add(newNode);
                recurse(newNode);
            }
        }
        return true;
    }
}