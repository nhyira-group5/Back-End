package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Entity.Alimento;
import API.nhyira.apivitalis.Entity.AlimentoPorRefeicao;
import API.nhyira.apivitalis.Entity.Refeicao;
import API.nhyira.apivitalis.Entity.RefeicaoPorDieta;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.AlimentoPorRefeicaoRepository;
import API.nhyira.apivitalis.Repository.AlimentoRepository;
import API.nhyira.apivitalis.Repository.RefeicaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlimentoService {
    private final AlimentoRepository aliRep;
    private final RefeicaoRepository refeicaoRep;
    private final AlimentoPorRefeicaoRepository aprRep;

    public List<Alimento> getAllAlimentos() {
        return aliRep.findAll();
    }

    public Alimento getAlimentoById(int id) {
        Optional<Alimento> alimento = aliRep.findById(id);
        alimento.orElseThrow(() -> new NaoEncontradoException("Alimento"));
        return alimento.get();
    }

    public List<Alimento> alimentosPorRefeicao(List<AlimentoPorRefeicao> dataList) {
        List<Alimento> alimentos = new ArrayList<>();
        for (AlimentoPorRefeicao row : dataList) {
            Optional<Alimento> alimento = aliRep.findById(row.getAlimentoId().getIdAlimento());
            alimento.ifPresent(alimentos::add);
        }
        return alimentos;
    }

    public List<Alimento> getAlimentosByRefeicao(int refeicaoId) {
        Optional<Refeicao> refeicao = refeicaoRep.findById(refeicaoId);
        refeicao.orElseThrow(() -> new NaoEncontradoException("Refeição"));
        return alimentosPorRefeicao(aprRep.findByRefeicaoIdIs(refeicao.get()));
    }
}
