package API.nhyira.apivitalis.DTO.Personal;

import API.nhyira.apivitalis.Auth.Personal.DTO.PersonalTokenDto;
import API.nhyira.apivitalis.Entity.Personal;

public class PersonalMapper {

    public static Personal toDto(PersonalCreateEditDto dto){
        if (dto != null) {
            Personal personal = new Personal();
            personal.setUsername(dto.getUsername());
            personal.setCpf(dto.getCpf());
            personal.setNome(dto.getNome());
            personal.setGenero(dto.getGenero());
            personal.setDtNasc(dto.getDtNasc());
            personal.setEmail(dto.getEmail());
            personal.setEmail2(dto.getEmail2());
            personal.setSenha(dto.getSenha());
            personal.setMidia(dto.getMidia());

            return personal;
        }

        return null;
    }

    public static PersonalExibitionDto toExibition(Personal entity){
        if (entity != null){
            PersonalExibitionDto user = new PersonalExibitionDto();
            user.setUsername(entity.getUsername());
            user.setCpf(entity.getCpf());
            user.setNome(entity.getNome());
            user.setDtNasc(entity.getDtNasc());
            user.setGenero(entity.getGenero());
            user.setEmail(entity.getEmail());
            user.setEmail2(entity.getEmail2());
            user.setMidia(entity.getMidia());
            return user;
        }

        return null;
    }

    public static Personal toEditDto(Personal user , PersonalCreateEditDto dto ){
        if (dto != null){
            user.setUsername(dto.getUsername());
            user.setCpf(dto.getCpf());
            user.setNome(dto.getNome());
            user.setDtNasc(dto.getDtNasc());
            user.setGenero(dto.getGenero());
            user.setEmail(dto.getEmail());
            user.setEmail2(dto.getEmail2());
            user.setSenha(dto.getSenha());
            user.setMidia(dto.getMidia());
            return user;
        }
        return null;
    }

    public static PersonalTokenDto of(Personal user, String token){
        PersonalTokenDto personal = new PersonalTokenDto();
        personal.setEmail(user.getEmail());
        personal.setUsername(user.getUsername());
        personal.setToken(token);

        return personal;
    }
}
