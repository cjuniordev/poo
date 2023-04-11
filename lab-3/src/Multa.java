public class Multa {
    private String infracao;
    private float valor;

    public Multa(String infracao, float valor)
    {
        super();
        this.infracao = infracao;
        this.valor = valor;
    }

    public String getInfracao() {
        return infracao;
    }

    public float getValor() {
        return valor;
    }
}
