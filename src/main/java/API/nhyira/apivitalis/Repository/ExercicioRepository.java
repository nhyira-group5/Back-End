package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Integer> {
}
