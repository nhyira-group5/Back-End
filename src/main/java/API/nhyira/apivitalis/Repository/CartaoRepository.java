package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {
    Optional<Cartao> findByBandeiraAndCvv(String bandeira, Integer cvv);
    Optional<Cartao> findByNumero(String numero);
    Optional<Cartao> findByNomeTitular(String nomeTitular);
}