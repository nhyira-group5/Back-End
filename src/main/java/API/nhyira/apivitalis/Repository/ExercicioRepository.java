package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Integer> {

    @Query("SELECT e FROM Exercicio e JOIN Treino t WHERE t.idTreino = :treino")
    Exercicio buscarPorTreino(int treino);
}
