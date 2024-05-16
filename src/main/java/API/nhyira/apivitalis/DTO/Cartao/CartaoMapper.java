package API.nhyira.apivitalis.DTO.Cartao;

import API.nhyira.apivitalis.Entity.Cartao;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartaoMapper {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CartaoMapper(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Cartao toEntity(CartaoCreateEditDto dto) {
        Cartao cartao = new Cartao();
        cartao.setNumero(dto.getNumero());
        cartao.setNomeTitular(dto.getNomeTitular());
        cartao.setValidade(dto.getValidade());
        cartao.setCvv(dto.getCvv());
        cartao.setBandeira(dto.getBandeira());

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario not found with id " + dto.getUsuarioId()));
            cartao.setUsuario(usuario);
        } else {
            throw new RuntimeException("UsuarioId is null");
        }

        return cartao;
    }

    public CartaoCreateEditDto toCreateEditDto(Cartao entity) {
        CartaoCreateEditDto dto = new CartaoCreateEditDto();
        dto.setNumero(entity.getNumero());
        dto.setNomeTitular(entity.getNomeTitular());
        dto.setValidade(entity.getValidade());
        dto.setCvv(entity.getCvv());
        dto.setBandeira(entity.getBandeira());
        if (entity.getUsuario() != null) {
            dto.setUsuarioId(entity.getUsuario().getIdUsuario());
        }
        return dto;
    }
}