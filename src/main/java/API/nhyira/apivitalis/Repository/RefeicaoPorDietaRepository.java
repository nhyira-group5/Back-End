package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.RefeicaoPorDieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefeicaoPorDietaRepository extends JpaRepository<RefeicaoPorDieta, Integer> {

}
