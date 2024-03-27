package API.nhyira;

import java.util.LinkedList;
import java.util.Queue;

public class FilaProgressoMuscular {
    private Queue<ProgressoMuscular> progressoMuscular;

    public FilaProgressoMuscular() {
        this.progressoMuscular = new LinkedList<>();
    }

    public void adicionar(ProgressoMuscular progresso) {
        progressoMuscular.offer(progresso);
    }

    public ProgressoMuscular remover() {
        return progressoMuscular.poll();
    }

    public boolean isEmpty() {
        return progressoMuscular.isEmpty();
    }
}
