package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Lembrete;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LembreteRepository extends JpaRepository<Lembrete, Integer> {
    List<Lembrete> findByUsuarioId(Usuario usuario);
}
