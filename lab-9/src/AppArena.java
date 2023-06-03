import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class AppArena
{
    private static final String[] nomes = {"Carlos", "Pedro", "Ricardo", "Nicolas", "Brigida", "Lucas", "Bernardo"};
    private static final String[] sobrenomes = {"Rogerio", "Brito", "Modenese", "Cremasco", "Faitanin", "Littig", "Seixas"};
    private static final String[] nomeDeArmas = {"Espada", "Machado", "Faca", "Lança", "Arco", "Besta", "Porrete", "Clava", "Martelo"};
    private final int qtdCombatentes;
    private final String filePath;
    private final boolean isFile;

    public AppArena(int qtdCombatentes)
    {
        this.qtdCombatentes = qtdCombatentes;
        this.filePath = "";
        this.isFile = false;
    }

    public AppArena(String csvFilePath) {
        this.filePath = csvFilePath;
        this.qtdCombatentes = 0;
        this.isFile = true;
    }

    private String geraNome()
    {
        String nome = Util.getItemAleatorioNoArray(nomes);
        String sobrenome = Util.getItemAleatorioNoArray(sobrenomes);

        return nome + " " + sobrenome;
    }

    private Arma geraArma()
    {
        String nome = Util.getItemAleatorioNoArray(nomeDeArmas);
        Arma arma = new Arma(nome);

        arma.adicionaGolpe(new Golpe("Corte", 50));
        arma.adicionaGolpe(new Golpe("Estocada", 80));
        arma.adicionaGolpe(new Golpe("Golpe com a empunhadura", 10));

        return arma;
    }

    private Gladiador geraGladiador()
    {
        Gladiador gladiador = new Gladiador(geraNome(), 100);
        Arma arma = geraArma();

        gladiador.ganhaArma(arma);

        return gladiador;
    }

    private Combatente[] getCombatentesFromFile()
    {
        ArrayList<Combatente> combatentes = new ArrayList<>();

        String line = "";
        String splitBy = ";";


        try {
            BufferedReader br = new BufferedReader(new FileReader(this.filePath));
            while ((line = br.readLine()) != null)
            {
                String[] data = line.split(splitBy);
                String tipoPersonagem = data[0];
                String nome = data[1];
                
                if(tipoPersonagem.equalsIgnoreCase("Fera")) {
                    Combatente fera = new Fera(nome, Double.parseDouble(data[2]));
                    combatentes.add(fera);
                }
                else if (tipoPersonagem.equalsIgnoreCase("Lutador")) {
                    Combatente lutador = new Lutador(nome, Double.parseDouble(data[2]));
                    combatentes.add(lutador);
                }
                else if (tipoPersonagem.equalsIgnoreCase("Gladiador")) {
                    Gladiador gladiador = new Gladiador(nome, Double.parseDouble(data[2]));

                    String armaNome = data[3];
                    String golpeNome = data[4];
                    double golpeForca = 0.0;

                    if (armaNome.isEmpty()) {
                        armaNome = "Arma sem nome";
                    }

                    if (golpeNome.isEmpty()) {
                        golpeNome = "Golpe sem nome";
                    }
                    else {
                        golpeForca = Double.parseDouble(data[5]);
                    }

                    Arma arma = new Arma(data[3]);
                    Golpe golpe = new Golpe(golpeNome, golpeForca);
                    arma.adicionaGolpe(golpe);
                    gladiador.ganhaArma(arma);
                    combatentes.add(gladiador);
                }
                else {

                }

            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        Combatente[] combatentesArray = new Combatente[combatentes.size()];
        combatentes.toArray(combatentesArray);

        return combatentesArray;
    }

    private Combatente[] gerarCombantentes()
    {
        if (this.isFile) {
            return this.getCombatentesFromFile();
        }

        Combatente[] combatentes = new Combatente[this.qtdCombatentes];
        int contadorDeFeras = 0;

        for (int i = 0; i < this.qtdCombatentes; i++) {
            double random = Math.random();

            if (random <= 0.3) {
                String nome = geraNome();
                combatentes[i] = new Lutador(nome, 100);
                continue;
            }

            if (random <= 0.7) {
                combatentes[i] = geraGladiador();
                continue;
            }

            combatentes[i] = new Fera("Fera " + contadorDeFeras++, 100);
        }

        return combatentes;
    }

    public Combatente iniciarCombate(Combatente combatente1, Combatente combatente2)
    {
        int turno = 0;

        while (combatente1.estaVivo() && combatente2.estaVivo()) {
            Combatente atacante, defensor;

            if ((turno % 2) == 0) {
                atacante = combatente1;
                defensor = combatente2;
                turno = 0;
            } else {
                atacante = combatente2;
                defensor = combatente1;
            }

            System.out.println("O combatente " + atacante + " ataca " + defensor + ".");

            double forcaDoAtaque = 0;

            if (atacante instanceof Gladiador gladiador) {
                forcaDoAtaque = gladiador.atacar(defensor);
            } else {
                forcaDoAtaque = atacante.atacar(defensor);
            }


            System.out.println("O ataque tirou " + String.format("%.2f", forcaDoAtaque) + " do inimigo.");

            turno++;
        }

        return combatente1.estaVivo() ? combatente1 : combatente2;
    }

    public Combatente iniciarTorneio()
    {
        Combatente[] combatentes = gerarCombantentes();

        while(combatentes.length > 1) {
            System.out.println("Iniciando nova rodada do torneio.");

            Combatente[] ganhadores = new Combatente[combatentes.length / 2];

            for (int i = 0; i < combatentes.length; i += 2) {

                if (combatentes.length <= i + 1) {
                    continue;
                }

                System.out.println("\tIniciando combate entre " + combatentes[i] + " e " + combatentes[i+1] + ".");

                Combatente ganhador = iniciarCombate(combatentes[i], combatentes[i+1]);

                System.out.println("\t\tO combatente " + ganhador + " venceu o combate.");

                ganhadores[i / 2] = ganhador;
            }

            combatentes = ganhadores;
        }

        System.out.println("O combatente " + combatentes[0].getNome() + " venceu o torneio.");

        return combatentes[0];
    }


    public static void main(String[] args)
    {
        String filePath =  "./src/arquivoArena.csv";

        AppArena arena = new AppArena(filePath);

        String cabecalho = "tipoPersonagem;nomePersonagem;nivelEnergia;;;;;\n";

        try {
            FileWriter writer = new FileWriter("./src/campeoes.csv");
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(cabecalho);

            for (int i = 0; i < 10; i++) {
                Combatente vencedor = arena.iniciarTorneio();
                buffer.write(vencedor.converteCSV());
            }

            buffer.close();

            arena = new AppArena("./src/campeoes.csv");

            Combatente vencedor = arena.iniciarTorneio();

            writer = new FileWriter("./src/campeoes.csv", true);
            buffer = new BufferedWriter(writer);
            buffer.write(vencedor.converteCSV());

            buffer.close();

        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
