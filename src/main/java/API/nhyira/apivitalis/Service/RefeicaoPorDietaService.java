package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Entity.RefeicaoPorDieta;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.RefeicaoPorDietaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefeicaoPorDietaService {
    private final RefeicaoPorDietaRepository rpdRep;
//    private final RefeicaoRepository refRep;
//    private final DietaRepository dRep;

    public List<RefeicaoPorDieta> getAllRefeicaoPorDieta() {
        return rpdRep.findAll();
    }

    public RefeicaoPorDieta getRefeicaoPorDietaById(int id) {
        Optional<RefeicaoPorDieta> refeicaoPorDieta = rpdRep.findById(id);
        refeicaoPorDieta.orElseThrow(() -> new NaoEncontradoException("Alimento Por Refeição"));
        return refeicaoPorDieta.get();
    }
}
