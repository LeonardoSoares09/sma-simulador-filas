import java.util.Arrays;

public class Main {
     public static void main(String[] args) {
        
        Fila fila = new Fila(2, 3, 1, 5, 4, 5);

        fila.entrada();
        fila.acumulaTempo(5);

        fila.entrada();
        fila.acumulaTempo(3);

        fila.saida();
        fila.acumulaTempo(2);

        System.out.println(Arrays.toString(fila.getTempos()));
    }
    
}
