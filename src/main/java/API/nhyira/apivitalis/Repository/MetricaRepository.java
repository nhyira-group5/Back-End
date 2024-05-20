package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Metrica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricaRepository extends JpaRepository<Metrica, Integer> {
}
