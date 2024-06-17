package API.nhyira.apivitalis.Repository;


import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Integer> {
    List<Treino> findByRotinaDiariaIdIs(RotinaDiaria rotinaDiaria);

    @Query(value = "SELECT count(t.concluido) FROM treino t JOIN exercicio e ON t.exercicio_id = e.id_exercicio JOIN rotina_diaria rd ON t.rotina_diaria_id = rd.id_rotina_diaria JOIN rotina_semanal rs ON rd.rotina_semanal_id = rs.id_rotina_semanal JOIN rotina_mensal rm ON rs.rotina_mensal_id = rm.id_rotina_mensal WHERE t.concluido = 1 AND rm.id_rotina_mensal = :idMensal group by e.nome ORDER BY t.concluido", nativeQuery = true)
    List<Integer> findCompletedTrainingsCount(int idMensal);


    @Query(value = "SELECT e.nome FROM treino t JOIN exercicio e ON t.exercicio_id = e.id_exercicio JOIN rotina_diaria rd ON t.rotina_diaria_id = rd.id_rotina_diaria JOIN rotina_semanal rs ON rd.rotina_semanal_id = rs.id_rotina_semanal JOIN rotina_mensal rm ON rs.rotina_mensal_id = rm.id_rotina_mensal WHERE t.concluido = 1 AND rm.id_rotina_mensal = :idMensal group by e.nome ORDER BY t.concluido", nativeQuery = true)
    List<String> findCompletedTrainingsString(int idMensal);

}
