package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.AlimentoPorRefeicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentoPorRefeicaoRepository extends JpaRepository<AlimentoPorRefeicao, Integer> {

}
