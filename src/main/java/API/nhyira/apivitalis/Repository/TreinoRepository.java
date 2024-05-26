package API.nhyira.apivitalis.Repository;


import API.nhyira.apivitalis.Entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Integer> {
}
