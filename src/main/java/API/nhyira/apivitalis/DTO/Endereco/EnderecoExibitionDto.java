package API.nhyira.apivitalis.DTO.Endereco;

import API.nhyira.apivitalis.DTO.Usuario.UsuarioDto;
import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoExibitionDto {


    private Integer id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
