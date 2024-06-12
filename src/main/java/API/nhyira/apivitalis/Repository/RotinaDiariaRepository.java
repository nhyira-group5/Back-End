package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.RotinaDiaria;

import API.nhyira.apivitalis.Entity.RotinaSemanal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RotinaDiariaRepository extends JpaRepository<RotinaDiaria, Integer> {

    List<RotinaDiaria> findByRotinaSemanalIdIs(RotinaSemanal rotinaSemanal);

}
