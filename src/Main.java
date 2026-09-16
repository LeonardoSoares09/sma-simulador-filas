import java.io.FileReader;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.out.println("Uso: java Main <arquivo de configuracao>");
            System.out.println("Exemplo: java Main ../tandem.properties");
            return;
        }

        Properties p = new Properties();
        p.load(new FileReader(args[0]));

        // parametros gerais
        double primeiraChegada = Double.parseDouble(p.getProperty("primeiraChegada"));
        long semente = Long.parseLong(p.getProperty("semente"));
        int aleatorios = Integer.parseInt(p.getProperty("aleatorios"));

        // fila 1
        int servidores1 = Integer.parseInt(p.getProperty("fila1.servidores"));
        int capacidade1 = Integer.parseInt(p.getProperty("fila1.capacidade"));

        String[] chegada1 = p.getProperty("fila1.chegada").split(",");
        double minChegada1 = Double.parseDouble(chegada1[0]);
        double maxChegada1 = Double.parseDouble(chegada1[1]);

        String[] atendimento1 = p.getProperty("fila1.atendimento").split(",");
        double minAtendimento1 = Double.parseDouble(atendimento1[0]);
        double maxAtendimento1 = Double.parseDouble(atendimento1[1]);

        // fila 2 (sem chegadas externas)
        int servidores2 = Integer.parseInt(p.getProperty("fila2.servidores"));
        int capacidade2 = Integer.parseInt(p.getProperty("fila2.capacidade"));

        String[] atendimento2 = p.getProperty("fila2.atendimento").split(",");
        double minAtendimento2 = Double.parseDouble(atendimento2[0]);
        double maxAtendimento2 = Double.parseDouble(atendimento2[1]);

        // roteamento (neste modelo em tandem e sempre 1.0 da fila 1 para a fila 2)
        double roteamento12 = Double.parseDouble(p.getProperty("roteamento.fila1.fila2"));

        Fila fila1 = new Fila(servidores1, capacidade1, minChegada1, maxChegada1, minAtendimento1, maxAtendimento1);
        Fila fila2 = new Fila(servidores2, capacidade2, 0, 0, minAtendimento2, maxAtendimento2);
        GeradorAleatorio gerador = new GeradorAleatorio(semente, aleatorios);

        System.out.println("Roteamento fila1 -> fila2: " + roteamento12);
        System.out.println();

        Simulador simulador = new Simulador(fila1, fila2, gerador);
        simulador.executar(primeiraChegada);
        simulador.imprimirResultados();
    }
}