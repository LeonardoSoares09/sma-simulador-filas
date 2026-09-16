public class Evento implements Comparable<Evento> {

    public enum Tipo { CHEGADA, PASSAGEM, SAIDA};

    private final Tipo tipo;
    private final double tempo;

    public Evento(Tipo tipo, double tempo) {
        this.tipo = tipo;
        this.tempo = tempo;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public double getTempo() {
        return this.tempo;
    }

    @Override
    public int compareTo(Evento outro) {
        return Double.compare(this.tempo, outro.tempo);
    }
    
}