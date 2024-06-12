package API.nhyira.apivitalis.Repository;


import API.nhyira.apivitalis.Entity.RefeicaoDiaria;
import API.nhyira.apivitalis.Entity.RotinaDiaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefeicaoDiariaRepository extends JpaRepository<RefeicaoDiaria,Integer > {
    List<RefeicaoDiaria> findRefeicaoDiariaByRotinaDiariaIdIs(RotinaDiaria rotinaDiaria);
}
