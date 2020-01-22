package utils;

import org.jetbrains.annotations.Contract;
import state.StateNodeHeuristic;

public class Heuristic
{
    private double value;

    @Contract(pure = true)
    public Heuristic(){}

    /**
     * This function will go through each node and assign (missionaries + cannibals) / boatCapacity value
     * We assume that the final state exists in the tree
     * @param rootNode the root node of the tree. We have access to the tree from here
     */
    public boolean firstH(StateNodeHeuristic rootNode)
    {
        if(rootNode == null)
        {
            return false;
        }

        rootNode.setValue(rootNode.getCurrentState().getMissionaries() +
                rootNode.getCurrentState().getCannibals());

        if(rootNode.getChildNodes() != null)
        {
            for (StateNodeHeuristic stateNode : rootNode.getChildNodes())
            {
                stateNode.setValue((stateNode.getCurrentState().getMissionaries() +
                        stateNode.getCurrentState().getCannibals())/stateNode.getCurrentState().getBoatCapacity());

                firstH(stateNode);
            }
        }

        return true;
    }

    /**
     * This function will go through each node and assign cannibals + missionaries value
     * We assume that the final state exists in the tree
     * @param rootNode the root node of the tree. We have access to the tree from here
     */
    public boolean secondH(StateNodeHeuristic rootNode)
    {
        if(rootNode == null)
        {
            return false;
        }

        rootNode.setValue(rootNode.getCurrentState().getMissionaries() +
                rootNode.getCurrentState().getCannibals());

        if(rootNode.getChildNodes() != null)
        {
            for (StateNodeHeuristic stateNode : rootNode.getChildNodes())
            {
                stateNode.setValue((stateNode.getCurrentState().getMissionaries() +
                        stateNode.getCurrentState().getCannibals()));

                secondH(stateNode);
            }
        }

        return true;
    }

    /**
     * This function will go through each node and assign cannibals + missionaries value
     * We assume that the final state exists in the tree
     * @param rootNode the root node of the tree. We have access to the tree from here
     */
    public boolean thirdH(StateNodeHeuristic rootNode)
    {
        if(rootNode == null)
        {
            return false;
        }

        rootNode.setValue(rootNode.getCurrentState().getMissionaries() +
                rootNode.getCurrentState().getCannibals());

        if(rootNode.getChildNodes() != null)
        {
            for (StateNodeHeuristic stateNode : rootNode.getChildNodes())
            {
                stateNode.setValue(100/(stateNode.getCurrentState().getMissionariesAcross() +
                        stateNode.getCurrentState().getCannibalsAcross()));

                thirdH(stateNode);
            }
        }

        return true;
    }

}
