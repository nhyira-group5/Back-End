package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.EspecialidadePorPersonal;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspecialidadePorPersonalRepository extends JpaRepository<EspecialidadePorPersonal, Integer> {

    List<EspecialidadePorPersonal> findByPersonalIdIs(Usuario usuario);
}
