import java.util.ArrayList;

public class Arma implements Perfurante
{
    public String descricao;
    private final ArrayList<Golpe> golpes = new ArrayList<>();

    public Arma(String descricao)
    {
        this.descricao = descricao;
    }

    public double ataca()
    {
        int quantidadeDeGolpes = this.golpes.size();

        if (quantidadeDeGolpes == 0) {
            return this.perfura();
        }

        int indice = (int) (Math.random() * this.golpes.size());

        Golpe golpe = this.golpes.get(indice);

        return golpe.forca;
    }

    public void adicionaGolpe(Golpe golpe)
    {
        this.golpes.add(golpe);
    }

    @Override
    public double perfura() {
        return 50;
    }
}
