import java.util.Arrays;

public class Main {
     public static void main(String[] args) {
        
        Escalonador esc = new Escalonador();
        esc.agendar(new Evento(Evento.Tipo.SAIDA, 7.0));
        esc.agendar(new Evento(Evento.Tipo.CHEGADA, 2.5));
        esc.agendar(new Evento(Evento.Tipo.PASSAGEM, 4.3));
        esc.agendar(new Evento(Evento.Tipo.CHEGADA, 4.0));

        for (int i = 0; i < 4; i++) {
            Evento e = esc.proximo();
            System.out.println(e.getTempo() + " " + e.getTipo());
        }
    }
}