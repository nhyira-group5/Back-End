package API.nhyira.apivitalis.Repository;


import API.nhyira.apivitalis.Entity.RefeicaoDiaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefeicaoDiariaRepository extends JpaRepository<RefeicaoDiaria,Integer > {
}
