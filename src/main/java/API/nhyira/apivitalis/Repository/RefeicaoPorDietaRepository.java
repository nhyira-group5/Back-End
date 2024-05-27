package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Alimento;
import API.nhyira.apivitalis.Entity.Dieta;
import API.nhyira.apivitalis.Entity.Refeicao;
import API.nhyira.apivitalis.Entity.RefeicaoPorDieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefeicaoPorDietaRepository extends JpaRepository<RefeicaoPorDieta, Integer> {
    List<RefeicaoPorDieta> findByDietaIdIs(Dieta dieta);
}
