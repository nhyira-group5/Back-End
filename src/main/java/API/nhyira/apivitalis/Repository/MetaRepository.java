package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Integer> {
}
