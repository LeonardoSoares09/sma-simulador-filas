import java.util.PriorityQueue;

public class Escalonador {

    private final PriorityQueue<Evento> eventos = new PriorityQueue<>();

    public void agendar(Evento e) {
        eventos.add(e);
    }

    public Evento proximo() {
        return eventos.poll();
    }
}