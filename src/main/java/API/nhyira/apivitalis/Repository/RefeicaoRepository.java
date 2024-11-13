package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Refeicao;
import API.nhyira.apivitalis.Entity.RefeicaoDiaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefeicaoRepository extends JpaRepository<Refeicao, Integer> {
    @Query("""
                SELECT ref FROM Refeicao AS ref WHERE ref.nome ilike %:nome%
            """)
    List<Refeicao> buscarFiltroPorNome(String nome);

    @Query("""
            SELECT r FROM Refeicao r
            JOIN RefeicaoDiaria rd ON r.idRefeicao = rd.refeicaoId.idRefeicao
                WHERE rd.idRefeicaoDiaria = :idRefd
            """)
    Refeicao searchMealsByDailyMeal(Integer idRefd);

    @Query("""
            SELECT r FROM Refeicao r
                    JOIN RefeicaoDiaria rd ON r.idRefeicao = rd.refeicaoId.idRefeicao
                    WHERE rd.rotinaDiariaId.idRotinaDiaria = :idRd
            """)
    List<Refeicao> searchMealsByDailyRoutine(Integer idRd);

    @Query("""
                SELECT ref
                    FROM Refeicao ref
                    JOIN RefeicaoPorDieta rd ON rd.refeicaoId.idRefeicao = ref.idRefeicao
                    JOIN Dieta dieta ON dieta.idDieta = rd.dietaId.idDieta
                    WHERE dieta.metaId.idMeta = :idMeta
                    AND ref.nome ilike %:name%
            """)
    List<Refeicao> searchByMetaAndName(Integer idMeta, String name);
}
