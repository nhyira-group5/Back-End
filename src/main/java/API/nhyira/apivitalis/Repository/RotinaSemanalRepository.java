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
            SELECT rs FROM RotinaSemanal rs
            JOIN rs.rotinaMensalId rm
            JOIN rm.rotinaUsuarioId ru
            WHERE ru.usuarioId = :usuario
            AND rm.mes = :mes
            AND rm.ano = :ano
            AND rs.numSemana = :numSemana
            """)
    RotinaSemanal searchCurrentWeekRoutineByUserId(
            Usuario usuario,
            int mes,
            int ano,
            int numSemana
    );


    @Query("""
               SELECT COUNT(rd.concluido) FROM RotinaDiaria rd
                    JOIN rd.rotinaSemanalId rs
                        WHERE rs = :rotinaSemanal AND rd.concluido = 1
                        GROUP BY rd.concluido
            """)
    Optional<Integer> qtdDiasRealizadosPorSemana(RotinaSemanal rotinaSemanal);
}
