package API.nhyira.apivitalis.DTO.RotinaSemanal;

import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalExibitionDto;
import API.nhyira.apivitalis.Entity.RotinaMensal;
import lombok.Data;
import org.hibernate.type.descriptor.jdbc.TinyIntJdbcType;

@Data
public class RotinaSemanalExibitionDto {
    private int id;

    private RotinaMensalDto rotinaMensalId;

    private TinyIntJdbcType concluido;



    @Data
    public static class RotinaMensalDto{
        private int id;

        private String mes;

        private int ano;
    }

}
