import java.util.Random;

public class Util
{
    private static Random random = new Random();

    public static String getItemAleatorioNoArray(String[] array)
    {
        int index = random.nextInt(array.length);

        return array[index];
    }

    public static String getRandomChar()
    {
        int randomInteger = random.nextInt(26);
        char randomChar = (char)(randomInteger + 'A');

        return String.valueOf(randomChar);
    }

    public static int getRandomInt(int min, int max)
    {
        double randomNumber = Math.random() * (max - min) + min;

        return (int) Math.floor(randomNumber);
    }

    public static int getRandomDigit()
    {
        return getRandomInt(0, 9);
    }

    public static int getRandomIntMaiorQueUm(int max)
    {
        return Util.getRandomInt(1, max);
    }
}
