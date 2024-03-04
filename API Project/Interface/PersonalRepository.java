import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
    
   
    List<Personal> findByEspecialidade(String especialidade);

 
    ResponseEntity<Personal> findByCpf(String cpf);

 
    ResponseEntity<Personal> findByEmail(String email);

    
    ResponseEntity<String> criarPersonal(Personal personal);

    
    List<Personal> filtrarPersonal(String nome, String especialidade, String cpf, String email);
}
