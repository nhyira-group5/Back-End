package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Alimento;
import API.nhyira.apivitalis.Entity.AlimentoPorRefeicao;
import API.nhyira.apivitalis.Entity.Refeicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlimentoPorRefeicaoRepository extends JpaRepository<AlimentoPorRefeicao, Integer> {
    List<AlimentoPorRefeicao> findByRefeicaoIdIs(Refeicao refeicao);
    List<AlimentoPorRefeicao> findByAlimentoIdIs(Alimento alimento);
}
