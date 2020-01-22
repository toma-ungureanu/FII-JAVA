package state;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

public class StateNode
{
    private State currentState;
    private StateNode parentNode;
    private ArrayList<StateNode> childNodes;

    public ArrayList<StateNode> getChildNodes()
    {
        return childNodes;
    }

    public void setChildNodes(ArrayList<StateNode> childNodes)
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

    public StateNode getParentNode()
    {
        return parentNode;
    }

    public void setParentNode(StateNode parentState)
    {
        this.parentNode = parentState;
    }

    @Contract(pure = true)
    public StateNode(State currentState, StateNode parentState)
    {
        this.currentState = currentState;
        this.parentNode = parentState;
        this.childNodes = new ArrayList<>();
    }

    @Contract(pure = true)
    public StateNode(State currentState, StateNode parentState, ArrayList<StateNode> childNodes)
    {
        this.currentState = currentState;
        this.parentNode = parentState;
        this.childNodes = childNodes;
    }

    @Contract(pure = true)
    public StateNode()
    {
        this.childNodes = new ArrayList<>();
    }
}
