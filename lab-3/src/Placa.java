import java.util.Random;

public class Placa {
    private static int sequencial = 0;
    private String placa;

    public Placa()
    {
        this.placa = Placa.getRandomPlaca();
    }

    private static int getSequencial()
    {
        int ultimoSequencial = Placa.sequencial;
        Placa.sequencial++;

        return ultimoSequencial;
    }

    private static String getRandomPlaca()
    {
        StringBuilder placa = new StringBuilder();
        String sequencialFormatado = String.format("%03d", Placa.getSequencial());

        for (int i = 0; i < 3; i++) {
            placa.append(Util.getRandomChar());
        }

        placa
            .append("-")
            .append(Util.getRandomDigit())
            .append(Util.getRandomChar())
            .append(sequencialFormatado);

        return placa.toString();
    }

    @Override
    public String toString() {
        return placa;
    }
}
