package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Especialidade;
import API.nhyira.apivitalis.Entity.EspecialidadePorMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspecialidadePorMetaRepository extends JpaRepository<EspecialidadePorMeta, Integer> {
    Optional<EspecialidadePorMeta> findByEspecialidadeId(Especialidade especialidade);
}
