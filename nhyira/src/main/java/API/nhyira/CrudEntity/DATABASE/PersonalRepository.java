package API.nhyira.CrudEntity.DATABASE;

import API.nhyira.CrudEntity.Model.PersonalModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepository extends JpaRepository<PersonalModel, Integer> {
}
