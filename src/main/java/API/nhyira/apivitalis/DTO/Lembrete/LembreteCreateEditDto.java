package API.nhyira.apivitalis.DTO.Lembrete;


import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class LembreteCreateEditDto {

    @NotBlank
    private String conteudo;

    @NotNull
    private Date dataLembrete;

    @NotNull
    private Integer usuarioId;

}
