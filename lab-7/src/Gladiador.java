import java.util.ArrayList;

public class Gladiador extends Lutador
{
    public ArrayList<Arma> armas = new ArrayList<>();

    public Gladiador(String nome)
    {
        super("Gladiador " + nome);
    }

    @Override
    public double atacar(Combatente inimigo)
    {
        double forca = 0;
        int quantidadeDeArmas = this.armas.size();

        if (quantidadeDeArmas == 0) {
            return forca;
        }

        int indice = (int) (Math.random() * quantidadeDeArmas);

        Arma arma = this.armas.get(indice);

        forca = arma.ataca();

        aplicaDano(inimigo, forca);

        return forca;
    }

    public void ganhaArma(Arma arma)
    {
        this.armas.add(arma);
    }
}
