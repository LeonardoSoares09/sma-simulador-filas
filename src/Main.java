import java.util.Arrays;

public class Main {
     public static void main(String[] args) {
        
        Fila f1 = new Fila(2, 3, 1.0, 5.0, 4.0, 5.0);
        Fila f2 = new Fila(1, 5, 0, 0, 1.0, 3.0);
        GeradorAleatorio g = new GeradorAleatorio(42, 100000);
        Simulador sim = new Simulador(f1, f2, g);
        System.out.println("Simulador criado");
    }
}