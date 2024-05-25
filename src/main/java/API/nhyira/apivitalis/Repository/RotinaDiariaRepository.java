package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.RotinaDiario;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RotinaDiarioRepository extends JpaRepository<RotinaDiario, Integer> {

    Optional<RotinaDiario> findByRotinaSemanalIdIs(RotinaSemanal rotinaSemanal);

}
