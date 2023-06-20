import java.beans.*;
import java.io.*;
import java.util.*;

public class Monitor {

    final int TIME_OUT = 50000; //ms

    String diretorio;
    List arquivosPrevios;
    private PropertyChangeSupport listener;

    public Monitor(String diretorio) {
        this.diretorio = diretorio;
        listener = new PropertyChangeSupport(this);
        arquivosPrevios = new ArrayList();
    }

    public void addListener(PropertyChangeListener listener) {
        this.listener.addPropertyChangeListener(listener);
    }

    public void iniciarMonitoramento() throws InterruptedException {
        long timeOut = 0;
        File dir = new File(diretorio);

        if(!dir.isDirectory()) {
            System.out.println("Diretório não encontrado.");
            return;
        }

        while(timeOut < TIME_OUT) {
            FilenameFilter filter = (d, name) -> name.endsWith(".csv");
            File[] files = dir.listFiles(filter);

            List<File> arquivos = Arrays.asList(files);

            if(arquivos.size() == arquivosPrevios.size()) {
                Thread.sleep(1000);
                timeOut+=1000;
            }else {
                timeOut = 0;
                //Arrays.asList(files);

                List<File> differences = new ArrayList<>(arquivos);
                differences.removeAll(arquivosPrevios);

                for (File file : differences) {
                    listener.firePropertyChange( new PropertyChangeEvent(file,"filePath","",file.getAbsolutePath()));
                }
                arquivosPrevios = Arrays.asList(files);
            }
        }
    }
}
