package API.nhyira.Crud.Repository;

import API.nhyira.Crud.Model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    Optional<UsuarioModel> findByUsernameIgnoreCase(String username);
    Optional<UsuarioModel> findByEmailIgnoreCase(String email);
    Optional<UsuarioModel> findByCpf(String cpf);
}
