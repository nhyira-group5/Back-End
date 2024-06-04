package API.nhyira.apivitalis.DTO.Contrato;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratoEditDto {

    @FutureOrPresent
    private LocalDate fimContrato;

    private Integer afiliado;
}
