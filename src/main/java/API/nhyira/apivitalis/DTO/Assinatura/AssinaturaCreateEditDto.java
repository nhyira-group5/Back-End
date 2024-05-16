package API.nhyira.apivitalis.DTO.Assinatura;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssinaturaCreateEditDto {
    private String nome;
    private Double valor;
}