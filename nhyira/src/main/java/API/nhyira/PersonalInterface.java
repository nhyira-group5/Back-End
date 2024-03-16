package API.nhyira;

import java.util.List;

public interface PersonalInterface {
    PersonalModel createPersonal(PersonalModel personal) throws Exception;

    PersonalModel updatePersonal(Long id, PersonalModel personalDetails) throws Exception;

    void deletePersonal(Long id);

    List<PersonalModel> getAllPersonals();

    PersonalModel getPersonalById(Long id);
}
