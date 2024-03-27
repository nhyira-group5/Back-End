package API.nhyira.DBA;

import API.nhyira.Model.PersonalModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepository extends JpaRepository<PersonalModel, Integer> {
}
