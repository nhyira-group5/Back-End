package API.nhyira.apivitalis.Pagamento.Service;

import org.springframework.stereotype.Service;

import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Pagamento.Repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    private final PagamentoRepository pagRep;

    public boolean verifyUserPagamento(Usuario usuario) {
        return pagRep.findUltimoPagamentoValido(usuario).isPresent();
    }
}