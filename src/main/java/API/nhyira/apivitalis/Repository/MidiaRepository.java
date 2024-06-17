package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Midia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MidiaRepository extends JpaRepository<Midia, Integer> {


}
