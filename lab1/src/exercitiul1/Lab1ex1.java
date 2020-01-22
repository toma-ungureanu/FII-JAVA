package exercitiul1;

import exercitiul1.numere.Numere;

public class Lab1ex1 extends Numere
{
    static String languages[] = {"C", "C++", "C#", "Go", "JavaScript", "Perl", "PHP", "Python", "Swift", "Java"};

    public void method()
    {
        int n = generateRandom();
        n*= 3;
        n+= 0b10101;
        n+= 0xFF;
        n*= 6;
        int sum = calc(n);
        System.out.println("Willy-nilly, this semester I will learn " + languages[sum]);
    }

}
