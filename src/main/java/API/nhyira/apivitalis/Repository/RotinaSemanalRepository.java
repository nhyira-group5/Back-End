package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.RotinaMensal;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RotinaSemanalRepository extends JpaRepository<RotinaSemanal, Integer> {


    List<RotinaSemanal> findByRotinaMensalIdIs(RotinaMensal rotinaMensal);
}
