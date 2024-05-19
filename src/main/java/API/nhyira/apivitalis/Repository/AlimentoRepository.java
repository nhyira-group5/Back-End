package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Integer> {

}
