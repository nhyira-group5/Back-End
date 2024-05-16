package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Integer> {
}
