package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Dieta;
import API.nhyira.apivitalis.Entity.Meta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DietaRepository extends JpaRepository<Dieta, Integer> {
    List<Dieta> findByMetaIdIs(Meta meta);
}
