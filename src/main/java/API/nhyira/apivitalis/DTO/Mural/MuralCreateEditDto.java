package API.nhyira.apivitalis.DTO.Mural;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MuralCreateEditDto {
    private Integer usuarioId;

    @NotNull
    @Positive
    private Integer midiaId;
    private LocalDate dtPostagem;
}
