package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Contrato;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {

    Optional<Contrato> findByUsuarioIdIs(Usuario usuario);

    Optional<Contrato> findByPersonalIdIs(Usuario usuario);


    @Query("SELECT c FROM Contrato c WHERE c.personalId = :personal AND c.afiliacao = 0")
    List<Contrato> buscarContratoPorPersonal(Usuario personal);
}
