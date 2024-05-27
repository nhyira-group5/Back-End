package API.nhyira.apivitalis.DTO.Metrica;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MetricaCreateDto {
    @Size(min = 1, max = 100)
    private String metrica;
}
