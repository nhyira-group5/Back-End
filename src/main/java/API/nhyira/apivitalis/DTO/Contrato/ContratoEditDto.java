package API.nhyira.apivitalis.DTO.Contrato;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContratoEditDto {

    @FutureOrPresent
    private LocalDate fimContrato;

    private Integer afiliado;
}
