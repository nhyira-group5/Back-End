package API.nhyira.apivitalis.Pagamento.Repository;

import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Pagamento.Entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    List<Pagamento> findByUsuario(Usuario usuario);
}
