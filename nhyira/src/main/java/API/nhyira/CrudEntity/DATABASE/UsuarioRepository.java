package API.nhyira.CrudEntity.DATABASE;

import API.nhyira.CrudEntity.Model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {}
