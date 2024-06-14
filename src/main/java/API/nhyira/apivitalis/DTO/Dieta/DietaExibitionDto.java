package API.nhyira.apivitalis.DTO.Dieta;

import API.nhyira.apivitalis.Entity.Meta;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DietaExibitionDto {
    private Integer id;
    private String nome;
    private String descricao;
    private Meta metaId;
}
