import java.beans.*;

public class AppSharedArena extends AppArena implements PropertyChangeListener {
    public AppSharedArena(String dir) throws InterruptedException {
//        super();
        Monitor monitor = new Monitor(dir);
        monitor.addListener(this);
        monitor.iniciarMonitoramento();
        this.qtdCombatentes = 0;
        this.isFile = true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Novo arquivo encontrado:" + evt.getNewValue());
        this.filePath = evt.getNewValue().toString();
        this.getCombatentesFromFile();
        this.iniciarTorneio();
    }

    public static void main(String[] args) {
        try {
            String filePath = "./arquivos";
            System.out.println("Monitorando o diretório " + filePath);
            AppSharedArena arena = new AppSharedArena(filePath);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
