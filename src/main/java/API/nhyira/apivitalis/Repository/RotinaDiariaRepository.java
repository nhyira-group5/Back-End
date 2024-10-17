package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.RefeicaoDiaria;
import API.nhyira.apivitalis.Entity.RotinaDiaria;

import API.nhyira.apivitalis.Entity.RotinaSemanal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RotinaDiariaRepository extends JpaRepository<RotinaDiaria, Integer> {
    @Query("SELECT rd \n" +
            "FROM RotinaDiaria rd\n" +
            "LEFT JOIN rd.rotinaSemanalId rs\n" +
            "LEFT JOIN rs.rotinaMensalId rm \n" +
            "LEFT JOIN rm.rotinaUsuarioId ru \n" +
            "LEFT JOIN ru.usuarioId u \n" +
            "WHERE u.idUsuario = :idUsuario order by rd.idRotinaDiaria LIMIT 1 \n")
    RotinaDiaria findFirstByRotinaDiaria(int idUsuario);

    List<RotinaDiaria> findByRotinaSemanalIdIs(RotinaSemanal rotinaSemanal);

    @Query("""
            SELECT rd FROM RotinaDiaria rd
                        JOIN rd.rotinaSemanalId rs
                        WHERE rs.idRotinaSemanal = :idRotinaSemanal
                        AND rd.dia = :dia
            """)
    RotinaDiaria showCurrentDailyRoutine(
            Integer idRotinaSemanal,
            int dia
    );
}
