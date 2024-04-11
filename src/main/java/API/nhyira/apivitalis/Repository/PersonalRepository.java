package API.nhyira.apivitalis.Repository;

import API.nhyira.apivitalis.Entity.Personal;
import API.nhyira.apivitalis.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalRepository extends JpaRepository<Personal, Integer> {


    Optional<Personal> findByUsernameIgnoreCase(String username);

    Optional<Personal> findByEmailIgnoreCase(String email);
    Optional<Personal> findByCpf(String cpf);
}
