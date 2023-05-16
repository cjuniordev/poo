public class AppArena
{
    private static final String[] nomes = {"Carlos", "Pedro", "Ricardo", "Nicolas", "Brigida", "Lucas", "Bernardo"};
    private static final String[] sobrenomes = {"Rogerio", "Brito", "Modenese", "Cremasco", "Faitanin", "Littig", "Seixas"};
    private static final String[] nomeDeArmas = {"Espada", "Machado", "Faca", "Lança", "Arco", "Besta", "Porrete", "Clava", "Martelo"};
    private final int qtdCombatentes;

    public AppArena(int qtdCombatentes)
    {
        this.qtdCombatentes = qtdCombatentes;
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
        Gladiador gladiador = new Gladiador(geraNome());
        Arma arma = geraArma();

        gladiador.ganhaArma(arma);

        return gladiador;
    }

    private Combatente[] gerarCombantentes()
    {
        Combatente[] combatentes = new Combatente[this.qtdCombatentes];
        int contadorDeFeras = 0;

        for (int i = 0; i < this.qtdCombatentes; i++) {
            double random = Math.random();

            if (random <= 0.3) {
                String nome = geraNome();
                combatentes[i] = new Lutador(nome);
                continue;
            }

            if (random <= 0.7) {
                combatentes[i] = geraGladiador();
                continue;
            }

            combatentes[i] = new Fera("Fera " + contadorDeFeras++);
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

    public void iniciarTorneio()
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
    }

    public static void main(String[] args)
    {
        AppArena arena = new AppArena(10);

        arena.iniciarTorneio();
    }
}
