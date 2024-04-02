package API.nhyira.Crud.Repository;

import API.nhyira.Crud.Model.PersonalModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalRepository extends JpaRepository<PersonalModel, Integer> {
    Optional<PersonalModel> findByUsernameIgnoreCase(String username);
    Optional<PersonalModel> findByEmailIgnoreCase(String email);
    Optional<PersonalModel> findByCpf(String cpf);
}
