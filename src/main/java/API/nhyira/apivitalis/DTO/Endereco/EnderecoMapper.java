package API.nhyira.apivitalis.DTO.Endereco;

import API.nhyira.apivitalis.Entity.EnderecoModel;

public class EnderecoMapper {

    public static EnderecoModel toDto(EnderecoCreateEditDto dto) {
        if (dto == null) return null;

        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setLogradouro(dto.getLogradouro());
        enderecoModel.setNumero(dto.getNumero());
        enderecoModel.setComplemento(dto.getComplemento());
        enderecoModel.setBairro(dto.getBairro());
        enderecoModel.setCidade(dto.getCidade());
        enderecoModel.setEstado(dto.getEstado());
        enderecoModel.setCep(dto.getCep());

        return enderecoModel;
    }

    public static EnderecoExibitionDto toEntity(EnderecoModel entity) {
        if (entity == null) return null;

        EnderecoExibitionDto enderecoViewDTO = new EnderecoExibitionDto();
        enderecoViewDTO.setLogradouro(entity.getLogradouro());
        enderecoViewDTO.setBairro(entity.getBairro());
        enderecoViewDTO.setCidade(entity.getCidade());
        enderecoViewDTO.setEstado(entity.getEstado());
        enderecoViewDTO.setCep(entity.getCep());
        enderecoViewDTO.setNumero(entity.getNumero());
        enderecoViewDTO.setComplemento(entity.getComplemento());

        return enderecoViewDTO;

    }
}

