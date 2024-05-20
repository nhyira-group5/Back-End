package API.nhyira.apivitalis.DTO.RotinaSemanal;


import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalExibitionDto;
import API.nhyira.apivitalis.Entity.RotinaMensal;
import API.nhyira.apivitalis.Repository.RotinaMensalRepository;
import lombok.Data;

import java.util.List;

@Data
public class RotinaSemanalExibitionDto {

    private Integer id;
    private RotinaMensalExibitionDto rotinaMensalId;
    private Integer concluido;


    @Data
    public static class RotinaMensalExibitionDto{

        private int id;

        private String mes;

        private int ano;
    }

}
