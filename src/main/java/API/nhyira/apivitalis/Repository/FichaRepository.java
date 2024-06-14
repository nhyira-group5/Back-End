package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Ficha;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Integer> {
    Optional<Ficha> findByUsuarioIdIs(Usuario usuario);
}