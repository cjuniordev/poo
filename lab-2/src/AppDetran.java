public class AppDetran
{
    public static void main(String[] args)
    {
        Multa velocidade = new Multa("Acima de 800km", 1000);
        Multa estacionadoEmVagaProibida = new Multa("Vaga errada", 50.01f);

        Carro uno = new Carro("Uno 5.0");
        Carro siena = new Carro("Siena Turbo");

        uno.setPlaca("BRL-000");
        siena.setPlaca("BRL-0001");

        uno.addMulta(velocidade);
        uno.addMulta(estacionadoEmVagaProibida);

        Proprietario proprietario = new Proprietario("Carlos");
        proprietario.setCnh("123456789");

        proprietario.addCarro(uno);
        proprietario.addCarro(siena);

        Carro[] meusCarros = proprietario.getCarros();

        System.out.println(proprietario.getNome() + " tem a CNH " + proprietario.getCnh() + " e os seguintes carros: \n");

        for (Carro carro : meusCarros) {
            System.out.println("O carro " + carro.getModelo() + " [" + carro.getPlaca() + "]");

            Multa[] multas = carro.getMultas();

            for (Multa multa : multas) {
                System.out.println("\tTomou a multa por " + multa.getInfracao() + " no valor de " + multa.getValor());
            }
        }

    }
}
