package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.RotinaDiaria;

import API.nhyira.apivitalis.Entity.RotinaSemanal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RotinaDiarioRepository extends JpaRepository<RotinaDiaria, Integer> {

    List<RotinaDiaria> findByRotinaSemanalIdIs(RotinaSemanal rotinaSemanal);

}
