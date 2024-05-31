package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Contrato;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {

    Optional<Contrato> findByUsuarioIdIs(Usuario usuario);

    Optional<Contrato> findByPersonalIdIs(Usuario usuario);

}
