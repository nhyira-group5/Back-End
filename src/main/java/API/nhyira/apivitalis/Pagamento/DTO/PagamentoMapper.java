package API.nhyira.apivitalis.Pagamento.DTO;

import API.nhyira.apivitalis.Entity.Assinatura;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Pagamento.Entity.Pagamento;
import API.nhyira.apivitalis.Repository.AssinaturaRepository;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PagamentoMapper {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public PagamentoMapper(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    private AssinaturaRepository assinaturaRepository;


    public Pagamento mapCreateDtoToEntity(PagamentoCreateEditDto dto) {
        Pagamento pagamento = new Pagamento();
        pagamento.setIdPagamento(dto.getIdPagamento());
        pagamento.setTipo(dto.getTipo());

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        pagamento.setUsuario(usuario);

        Optional<Assinatura> assinaturaOpt = assinaturaRepository.findById(dto.getAssinaturaId());
        Assinatura assinatura = assinaturaOpt.orElseThrow(() -> new RuntimeException("Assinatura não encontrada"));
        pagamento.setAssinatura(assinatura);

        return pagamento;
    }


    public PagamentoExibitionDto mapEntityToExibitionDto(Pagamento pagamento) {
        PagamentoExibitionDto dto = new PagamentoExibitionDto();
        dto.setIdPagamento(pagamento.getIdPagamento());
        dto.setUsuarioId(pagamento.getUsuario().getIdUsuario());
        dto.setTipo(pagamento.getTipo());


        PagamentoExibitionDto.AssinaturaDto assinaturaDto = new PagamentoExibitionDto.AssinaturaDto();
        assinaturaDto.setIdAssinatura(pagamento.getAssinatura().getIdAssinatura());
        assinaturaDto.setNome(pagamento.getAssinatura().getNome());
        assinaturaDto.setValor(pagamento.getAssinatura().getValor());
        dto.setAssinatura(assinaturaDto);

        return dto;
    }
}
