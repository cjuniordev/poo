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

    public void setInfracao(String infracao)
    {
        this.infracao = infracao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
