package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Entity.Treino;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.TreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreinoService {
    private final TreinoRepository treinoRepository;
    private final RotinaDiariaService rotinaDiariaService;

    public Treino show(int id){
        Optional<Treino> optTreino = treinoRepository.findById(id);
        optTreino.orElseThrow(() -> new NaoEncontradoException("Treino"));
        return optTreino.get();
    }

    public List<Integer> showCount(int id){
        List<Integer> treinos = treinoRepository.findCompletedTrainingsCount(id);
        return treinos;
    }

    public List<String> showString(int id){
        List<String> treinos = treinoRepository.findCompletedTrainingsString(id);
        return treinos;
    }

    public List<Treino> showPorDiaria(int id){
        RotinaDiaria rotinaDiaria = rotinaDiariaService.show(id);
        List<Treino> optTreino = treinoRepository.findByRotinaDiariaIdIs(rotinaDiaria);
        return optTreino;
    }

    public List<Treino> showByRotinaDiaria(RotinaDiaria rd){
        return treinoRepository.findByRotinaDiariaIdIs(rd);
    }

    public Treino updateConcluido (int id, int concluido) {
        Treino t = treinoRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Treino"));
        if (t.getConcluido() == concluido) return t;
        t.setConcluido(concluido);
        return treinoRepository.save(t);
    }
}
