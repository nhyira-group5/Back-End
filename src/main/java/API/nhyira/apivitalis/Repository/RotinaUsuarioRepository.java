package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.RotinaUsuario;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RotinaUsuarioRepository extends JpaRepository<RotinaUsuario, Integer> {

    Optional<RotinaUsuario> findByUsuarioIdIs(Usuario usuario);
}
