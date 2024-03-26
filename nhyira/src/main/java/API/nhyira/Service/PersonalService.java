package API.nhyira.Service;

import API.nhyira.DBA.PersonalRepository;
import API.nhyira.Model.PersonalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Predicate;

@Service
public class PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    public Boolean adicionarPersonal(PersonalModel personal) {
        if (personal != null) {
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

    public void validarPersonal(PersonalModel personal) throws Exception {
        Map<String, Predicate<String>> validacoes = criarValidacoes();

        for (Map.Entry<String, Predicate<String>> entry : validacoes.entrySet()) {
            String atributo = entry.getKey();
            Predicate<String> validacao = entry.getValue();
            String valor = getValue(personal, atributo);

            if (!validacao.test(valor)) {
                throw new Exception("Erro de validação para: " + atributo);
            }
        }
    }

    private Map<String, Predicate<String>> criarValidacoes() {
        Map<String, Predicate<String>> validacoes = new HashMap<>();
        validacoes.put("nome", this::validarNome);
        validacoes.put("username", this::validarUsername);
        validacoes.put("email", this::validarEmail);
        validacoes.put("email2", this::validarEmail2);
        validacoes.put("senha", this::validarSenha);
        validacoes.put("cpf", this::validarCPF);
        validacoes.put("genero", this::validarGenero);
        validacoes.put("dtNasc", this::validarDtNasc);
        return validacoes;
    }

    private String getValue(PersonalModel personal, String atributo) {
        switch (atributo) {
            case "nome":
                return personal.getNome();
            case "username":
                return personal.getUsername();
            case "email":
                return personal.getEmail();
            case "email2":
                return personal.getEmail2();
            case "senha":
                return personal.getSenha();
            case "cpf":
                return personal.getCpf();
            case "dtNasc":
                return personal.getDtNasc() != null ? personal.getDtNasc().toString() : null;
            case "genero":
                return personal.getGenero();
            default:
                return null;
        }
    }

    private boolean validarNome(String nome) {
        return !StringUtils.isEmpty(nome);
    }

    public boolean validarUsername(String username) {
        return username != null && !username.isEmpty() && username.length() <= 20;
    }

    public boolean validarEmail(String email) {
        return email != null && !email.isEmpty() && email.matches("^(.+)@(.+)$") && email.length() <= 100;
    }

    public boolean validarEmail2(String email2) {
        return email2 != null && !email2.isEmpty() && email2.matches("^(.+)@(.+)$") && email2.length() <= 100;
    }


    public boolean validarSenha(String senha) {
        if (senha == null || senha.isEmpty() || senha.length() < 8) {
            return false;
        }

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";
        return senha.matches(regex);
    }

    private boolean validarCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    public boolean validarGenero(String genero) {
        return genero != null && (genero.equals("M") || genero.equals("F") || genero.equals("N/A"));
    }

    private boolean validarDtNasc(String dtNasc) {
        return dtNasc != null && !dtNasc.isEmpty();
    }
}
