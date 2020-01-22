package statgenerator;

import org.jetbrains.annotations.Contract;

import java.time.Duration;
import java.util.ArrayList;

public class StatisticsGenerator
{
    private ArrayList<Duration> durations;
    private ArrayList<Integer> transitions;
    private int instances;

    public int getInstances()
    {
        return instances;
    }

    public void setInstances(int instances)
    {
        this.instances = instances;
    }

    public String generateTimeStatistics() throws NullPointerException
    {
        if(durations.isEmpty())
        {
            throw new ArrayIndexOutOfBoundsException("Assign the generator variables first!");
        }
        return this.durations.stream().mapToDouble(Duration::toMillis).sum() / instances + "ms";
    }

    public String generateTransitionStatistics() throws NullPointerException
    {
        if(transitions.isEmpty())
        {
            throw new ArrayIndexOutOfBoundsException("Assign the generator variables first!");
        }
       return this.transitions.stream().mapToDouble(a -> a).sum() / instances + " transitions";
    }

    @Contract(pure = true)
    public StatisticsGenerator(ArrayList<Duration> durations, ArrayList<Integer> transitions, int instances)
    {
        this.transitions = transitions;
        this.durations = durations;
        this.instances = instances;
    }

    @Contract(pure = true)
    public  StatisticsGenerator(){ this.transitions = new ArrayList<>(); this.durations = new ArrayList<>();}

    public ArrayList<Duration> getDurations()
    {
        return durations;
    }

    public void setDurations(ArrayList<Duration> durations)
    {
        this.durations = durations;
    }

    public ArrayList<Integer> getTransitions()
    {
        return transitions;
    }

    public void setTransitions(ArrayList<Integer> transitions)
    {
        this.transitions = transitions;
    }
}
