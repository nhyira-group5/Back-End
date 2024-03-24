package API.nhyira;

import java.util.Stack;

public class PilhaAgendamentos {
    private Stack<Agendamento> agendamentos;

    public PilhaAgendamentos() {
        this.agendamentos = new Stack<>();
    }

    public void agendar(Agendamento agendamento) {
        agendamentos.push(agendamento);
    }

    public Agendamento desempilhar() {
        return agendamentos.pop();
    }

    public boolean isEmpty() {
        return agendamentos.isEmpty();
    }
}
