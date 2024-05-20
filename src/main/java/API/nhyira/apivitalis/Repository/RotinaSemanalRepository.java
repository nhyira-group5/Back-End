package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.RotinaSemanal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RotinaSemanalRepository extends JpaRepository<RotinaSemanal, Integer> {
}
