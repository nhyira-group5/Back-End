package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Entity.*;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.RefeicaoDiariaRepository;
import API.nhyira.apivitalis.Repository.RefeicaoPorDietaRepository;
import API.nhyira.apivitalis.Repository.RefeicaoRepository;
import API.nhyira.apivitalis.Repository.RotinaDiariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefeicaoService {
    private final RefeicaoRepository refRep;
//    private final DietaRepository diRep;
    private final RefeicaoPorDietaRepository rpdRep;
    private final RefeicaoDiariaRepository refdRep;
    private final RotinaDiariaRepository rdRep;
//    private final RotinaSemanalRepository rsRep;

    public List<Refeicao> getAllRefeicoes() {
        return refRep.findAll();
    }

    public Refeicao getRefeicaoById(int id) {
        Refeicao refeicao = refRep.findById(id).orElseThrow(() -> new NaoEncontradoException("Refeição"));
        return refeicao;
    }

    public List<Refeicao> showRefeicaoByDieta(Dieta dieta) {
        List<RefeicaoPorDieta> rpdList = rpdRep.findByDietaIdIs(dieta);
        if (rpdList.isEmpty()) throw new NaoEncontradoException("Refeição por Dieta com a dieta " + dieta.getNome());
        List<Refeicao> refeicoes = rpdList.stream().map(rpd -> refRep.findById(rpd.getRefeicaoId().getIdRefeicao()).get()).toList();
        return refeicoes;
    }

    public Refeicao showByRefeicaoDiaria(int idRefeicaoDiaria) {
        RefeicaoDiaria refd = refdRep.findById(idRefeicaoDiaria).orElseThrow(() -> new NaoEncontradoException("Refeição Diária"));
        Refeicao ref = refRep.searchMealsByDailyMeal(refd.getIdRefeicaoDiaria());
        return ref;
    }

    public List<Refeicao> showByRotinaDiaria(int idRotinaDiaria) {
        RotinaDiaria rd = rdRep.findById(idRotinaDiaria).orElseThrow(() -> new NaoEncontradoException("Rotina Diária"));
        List<Refeicao> ref = refRep.searchMealsByDailyRoutine(rd.getIdRotinaDiaria());
        return ref;
    }

    public List<Refeicao> showRefeicoesByNome(String nome) {
        return refRep.buscarFiltroPorNome(nome);
    }

    public List<Refeicao> showMealsByMetaAndNome(int idMeta, String nome) { return refRep.searchByMetaAndName(idMeta, nome); }
}
