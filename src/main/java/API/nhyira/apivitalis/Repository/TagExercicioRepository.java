package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.TagExercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagExercicioRepository extends JpaRepository<TagExercicio, Integer> {
}
