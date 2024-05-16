package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Integer> {
}
