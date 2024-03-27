package API.nhyira;

import java.util.Stack;

public class PilhaProgressoMuscular {
    private Stack<ProgressoMuscular> progressoMuscular;

    public PilhaProgressoMuscular() {
        this.progressoMuscular = new Stack<>();
    }

    public void adicionar(ProgressoMuscular progresso) {
        progressoMuscular.push(progresso);
    }

    public ProgressoMuscular remover() {
        return progressoMuscular.pop();
    }

    public boolean isEmpty() {
        return progressoMuscular.isEmpty();
    }
}
