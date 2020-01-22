package strategies;

import state.State;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Toma-Florin Ungureanu
 */
public class RandomStrategy implements IStrategy
{
    public ArrayList<Integer> applyStrategy(State state, ArrayList<State> states)
    {
        int chosenMissionaries = 0, chosenCannibals = 0;
        int boatCapacity  = state.getBoatCapacity();
        int shore = state.getShore();
        int possibleMissionaries = state.getMissionaries();
        int possibleCannibals = state.getCannibals();
        int possibleCannibalsAccross = state.getCannibalsAcross();
        int possibleMissionariesAccross = state.getMissionariesAcross();

        Random random = new Random();
        if(shore == 1)
        {
            while(Math.abs(chosenCannibals + chosenMissionaries) > boatCapacity ||
                    Math.abs(chosenCannibals + chosenMissionaries) == 0)
            {
                chosenMissionaries = -random.nextInt(possibleMissionaries + 1);
                chosenCannibals = -random.nextInt(possibleCannibals + 1);
            }
        }
        else
        {
            while(Math.abs(chosenCannibals + chosenMissionaries) > boatCapacity ||
                    Math.abs(chosenCannibals + chosenMissionaries) == 0)
            {
                if(possibleMissionariesAccross != 0)
                {
                    chosenMissionaries = random.nextInt(possibleMissionariesAccross + 1);
                }

                if(possibleCannibalsAccross != 0)
                {
                    chosenCannibals = random.nextInt(possibleCannibalsAccross + 1);
                }
            }
        }

        ArrayList<Integer> returnArray = new ArrayList<>();
        returnArray.add(chosenMissionaries);
        returnArray.add(chosenCannibals);
        return returnArray;
    }
}
