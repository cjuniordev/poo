public class AppArena
{
    private static final String[] nomes = {"Carlos", "Pedro", "Ricardo", "Nicolas", "Brigida", "Lucas", "Bernardo"};
    private static final String[] sobrenomes = {"Rogerio", "Brito", "Modenese", "Cremasco", "Faitanin", "Littig", "Seixas"};
    private int qtdCombatentes;
    private Combatente combatentes[];

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

    private Combatente[] gerarCombantentes()
    {
        Combatente[] combatentes = new Combatente[this.qtdCombatentes];
        int contadorDeFeras = 0;

        for (int i = 0; i < this.qtdCombatentes; i++) {
            if (Math.random() > 0.5) {
                String nome = geraNome();
                combatentes[i] = new Lutador(nome);
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
            double forcaDoAtaque = atacante.atacar(defensor);
            System.out.println("O ataque tirou " + String.format("%.2f", forcaDoAtaque) + " do inimigo.");

            turno++;
        }

        return combatente1.estaVivo() ? combatente1 : combatente2;
    }

    public void iniciarTorneio()
    {
        this.combatentes = gerarCombantentes();

        while(this.combatentes.length > 1) {
            System.out.println("Iniciando nova rodada do torneio.");

            Combatente[] ganhadores = new Combatente[this.combatentes.length / 2];

            for (int i = 0; i < this.combatentes.length; i += 2) {

                if (this.combatentes.length <= i + 1) {
                    continue;
                }

                System.out.println("\tIniciando combate entre " + this.combatentes[i] + " e " + this.combatentes[i+1] + ".");

                Combatente ganhador = iniciarCombate(this.combatentes[i], this.combatentes[i+1]);

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
