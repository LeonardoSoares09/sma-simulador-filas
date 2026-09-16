public class Fila {

    private int servidores;
    private int capacidade;
    private double minChegada;
    private double maxChegada;
    private double minAtendimento; 
    private double maxAtendimento;
    private int clientes;
    private int perdas;
    private double[] tempos;

    public Fila (int servidores, int capacidade, double minChegada, double maxChegada, double minAtendimento, double maxAtendimento) {
        this.servidores = servidores;
        this.capacidade = capacidade;
        this.minChegada = minChegada;
        this.maxChegada = maxChegada;
        this.minAtendimento = minAtendimento;
        this.maxAtendimento = maxAtendimento;
        this.clientes = 0;
        this.perdas = 0;
        this.tempos = new double[capacidade + 1];
    }

    public int status() {
        return clientes;
    }

    public int getCapacidade() {
        return this.capacidade;
    }

    public int getServidores() {
        return this.servidores;
    }

    public void perda() {
        this.perdas++;
    }

    public void entrada() {
        this.clientes++;
    }

    public void saida() {
        this.clientes--;
    }

    public double getMinChegada() {
        return minChegada;
    }

    public double getMaxChegada() {
        return maxChegada;
    }

    public double getMinAtendimento() {
        return minAtendimento;
    }

    public double getMaxAtendimento() {
        return maxAtendimento;
    }

    public int getPerdas() {
        return perdas;
    }

    public double[] getTempos() {
        return tempos;
    }

    public void acumulaTempo(double delta) {
        int i;
        tempos[clientes] += delta;
    }
}
