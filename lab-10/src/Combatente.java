import java.lang.reflect.Field;

public abstract class Combatente
{
    protected String nome;
    protected double energia;
    protected double ataqueBase = 10;

    public Combatente(String nome)
    {
        super();
        this.nome = nome;
        this.energia = 100;
    }

    public String getNome() {
        return nome;
    }

    public double getEnergia() {
        return energia;
    }

    public String getEnergiaFormatada()
    {
        return String.format("%.2f", energia);
    }

    @Override
    public String toString() {
        return this.nome + "[" + this.getEnergiaFormatada() + "]";
    }

    protected void aplicaDano(Combatente inimigo, double forca)
    {
        inimigo.defender(forca);
    }

    protected void recebeDano(double forca)
    {
        this.energia -= forca;
    }

    public boolean estaVivo()
    {
        return (this.energia <= 0);
    }

    public abstract double atacar(Combatente inimigo);

    public void defender(double forca)
    {
        recebeDano(forca);
    }

    public String converteCSV()
    {
        StringBuilder linha = new StringBuilder();

        Class<?> classe = this.getClass();

        linha.append(classe.getName());
        linha.append(";");

        linha.append(this.nome);
        linha.append(";");

        linha.append(this.energia);
        linha.append(";0;0;0;0;0\n");

        return linha.toString();
    }
}
