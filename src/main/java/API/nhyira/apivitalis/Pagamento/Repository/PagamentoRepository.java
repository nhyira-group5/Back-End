package API.nhyira.apivitalis.Pagamento.Repository;

import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Pagamento.Entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    List<Pagamento> findByUsuario(Usuario usuario);

    @Query("""
        SELECT p FROM Pagamento p
                WHERE p.usuario = :usuario
                AND DATEDIFF(CURRENT_DATE, p.dataPagamento) <= 30
                ORDER BY p.dataPagamento DESC
    """)
    Optional<Pagamento> findUltimoPagamentoValido(Usuario usuario);
}
