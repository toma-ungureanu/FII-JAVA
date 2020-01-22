package strategies;
import state.State;
import java.util.ArrayList;

/**
 * @author Toma-Florin Ungureanu
 */
public interface IStrategy
{
    ArrayList<Integer> applyStrategy(State state, ArrayList<State> states);
}
