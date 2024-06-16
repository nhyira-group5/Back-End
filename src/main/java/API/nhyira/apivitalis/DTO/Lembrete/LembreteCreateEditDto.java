package API.nhyira.apivitalis.DTO.Lembrete;


import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LembreteCreateEditDto {

    @NotBlank
    private String conteudo;

    @NotNull
    private LocalDate dataLembrete;

    @NotNull
    private Integer usuarioId;

}
