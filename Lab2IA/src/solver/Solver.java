package solver;
import org.jetbrains.annotations.Contract;
import state.State;
import state.StateNodeHeuristic;
import strategies.*;
import utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Toma-Florin Ungureanu
 */
public class Solver
{
    private State state;
    private IStrategy strategy;

    @Contract(pure = true)
    public Solver(State startingState, IStrategy strategy)
    {
        this.state = startingState;
        this.strategy = strategy;
    }

    public ArrayList<State> solve()
    {
        Utils utils = new Utils();
        boolean flag;
        int counter = 0;
        ArrayList<State> states = new ArrayList<>();

        if(this.strategy instanceof RandomStrategy)
        {
            states.add(this.state);
            while(!utils.isFinal(this.state) && counter < 1000)
            {
                counter++;
                flag = false;
                ArrayList<Integer> strategyResult = this.strategy.applyStrategy(this.state, null);
                int missionaries = strategyResult.get(0);
                int cannibals = strategyResult.get(1);

                if(utils.validation(state, missionaries, cannibals))
                {
                    State newState = this.state.transition(missionaries, cannibals);
                    states.add(newState);
                    this.state = newState;
                }
            }
        }

        else if(this.strategy instanceof BKTStrategy)
        {
            this.strategy.applyStrategy(this.state, states);
            if(!states.isEmpty())
            {
                Collections.reverse(states);
            }
        }

        else if(this.strategy instanceof IDDFSStrategy)
        {
            this.strategy.applyStrategy(this.state, states);
            if(!states.isEmpty())
            {
                Collections.reverse(states);
            }
        }

        else if(this.strategy instanceof AStarStrategy)
        {
            this.strategy.applyStrategy(this.state, states);
            if(!states.isEmpty())
            {
                Collections.reverse(states);
            }

            ArrayList<StateNodeHeuristic> sndList = (ArrayList<StateNodeHeuristic>) ((AStarStrategy) this.strategy)
                    .getAllNodes();

            StateNodeHeuristic size = Collections.max(sndList, Comparator.comparingLong(StateNodeHeuristic::getValue));
            for(int i = 0; i < size.getValue(); i++)
            {
                states.add(sndList.get(i).getCurrentState());
            }
        }
        return states;
    }

    public void solve(State state, IStrategy strategy)
    {
        this.state = state;
        this.strategy = strategy;
        solve();
    }
}
