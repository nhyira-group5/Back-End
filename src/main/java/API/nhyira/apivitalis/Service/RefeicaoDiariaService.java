package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Entity.RefeicaoDiaria;
import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.RefeicaoDiariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefeicaoDiariaService {
    private final RefeicaoDiariaRepository diariaRepository;

    public RefeicaoDiaria show(int id){
        Optional<RefeicaoDiaria> refeicaoDiaria = diariaRepository.findById(id);
        refeicaoDiaria.orElseThrow(() -> new NaoEncontradoException("Refeicao diaria"));
        return refeicaoDiaria.get();
    }

    public List<RefeicaoDiaria> showByRotinaDiaria(RotinaDiaria rd){
        return diariaRepository.findRefeicaoDiariaByRotinaDiariaIdIs(rd);
    }

    public RefeicaoDiaria updateConcluido (int id, int concluido) {
        RefeicaoDiaria refd = diariaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Refeiçao Diaria"));
        if (refd.getConcluido() == concluido) return refd;
        refd.setConcluido(concluido);
        return diariaRepository.save(refd);
    }
}
