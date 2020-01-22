package game;

/**
 * @author Toma-Florin Ungureanu
 */
public class TimeKeeper implements Runnable
{
    private static final int GAMETIME = 100;
    private static final int ENDINTERVAL = 2;
    private static final int THINKING_TIME = 1000;
    private boolean endGame = false;
    private final long startTime = System.currentTimeMillis();

    public long getTimeInSeconds()
    {
        long currentTime = System.currentTimeMillis();
        return (currentTime - startTime) / 1000;
    }

    public boolean isOver()
    {
        long currentTime = getTimeInSeconds();
        if(GAMETIME - currentTime < ENDINTERVAL)
        {
            System.out.println("Time's up!\n");
            endGame = true;
            System.exit(0);
        }
        return false;
    }

    public void setEndGame(boolean val)
    {
        this.endGame = val;
    }

    public synchronized void getCurrentPlayTime()
    {
        long currentTime = getTimeInSeconds();
        System.out.println("###### Time played: " + currentTime + "######\n");
        System.out.println("###### Time remaining: " + (GAMETIME - currentTime) + "######\n");
    }

    @Override
    public void run()
    {
        while (!isOver() && !endGame)
        {
            try
            {
                Thread.sleep(THINKING_TIME);
                getCurrentPlayTime();
            }
            catch (InterruptedException excp)
            {
                System.exit(-1);
            }
        }
    }
}
