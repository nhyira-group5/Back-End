package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Refeicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefeicaoRepository extends JpaRepository<Refeicao, Integer> {

}
