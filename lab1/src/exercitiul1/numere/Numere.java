package exercitiul1.numere;

public class Numere
{
    protected int generateRandom()
    {
        return (int) (Math.random() * 1_000_000);
    }

    protected int calc(int n)
    {
        int sum = 0;
        while(n > 0 || sum > 9)
        {
            if(n == 0)
            {
                n = sum;
                sum = 0;
            }
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

}
