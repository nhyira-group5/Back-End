package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Midia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MidiaRepository extends JpaRepository<Midia, Integer> {
    @Query("""
        SELECT m FROM Midia AS m WHERE m.idMidia = (SELECT MAX(m2.idMidia) FROM Midia m2)
    """)
    Optional<Midia> buscaMaiorId();
}
