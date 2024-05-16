package API.nhyira.apivitalis.DTO.EspecialidadePorMeta;


import API.nhyira.apivitalis.Entity.Especialidade;
import API.nhyira.apivitalis.Entity.Meta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EspecialidadePorMetaExibitionDto {

    private Integer idEspecialidadeMeta;

    private Especialidade especialidadeId;

    private Meta metaId;

}
