package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Mural;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MuralRepository extends JpaRepository<Mural, Integer> {
    List<Mural> findByUsuarioIdIs(Usuario usuario);

    List<Mural> findByDtPostagem(LocalDate dtPostagem);

    List<Mural> findByDtPostagemAndUsuarioIdIs(LocalDate data, Usuario usuario);
}
