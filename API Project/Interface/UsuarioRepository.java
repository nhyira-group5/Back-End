import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
  
    ResponseEntity<Usuario> findByCpf(String cpf);

  
    ResponseEntity<Usuario> findByEmail(String email);

  
    ResponseEntity<String> criarUsuario(Usuario usuario);

    
    List<Usuario> filtrarUsuarios(String nome, String cpf, String email);
}
