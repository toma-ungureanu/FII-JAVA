package state;

import org.jetbrains.annotations.Contract;
import utils.Heuristic;

import java.util.ArrayList;

public class StateNodeHeuristic
{
    private State currentState;
    private StateNodeHeuristic parentNode;
    private ArrayList<StateNodeHeuristic> childNodes;
    private int value;
    private int distance;

    public int getDistance()
    {
        return distance;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public ArrayList<StateNodeHeuristic> getChildNodes()
    {
        return childNodes;
    }

    public void setChildNodes(ArrayList<StateNodeHeuristic> childNodes)
    {
        this.childNodes = childNodes;
    }

    public State getCurrentState()
    {
        return currentState;
    }

    public void setCurrentState(State currentState)
    {
        this.currentState = currentState;
    }

    public StateNodeHeuristic getParentNode()
    {
        return parentNode;
    }

    public void setParentNode(StateNodeHeuristic parentState)
    {
        this.parentNode = parentState;
    }

    @Contract(pure = true)
    public StateNodeHeuristic(State currentState, StateNodeHeuristic parentState)
    {
        this.currentState = currentState;
        this.parentNode = parentState;
        this.childNodes = new ArrayList<>();
    }

    @Contract(pure = true)
    public StateNodeHeuristic(State currentState, StateNodeHeuristic parentState, ArrayList<StateNodeHeuristic> childNodes)
    {
        this.currentState = currentState;
        this.parentNode = parentState;
        this.childNodes = childNodes;
    }

    @Contract(pure = true)
    public StateNodeHeuristic()
    {
        this.childNodes = new ArrayList<>();
    }
}
