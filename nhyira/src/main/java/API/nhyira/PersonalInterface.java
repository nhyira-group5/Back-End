package API.nhyira;

import java.util.List;

public interface PersonalInterface {
    boolean validate(PersonalModel personal);
    PersonalModel criarPersonal(PersonalModel personal) throws Exception;
    PersonalModel atualizarPersonal(Long id, PersonalModel personalDetails) throws Exception;
    void deletePersonal(Long id);
    List<PersonalModel> personalsPorEspecialidade();
    PersonalModel obterPersonalPorId(Long id);
}
