package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Entity.*;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.DietaRepository;
import API.nhyira.apivitalis.Repository.RefeicaoPorDietaRepository;
import API.nhyira.apivitalis.Repository.RefeicaoRepository;
import API.nhyira.apivitalis.Repository.RotinaSemanalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefeicaoService {
    private final RefeicaoRepository refRep;
    private final DietaRepository diRep;
    private final RefeicaoPorDietaRepository rpdRep;
//    private final RotinaSemanalRepository rsRep;

    public List<Refeicao> getAllRefeicoes() {
        return refRep.findAll();
    }

    public Refeicao getRefeicaoById(int id) {
        Optional<Refeicao> refeicao = refRep.findById(id);
        refeicao.orElseThrow(() -> new NaoEncontradoException("Refeição"));
        return refeicao.get();
    }

    public List<Refeicao> refeicoesPorDieta(List<RefeicaoPorDieta> dataList) {
        List<Refeicao> refeicoes = new ArrayList<>();
        for (RefeicaoPorDieta row : dataList) {
            Optional<Refeicao> refeicao = refRep.findById(row.getRefeicaoId().getIdRefeicao());
            refeicao.ifPresent(refeicoes::add);
        }
        return refeicoes;
    }

    public List<Refeicao> getRefeicaosByRefeicao(int dietaId) {
        Optional<Dieta> dieta = diRep.findById(dietaId);
        dieta.orElseThrow(() -> new NaoEncontradoException("Dieta"));
        return refeicoesPorDieta(rpdRep.findByDietaIdIs(dieta.get()));
    }
}
