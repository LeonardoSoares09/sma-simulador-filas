public class Simulador {

    private Fila fila1;
    private Fila fila2;
    private Escalonador escalonador;
    private GeradorAleatorio gerador;
    private double tempoGlobal;

    public Simulador(Fila fila1, Fila fila2, GeradorAleatorio gerador) {
        this.fila1 = fila1;
        this.fila2 = fila2;
        this.gerador = gerador;
        this.escalonador = new Escalonador();
        this.tempoGlobal = 0;
    }

    private double sortear(double min, double max) {
        return min + (max - min) * gerador.proximo();
    }

    private void acumulaTempo(double tempoEvento) {

        double delta = tempoEvento - tempoGlobal;

        fila1.acumulaTempo(delta);
        fila2.acumulaTempo(delta);

        tempoGlobal = tempoEvento;
    }
    
}
