package API.nhyira;

import java.util.LinkedList;
import java.util.Queue;

public class FilaAgendamentos {
    private Queue<Agendamento> agendamentos;

    public FilaAgendamentos() {
        this.agendamentos = new LinkedList<>();
    }

    public void agendar(Agendamento agendamento) {
        agendamentos.offer(agendamento);
    }

    public Agendamento desenfileirar() {
        return agendamentos.poll();
    }

    public boolean isEmpty() {
        return agendamentos.isEmpty();
    }
}
