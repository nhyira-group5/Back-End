package API.nhyira.apivitalis.DTO.Cartao;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartaoExibitionDto {
    private Integer idCartao;
    private String numero;
    private String nomeTitular;
    private String validade;
    private String bandeira;
}