import java.util.ArrayList;

class Carro
{
    private String modelo;
    private String placa;
    private ArrayList<Multa> multas;

    public Carro(String modelo)
    {
        super();
        this.modelo = modelo;
        this.multas = new ArrayList<>();
    }

    public String getModelo()
    {
        return modelo;
    }

    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        if (placa.length() > 7) {
            placa = "PLACA INVÁLIDA";
        }

        this.placa = placa;
    }

    public void addMulta(Multa multa)
    {
        this.multas.add(multa);
    }

    public Multa[] getMultas() {
        return this.multas.toArray(new Multa[0]);
    }
}