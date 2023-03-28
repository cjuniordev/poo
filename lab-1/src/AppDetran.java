public class AppDetran
{
    public static void main(String[] args)
    {
        Carro carro = new Carro();
        carro.setModelo("Uno");

        Proprietario proprietario = new Proprietario();
        proprietario.setNome("Carlos");
        proprietario.setMeuCarro(carro);

        System.out.println(proprietario.getNome() + " tem um " + proprietario.getMeuCarro().getModelo());
    }
}
