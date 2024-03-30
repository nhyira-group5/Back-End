package API.nhyira.CrudEntity.DATABASE;

import API.nhyira.CrudEntity.Model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    Optional<UsuarioModel> findByUsernameIgnoreCase(String username);
    Optional<UsuarioModel> findByEmailIgnoreCase(String email);
    Optional<UsuarioModel> findByCpf(String cpf);
}
