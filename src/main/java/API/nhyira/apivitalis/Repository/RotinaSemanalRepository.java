package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.RefeicaoDiaria;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RotinaSemanalRepository extends JpaRepository<RotinaSemanal, Integer> {

    @Query("SELECT rs FROM RotinaSemanal rs " +
            "               JOIN rs.rotinaMensalId rm " +
            "              JOIN rm.rotinaUsuarioId ru" +
            "              JOIN ru.usuarioId u " +
            "              WHERE u.idUsuario = :usuario")
    List<RotinaSemanal> buscarPorIdUsuario(Integer usuario);

    @Query("""
               SELECT COUNT(rd.concluido) FROM RotinaDiaria rd
                    JOIN rd.rotinaSemanalId rs
                        WHERE rs = :rotinaSemanal AND rd.concluido = 1
                        GROUP BY rd.concluido
            """)
    Optional<Integer> qtdDiasRealizadosPorSemana(RotinaSemanal rotinaSemanal);
}
