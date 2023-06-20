public class Fera extends Combatente
{
    public Fera(String nome, double energia)
    {
        super(nome);
        this.energia = energia;
    }

    private double morder()
    {
        double forcaMordida = 10;
        double forca = this.ataqueBase * Math.random();

        return forca + forcaMordida;
    }

    private double usarGarra()
    {
        double forcaGarra = 50;

        return this.ataqueBase + forcaGarra;
    }

    private double decideAtaque()
    {
        boolean atacaComMordida = Math.random() >= 0.6;

        if (atacaComMordida) {
            return this.morder();
        }

        return this.usarGarra();
    }

    private double decideDefesa(double forca)
    {
        boolean tomaDonaTotal = Math.random() <= 0.2;

        if (tomaDonaTotal) {
            return forca;
        }

        double defesa = forca * Math.random();

        return forca - defesa;
    }

    public double atacar(Combatente inimigo)
    {
        double forca = this.decideAtaque();

        aplicaDano(inimigo, forca);

        return forca;
    }

    @Override
    public void defender(double forca)
    {
        double defesa = this.decideDefesa(forca);

        this.recebeDano(forca);
    }
}
