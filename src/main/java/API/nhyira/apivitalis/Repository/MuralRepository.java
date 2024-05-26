package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Mural;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MuralRepository extends JpaRepository<Mural, Integer> {

    Optional<Mural> findByUsuarioIdIs(Usuario usuario);
}
