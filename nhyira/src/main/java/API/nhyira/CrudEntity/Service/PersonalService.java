package API.nhyira.CrudEntity.Service;

import API.nhyira.CrudEntity.DATABASE.PersonalRepository;
import API.nhyira.CrudEntity.Model.PersonalModel;
import API.nhyira.CrudEntity.Model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Boolean adicionarPersonal(PersonalModel personal) {
        if (personal != null) {
            personal.setSenha(encoder.encode(personal.getSenha()));
            personalRepository.save(personal);
            return true;
        }
        return false;
    }

    public PersonalModel atualizarPersonal(int id, PersonalModel personalDetails) {
        Optional<PersonalModel> personalOptional = personalRepository.findById(id);

        if (personalOptional.isPresent()) {
            PersonalModel personal = personalOptional.get();

            try {
                personal.setNome(personalDetails.getNome());
                personal.setUsername(personalDetails.getUsername());
                personal.setEmail(personalDetails.getEmail());
                personal.setSenha(personalDetails.getSenha());
                personal.setCpf(personalDetails.getCpf());
                personal.setDtNasc(personalDetails.getDtNasc());
                personal.setGenero(personalDetails.getGenero());
                personal.setEmail2(personalDetails.getEmail2());

                adicionarPersonal(personal);
                return personal;
            } catch (Exception e) {
                throw new RuntimeException("Erro ao atualizar personal: " + e.getMessage());
            }
        } else {
            throw new NoSuchElementException("Personal não encontrado");
        }
    }

    public Boolean deletarPersonal(int id) {
        if (personalRepository.existsById(id)) {
            personalRepository.deleteById(id);
            return true;
        }
        throw new NoSuchElementException("Personal não encontrado");
    }

    public List<PersonalModel> getPersonais() {
        return personalRepository.findAll();
    }

    public Optional<PersonalModel> getPersonaisPorId(int id) {
        return personalRepository.findById(id);
    }

    public boolean isUsernameUnique(String username) {
        return personalRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    public boolean isEmailUnique(String email) {
        return personalRepository.findByEmailIgnoreCase(email).isEmpty();
    }

    public boolean isCpfUnique(String cpf) {
        return personalRepository.findByCpf(cpf) == null;
    }
}
