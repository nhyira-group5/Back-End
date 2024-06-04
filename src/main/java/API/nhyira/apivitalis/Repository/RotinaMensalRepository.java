package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.RotinaMensal;
import API.nhyira.apivitalis.Entity.RotinaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RotinaMensalRepository extends JpaRepository<RotinaMensal, Integer> {

    @Query("SELECT r FROM RotinaMensal r WHERE r.rotinaUsuarioId = :usuario AND r.mes = :mes")
    RotinaMensal buscarMes(RotinaUsuario usuario, int mes);
}
