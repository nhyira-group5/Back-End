package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RotinaSemanalRepository extends JpaRepository<RotinaSemanal, Integer> {


    @Query("SELECT rs FROM RotinaSemanal rs " +
            "               JOIN rs.rotinaMensalId rm " +
            "              JOIN rm.rotinaUsuarioId ru" +
            "              JOIN ru.usuarioId u " +
            "              WHERE u.idUsuario = :usuario")
    List<RotinaSemanal> buscarPorIdUsuario(Integer usuario);
}
