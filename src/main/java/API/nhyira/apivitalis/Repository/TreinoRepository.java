package API.nhyira.apivitalis.Repository;


import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Integer> {
    List<Treino> findByRotinaDiariaIdIs(RotinaDiaria rotinaDiaria);
}
