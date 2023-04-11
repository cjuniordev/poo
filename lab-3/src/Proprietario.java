import java.util.ArrayList;

public class Proprietario
{
    private String nome;
    private String cnh;
    private final ArrayList<Carro> carros;

    public Proprietario(String nome)
    {
        super();
        this.nome = nome;
        this.carros = new ArrayList<>();
    }

    public String getNome()
    {
        return this.nome;
    }

    public String getCnh()
    {
        return this.cnh;
    }

    public void setCnh(String cnh)
    {
        this.cnh = cnh;
    }

    public Carro[] getCarros()
    {
        return this.carros.toArray(new Carro[0]);
    }

    public void addCarro(Carro carro)
    {
        this.carros.add(carro);
    }
}
