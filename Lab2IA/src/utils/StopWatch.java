package utils;
import java.time.Duration;
import java.time.Instant;

public class StopWatch
{
    private static Instant startTime;
    public static void start()
    {
        startTime = Instant.now();
    }

    public static Duration stop() throws Exception
    {
        Instant endTime = Instant.now();
        if(startTime == null)
        {
            throw new Exception("Start not called");
        }
        return Duration.between(startTime, endTime);
    }
}
