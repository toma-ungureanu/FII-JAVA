package server;

import java.io.IOException;

/**
 * @author Toma-Florin Ungureanu
 */
public class ClientMonitor implements Runnable
{
    private static final int EXCEEDTIME = 20;
    private long startTime;
    private long timeRemaining;
    private boolean isRunning = true;
    private ClientThread clientThread;

    public ClientMonitor(ClientThread clientThread)
    {
        this.clientThread = clientThread;
        startTime = System.currentTimeMillis();
    }
    private long getTimeDiffInSeconds()
    {
        long currentTime = System.currentTimeMillis();
        return (currentTime - startTime) / 1000;
    }

    private void setStartTime(long time)
    {
        startTime = time;
    }

    public void setRunning(boolean isRunning)
    {
        this.isRunning = isRunning;
    }

    public void setTimeRemaining(long timeDiff)
    {
        timeRemaining = timeDiff;
    }

    @Override
    public void run()
    {
        while(isRunning)
        {
            try
            {
                while (getTimeDiffInSeconds() < EXCEEDTIME)
                {
                    Thread.sleep(1000);
                    setTimeRemaining(EXCEEDTIME - getTimeDiffInSeconds());
                    //System.out.println("Awaiting response in " + (EXCEEDTIME - getTimeDiffInSeconds()));
                }
                System.out.println("End connection");
                clientThread.setSocket(false);
                isRunning = false;
            } catch (InterruptedException excp)
            {
                System.out.println("Request received from client with thread id: " + clientThread.getId());
                setStartTime(System.currentTimeMillis());
                setTimeRemaining(EXCEEDTIME);
            }
        }

    }
}
