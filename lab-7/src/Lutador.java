public class Lutador extends Combatente implements Judo
{
    public Lutador(String nome)
    {
        super(nome);
    }

    private double socar()
    {
        double forcaSoco = 15;
        double forca = this.ataqueBase * Math.random();

        return forca + forcaSoco;
    }

    private double chutar()
    {
        double forcaChute = 50;

        return this.ataqueBase + forcaChute;
    }

    @Override
    public double UchiMata() {
        return 60;
    }

    protected double decideAtaque()
    {
        if (Math.random() >= 0.2) {
            return this.UchiMata();
        }

        boolean atacaComSoco = Math.random() >= 0.6;

        if (atacaComSoco) {
            return this.socar();
        }

        return this.chutar();
    }

    private boolean esquivar()
    {
        return Math.random() >= 0.3;
    }

    private double decideDefesa(double forca)
    {
        if (this.esquivar()) {
            return 0;
        }

        boolean tomaDonaTotal = Math.random() <= 0.2;

        if (tomaDonaTotal) {
            return forca;
        }

        double defesa = forca * Math.random();

        return forca - defesa;
    }

    @Override
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
