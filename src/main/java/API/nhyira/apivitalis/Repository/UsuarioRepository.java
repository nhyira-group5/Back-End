package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Midia;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("""
        SELECT u FROM Usuario AS u WHERE u.idUsuario = (SELECT MAX(u2.idUsuario) FROM Usuario u2)
    """)
    Optional<Usuario> buscaMaiorId();

    Optional<Usuario> findByNickname(String username);

    Optional<Usuario> findByEmailIgnoreCase(String email);

    Optional<Usuario> findByCpf(String cpf);

    @Query("SELECT u FROM Usuario u WHERE u.tipo =1")
    List<Usuario> buscarPersonal();

    @Query("SELECT u FROM Usuario u WHERE u.tipo = 0")
    List<Usuario> buscarUsuarios();


}
