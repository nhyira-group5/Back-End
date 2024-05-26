package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Entity.Refeicao;
import API.nhyira.apivitalis.Entity.RefeicaoDiaria;
import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.RefeicaoDiariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
