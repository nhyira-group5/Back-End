package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Entity.Alimento;
import API.nhyira.apivitalis.Entity.AlimentoPorRefeicao;
import API.nhyira.apivitalis.Entity.Refeicao;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.AlimentoPorRefeicaoRepository;
import API.nhyira.apivitalis.Repository.AlimentoRepository;
import API.nhyira.apivitalis.Repository.RefeicaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlimentoPorRefeicaoService {
    private final AlimentoPorRefeicaoRepository aprRep;
    private final AlimentoRepository aliRep;
    private final RefeicaoRepository refRep;

    public List<AlimentoPorRefeicao> getAllAlimentoPorRefeicao() {
        return aprRep.findAll();
    }

    public AlimentoPorRefeicao getAlimentoPorRefeicaoById(int id) {
        Optional<AlimentoPorRefeicao> alimentoPorRefeicao = aprRep.findById(id);
        alimentoPorRefeicao.orElseThrow(() -> new NaoEncontradoException("Alimento Por Refeição"));
        return alimentoPorRefeicao.get();
    }

    public List<AlimentoPorRefeicao> showAlimentoPorRefeicaoByIdRefeicao(int idRefeicao) {
        Optional<Refeicao> refeicao = refRep.findById(idRefeicao);
        refeicao.orElseThrow(() -> new NaoEncontradoException("Refeição"));
        return aprRep.findByRefeicaoIdIs(refeicao.get());
    }

    public List<AlimentoPorRefeicao> showAlimentoPorRefeicaoByIdAlimento(int idAlimento) {
        Optional<Alimento> alimento = aliRep.findById(idAlimento);
        alimento.orElseThrow(() -> new NaoEncontradoException("Alimento"));
        return aprRep.findByAlimentoIdIs(alimento.get());
    }
}
