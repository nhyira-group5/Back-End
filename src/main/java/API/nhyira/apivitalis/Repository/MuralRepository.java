package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Mural;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MuralRepository extends JpaRepository<Mural, Integer> {

    Optional<Mural> findByUsuarioIdIs(Usuario usuario);

//    Optional<Mural> findByIdMuralAndDtPostagem(Integer idMural ,LocalDate data);


    List<Mural> findByDtPostagemStartingWith(String date);


//    @Query("SELECT m FROM Mural m WHERE m.idMural = :idMural AND m.dtPostagem = ':data%'")
//    Optional<Mural> buscarPorData( Integer idMural ,LocalDate data);

}
