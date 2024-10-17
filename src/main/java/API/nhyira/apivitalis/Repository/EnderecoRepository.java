package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Endereco;
import API.nhyira.apivitalis.Entity.Ficha;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
//    Optional<Endereco> findByPersonalIdIs(Usuario usuario);
}
