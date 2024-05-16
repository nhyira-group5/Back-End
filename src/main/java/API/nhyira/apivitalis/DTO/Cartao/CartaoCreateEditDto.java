package API.nhyira.apivitalis.DTO.Cartao;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartaoCreateEditDto {

    private String numero;
    private String nomeTitular;
    private String validade;

    private Integer cvv;
    private String bandeira;
    private Integer usuarioId;
}