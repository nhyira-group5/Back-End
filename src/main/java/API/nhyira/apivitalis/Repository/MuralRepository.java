package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Mural;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MuralRepository extends JpaRepository<Mural, Integer> {

    Optional<Mural> findByUsuarioIdIs(Usuario usuario);

    @Query("SELECT m FROM Mural m WHERE m.idMural = :id AND m.dtPostagem = :data")
    Optional<Mural> buscarPorData(LocalDate data, Mural id);

}
