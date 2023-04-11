import java.util.ArrayList;

public class AppDetran
{
    private static final int quantidadeDeProprietariosMaximo = 3;
    private static final int quantidadeDeCarrosMaximoPorProprietario = 2;
    private static final int quantidadeDeMultasMaximoPorCarro = 2;
    private static final float chanceDeTomarMulta = 0.7f;
    private static final String[] nomes = {"Carlos", "Pedro", "Ricardo", "Nicolas", "Brigida", "Lucas", "Bernardo"};
    private static final String[] sobrenomes = {"Rogerio", "Brito", "Modenese", "Cremasco", "Faitanin", "Littig", "Seixas"};
    private static final String[] marcas = { "Toyota", "Honda", "Ford", "Chevrolet", "Audi", "BMW", "Mercedes-Benz", "Tesla", "Porsche", "Jeep" };
    private static final String[] modelos = { "Camry", "Civic", "Mustang", "Corvette", "A4", "X5", "S-Class", "Model S", "911", "Wrangler" };
    private static final String[] valores = { "2934.70", "1467.35", "130.16", "195.23", "195.23" };
    private static final String[] infracoes = {
            "Dirigir sob efeito de álcool ou drogas (art. 165 CTB)",
            "Ultrapassagem em local proibido (art. 203 CTB)",
            "Transitar em velocidade superior à máxima permitida em até 20% (art. 218, III CTB)",
            "Estacionar em local proibido (art. 181 CTB)",
            "Não usar o cinto de segurança (art. 167 CTB)",
    };
    private ArrayList<Proprietario> proprietarios = new ArrayList<>();
    private int multasAplicadas = 0;
    private int proprietariosCadastrados = 0;
    private int carrosCadastrados = 0;


    public static String gerarCnh()
    {
        StringBuilder cnh = new StringBuilder();

        for (int i = 0; i < 11; i++) {
            cnh.append(Util.getRandomDigit());
        }

        return cnh.toString();
    }

    public Proprietario criaProprietario()
    {
        String nome = Util.getItemAleatorioNoArray(AppDetran.nomes);
        String sobrenome = Util.getItemAleatorioNoArray(AppDetran.sobrenomes);
        String cnh = gerarCnh();

        Proprietario novoProprietario = new Proprietario(nome + " " + sobrenome);
        novoProprietario.setCnh(cnh);

        this.proprietariosCadastrados++;

        return novoProprietario;
    }

    public static Carro criaCarro()
    {
        String marca = Util.getItemAleatorioNoArray(AppDetran.marcas);
        String modelo = Util.getItemAleatorioNoArray(AppDetran.modelos);

        return new Carro(marca + " " + modelo);
    }

    public static Multa criaMulta()
    {
        float valor = Float.parseFloat(Util.getItemAleatorioNoArray(AppDetran.valores));
        String infracao = Util.getItemAleatorioNoArray(AppDetran.infracoes);

        return new Multa(infracao, valor);
    }

    public Carro compraCarro(Proprietario proprietario)
    {
        Carro carro = criaCarro();
        proprietario.addCarro(carro);

        this.carrosCadastrados++;

        return carro;
    }

    public void aplicaMulta(Carro carro)
    {
        Multa multa = criaMulta();
        carro.addMulta(multa);
        this.multasAplicadas++;
    }

    public static boolean deveTomarMulta()
    {
        return Math.random() > chanceDeTomarMulta;
    }

    public static void main(String[] args)
    {
        AppDetran detran = new AppDetran();

        int quantidadeDeProprietarios = Util.getRandomIntMaiorQueUm(quantidadeDeProprietariosMaximo);

        for (int i = 0; i < quantidadeDeProprietarios; i++) {
            Proprietario proprietario = detran.criaProprietario();

            int quantidadeDeCarros = Util.getRandomIntMaiorQueUm(quantidadeDeCarrosMaximoPorProprietario);

            for (int j = 0; j < quantidadeDeCarros; j++) {
                Carro carro = detran.compraCarro(proprietario);

                int quantidadeDeMultas = Util.getRandomIntMaiorQueUm(quantidadeDeMultasMaximoPorCarro);

                for (int k = 0; k < quantidadeDeMultas; k++) {
                    if (deveTomarMulta()) {
                        detran.aplicaMulta(carro);
                    }
                }
            }

            detran.proprietarios.add(proprietario);
        }

        detran.proprietarios.forEach((proprietario) -> {
            System.out.println(proprietario.getNome() + " tem a CNH " + proprietario.getCnh() + " e os seguintes carros: ");
            Carro[] carros = proprietario.getCarros();

            for (Carro carro: carros) {
                System.out.println("\t" + carro.getModelo() + " [" + carro.getPlaca() + "]");
                Multa[] multas = carro.getMultas();

                for (Multa multa : multas) {
                    System.out.println("\t\tTomou a multa por " + multa.getInfracao() + " no valor de " + multa.getValor());
                }

                if (multas.length == 0) {
                    System.out.println("\t\tEste carro não tem multas");
                }
            }
            System.out.println();
        });

        System.out.print("O sistema do detran cadastrou um total de " + detran.proprietariosCadastrados + " proprietarios, ");
        System.out.print(detran.carrosCadastrados + " carros e ");
        System.out.println(detran.multasAplicadas + " multas.");
    }
}
