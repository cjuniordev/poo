public class AppDetran
{
    public static void main(String[] args)
    {
        Carro carro = new Carro();
        carro.modelo = "Uno";
        Proprietario proprietario = new Proprietario();
        proprietario.nome = "Carlos";
        proprietario.meuCarro = carro;

        System.out.println(proprietario.nome + " tem um " + proprietario.meuCarro.modelo);
    }
}
