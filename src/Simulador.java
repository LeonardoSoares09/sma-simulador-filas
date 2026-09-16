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

        private void chegada(Evento e) {
        acumulaTempo(e.getTempo());

        if (fila1.status() < fila1.getCapacidade()) {
            fila1.entrada();
            if (fila1.status() <= fila1.getServidores() && gerador.temAleatorios()) {
                double atendimento = sortear(fila1.getMinAtendimento(), fila1.getMaxAtendimento());
                escalonador.agendar(new Evento(Evento.Tipo.PASSAGEM, e.getTempo() + atendimento));
            }
        } else {
            fila1.perda();
        }

        if (gerador.temAleatorios()) {
            double intervalo = sortear(fila1.getMinChegada(), fila1.getMaxChegada());
            escalonador.agendar(new Evento(Evento.Tipo.CHEGADA, e.getTempo() + intervalo));
        }
    }

    private void passagem(Evento e) {
        acumulaTempo(e.getTempo());

        // cliente sai da fila 1
        fila1.saida();
        if (fila1.status() >= fila1.getServidores() && gerador.temAleatorios()) {
            double atendimento = sortear(fila1.getMinAtendimento(), fila1.getMaxAtendimento());
            escalonador.agendar(new Evento(Evento.Tipo.PASSAGEM, e.getTempo() + atendimento));
        }

        // e tenta entrar na fila 2
        if (fila2.status() < fila2.getCapacidade()) {
            fila2.entrada();
            if (fila2.status() <= fila2.getServidores() && gerador.temAleatorios()) {
                double atendimento = sortear(fila2.getMinAtendimento(), fila2.getMaxAtendimento());
                escalonador.agendar(new Evento(Evento.Tipo.SAIDA, e.getTempo() + atendimento));
            }
        } else {
            fila2.perda();
        }
    }

    private void saida(Evento e) {
        acumulaTempo(e.getTempo());

        fila2.saida();
        if (fila2.status() >= fila2.getServidores() && gerador.temAleatorios()) {
            double atendimento = sortear(fila2.getMinAtendimento(), fila2.getMaxAtendimento());
            escalonador.agendar(new Evento(Evento.Tipo.SAIDA, e.getTempo() + atendimento));
        }
    }
    
        public void executar(double primeiraChegada) {
        escalonador.agendar(new Evento(Evento.Tipo.CHEGADA, primeiraChegada));

        while (gerador.temAleatorios()) {
            Evento e = escalonador.proximo();

            if (e.getTipo() == Evento.Tipo.CHEGADA) {
                chegada(e);
            } else if (e.getTipo() == Evento.Tipo.PASSAGEM) {
                passagem(e);
            } else if (e.getTipo() == Evento.Tipo.SAIDA) {
                saida(e);
            }
        }
    }

    public void imprimirResultados() {
        imprimirFila("Fila 1", fila1);
        imprimirFila("Fila 2", fila2);
        System.out.printf("Tempo global da simulacao: %.4f%n", tempoGlobal);
    }

    private void imprimirFila(String nome, Fila fila) {
        System.out.printf("%s (G/G/%d/%d)%n", nome, fila.getServidores(), fila.getCapacidade());
        System.out.println("Estado    Tempo acumulado    Probabilidade");

        double[] tempos = fila.getTempos();
        for (int i = 0; i < tempos.length; i++) {
            double probabilidade = tempos[i] / tempoGlobal * 100;
            System.out.printf("%6d %18.4f %15.2f%%%n", i, tempos[i], probabilidade);
        }

        System.out.println("Perdas: " + fila.getPerdas());
        System.out.println();
    }
}