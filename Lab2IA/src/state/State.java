package state;

import org.jetbrains.annotations.Contract;

import java.util.Objects;

/**
 * @author Toma-Florin Ungureanu
 */
public class State
{
    private int cannibals;
    private int missionaries;
    private int boatCapacity;
    private int shore;
    private int cannibalsAcross;
    private int missionariesAcross;

    public State(State state)
    {
        this.cannibals = state.cannibals;
        this.missionaries = state.cannibals;
        this.boatCapacity = state.boatCapacity;
        this.shore = state.shore;
        this.cannibalsAcross = state.cannibalsAcross;
        this.missionariesAcross = state.missionariesAcross;
    }

    public int getCannibals()
    {
        return cannibals;
    }

    public int getMissionaries()
    {
        return missionaries;
    }

    public int getShore()
    {
        return shore;
    }

    public int getBoatCapacity()
    {
        return boatCapacity;
    }

    public int getCannibalsAcross()
    {
        return cannibalsAcross;
    }

    public int getMissionariesAcross()
    {
        return missionariesAcross;
    }

    @Contract(pure = true)
    public State()
    {
        cannibals = 0;
        missionaries = 0;
        boatCapacity = 0;
        cannibalsAcross = 0;
        missionariesAcross = 0;
        shore = 1;
    }

    @Contract(pure = true)
    public State(int boatCapacity, int missionaries, int cannibals, int shore, int cannibalsAcross,
                 int missionariesAcross)
    {
        this.cannibals = cannibals;
        this.missionaries = missionaries;
        this.boatCapacity = boatCapacity;
        this.shore = shore;
        this.cannibalsAcross = cannibalsAcross;
        this.missionariesAcross = missionariesAcross;
    }

    public boolean init(int cannibals, int missionaries, int boatCapacity)
    {
        this.cannibals = cannibals;
        this.missionaries = missionaries;
        this.boatCapacity = boatCapacity;
        this.cannibalsAcross = 0;
        this.missionariesAcross = 0;
        shore = 1;

        return true;
    }

    public State transition(int missionaries, int cannibals)
    {
        return new State(boatCapacity,
                this.missionaries + missionaries,
                this.cannibals + cannibals,
                3 - this.shore,
                this.cannibalsAcross - cannibals,
                this.missionariesAcross - missionaries);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state = (State) o;
        return getCannibals() == state.getCannibals() &&
                getMissionaries() == state.getMissionaries() &&
                getBoatCapacity() == state.getBoatCapacity() &&
                getShore() == state.getShore() &&
                getCannibalsAcross() == state.getCannibalsAcross() &&
                getMissionariesAcross() == state.getMissionariesAcross();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCannibals(), getMissionaries(), getBoatCapacity(), getShore(), getCannibalsAcross(),
                getMissionariesAcross());
    }

    @Override
    public String toString() {
        return "State{" +
                "cannibals=" + cannibals +
                ", missionaries=" + missionaries +
                ", boatCapacity=" + boatCapacity +
                ", shore=" + shore +
                ", cannibalsAcross=" + cannibalsAcross +
                ", missionariesAcross=" + missionariesAcross +
                '}';
    }
}
