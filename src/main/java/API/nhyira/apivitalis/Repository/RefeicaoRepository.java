package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Refeicao;
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
}
