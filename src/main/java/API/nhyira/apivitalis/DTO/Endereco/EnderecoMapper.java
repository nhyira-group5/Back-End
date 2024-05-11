package API.nhyira.apivitalis.DTO.Endereco;

import API.nhyira.apivitalis.Entity.Endereco;

import java.util.ArrayList;
import java.util.List;

public class EnderecoMapper {

    public static Endereco toEntity(EnderecoCreateEditDto dto) {
        if (dto == null) return null;

        Endereco enderecoModel = new Endereco();
        enderecoModel.setLogradouro(dto.getLogradouro());
        enderecoModel.setNumero(dto.getNumero());
        enderecoModel.setBairro(dto.getBairro());
        enderecoModel.setCidade(dto.getCidade());
        enderecoModel.setEstado(dto.getEstado());
        enderecoModel.setCep(dto.getCep());
        enderecoModel.setComplemento(dto.getComplemento());

        return enderecoModel;
    }

    public static EnderecoExibitionDto toDto(Endereco entity) {
        if (entity == null) return null;

        EnderecoExibitionDto enderecoExibitionDto = new EnderecoExibitionDto();
        enderecoExibitionDto.setId(entity.getIdEndereco());
        enderecoExibitionDto.setLogradouro(entity.getLogradouro());
        enderecoExibitionDto.setNumero(entity.getNumero());
        enderecoExibitionDto.setBairro(entity.getBairro());
        enderecoExibitionDto.setCidade(entity.getCidade());
        enderecoExibitionDto.setEstado(entity.getEstado());
        enderecoExibitionDto.setCep(entity.getCep());
        enderecoExibitionDto.setPersonalId(entity.getPersonalId());
        enderecoExibitionDto.setComplemento(entity.getComplemento());


        return enderecoExibitionDto;
    }

    public static Endereco toEditDto(Endereco endereco, EnderecoCreateEditDto dto) {
        if (dto != null) {
            endereco.setLogradouro(dto.getLogradouro());
            endereco.setNumero(dto.getNumero());
            endereco.setBairro(dto.getBairro());
            endereco.setCidade(dto.getCidade());
            endereco.setEstado(dto.getEstado());
            endereco.setCep(dto.getCep());
            endereco.setComplemento(dto.getComplemento());

            return endereco;
        }
        return null;
    }

    public static List<EnderecoExibitionDto> toExibitionList(List<Endereco> entities) {
        List<EnderecoExibitionDto> dtos = new ArrayList<>();
        for (Endereco entity : entities) {
            EnderecoExibitionDto dto = toDto(entity);
            if (dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }
}