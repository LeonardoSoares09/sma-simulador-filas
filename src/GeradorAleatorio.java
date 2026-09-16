public class GeradorAleatorio {

    private long semente;
    private static final long A = 1664525;
    private static final long C = 1013904223;
    private static final long M = 1L << 32; //potencia de 2, sem nenhum arredondamento
    private int contador;

    public GeradorAleatorio(long semente, int contador) {
        this.semente = semente;
        this.contador = contador;
    }

    public double proximo(){
        this.semente = (A * semente + C) % M;
        contador--;
        return (double) semente/M; //cast vale so pra semente, fazendo o M virar double e o resultado vir com casas decimais
    }

    public boolean temAleatorios(){
        return contador > 0;
    }
    
}