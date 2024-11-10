package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Ficha;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNickname(String username);

    Optional<Usuario> findByEmailIgnoreCase(String email);

    Optional<Usuario> findByCpf(String cpf);

    @Query("SELECT u FROM Usuario u WHERE u.tipo = 1")
    List<Usuario> buscarPersonal();

    @Query("SELECT u FROM Usuario u WHERE u.tipo = 0")
    List<Usuario> buscarUsuarios();

    List<Usuario> findByPersonalIdIs(Usuario usuario);

    @Query("""
            SELECT u
                    FROM Usuario u
                    JOIN EspecialidadePorPersonal epp ON u.idUsuario = epp.personalId.idUsuario
                    JOIN EspecialidadePorMeta epm ON epp.especialidadeId.idEspecialidade = epm.especialidadeId.idEspecialidade
                    WHERE u.tipo = 1 AND epm.metaId.idMeta = :idMeta
            """)
    List<Usuario> searchTrainersByMeta(int idMeta);
}
