package API.nhyira.apivitalis.DTO.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EnderecoCreateEditDto {
    @NotBlank(message = "A rua é obrigatória")
    @Size(max = 100)
    private String logradouro;

    @NotBlank(message = "O bairro é obrigatório")
    @Size(max = 100)
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória")
    @Size(max = 100)
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    @Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres")
    @Pattern(regexp = "^[A-Za-z]{2}$", message = "O estado deve conter apenas letras")
    private String estado;

    @NotBlank(message = "O CEP é obrigatório")
//    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "O CEP deve estar no formato 12345-678")
    private String cep;

    @NotBlank(message = "O número é obrigatório")
    private String numero;

    private String complemento;

    private Integer personalId;

    
}