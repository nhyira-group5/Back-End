package API.nhyira.Service;

<<<<<<< HEAD:nhyira/src/main/java/API/nhyira/PersonalService.java
import org.springframework.http.ResponseEntity;
=======
import API.nhyira.Model.PersonalModel;
>>>>>>> fd49bcc327a8e63ede2ddc3dc430ab34102c685c:nhyira/src/main/java/API/nhyira/Service/PersonalService.java
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import org.springframework.util.StringUtils;
import java.util.*;
import java.util.function.Predicate;

@Service
public class PersonalService {

    private final List<PersonalModel> personals = new ArrayList<>();
    private long proximoId = 1;

<<<<<<< HEAD:nhyira/src/main/java/API/nhyira/PersonalService.java
    public Map<String, List<PersonalModel>> obterTodosPersonalsOrdenadosPorEspecialidade() {
        Map<String, List<PersonalModel>> personalsPorEspecialidade = new HashMap<>();
        List<PersonalModel> todosPersonals = obterTodosPersonals();

        for (PersonalModel personal : todosPersonals) {
            String especialidade = personal.getEspecialidade();
            personalsPorEspecialidade.computeIfAbsent(especialidade, key -> new ArrayList<>()).add(personal);
        }


        for (List<PersonalModel> personals : personalsPorEspecialidade.values()) {
            selectionSort(personals);
        }

        return personalsPorEspecialidade;
    }

    private void selectionSort(List<PersonalModel> personals) {
        int n = personals.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (personals.get(j).getNome().compareTo(personals.get(minIndex).getNome()) < 0) {
                    minIndex = j;
                }
            }

            PersonalModel temp = personals.get(minIndex);
            personals.set(minIndex, personals.get(i));
            personals.set(i, temp);
        }
    }

    public PersonalModel criarPersonal(PersonalModel personal) {
        try {
            validarPersonal(personal);

            // Verifica se o ID foi fornecido pelo cliente
            if (personal.getId() != null) {
                throw new IllegalArgumentException("O ID não deve ser fornecido ao criar um novo personal");
            }

            // Validação adicional para o CPF e email
            for (PersonalModel p : personals) {
                if (p.getCpf().equals(personal.getCpf()) && p.getEmail().equals(personal.getEmail())) {
                    throw new IllegalArgumentException("CPF e email duplicados");
                }
            }

            // Validação adicional para o CPF
            for (PersonalModel p : personals) {
                if (p.getCpf().equals(personal.getCpf())) {
                    throw new IllegalArgumentException("CPF duplicado");
                }
            }

            // Validação adicional para o email
            for (PersonalModel p : personals) {
                if (p.getEmail().equals(personal.getEmail())) {
                    throw new IllegalArgumentException("Email duplicado");
                }
            }

            // Se nenhum CPF ou email duplicado for encontrado, adiciona o personal
            personal.setId(proximoId++);
=======
    public PersonalModel criarPersonal(PersonalModel personal) {
        try {
            validarPersonal(personal);
            personal.setIdPersonal((int) proximoId++);
>>>>>>> fd49bcc327a8e63ede2ddc3dc430ab34102c685c:nhyira/src/main/java/API/nhyira/Service/PersonalService.java
            personals.add(personal);
            return personal;
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao criar personal: " + e.getMessage());
        }
    }


<<<<<<< HEAD:nhyira/src/main/java/API/nhyira/PersonalService.java

    public boolean existePersonal(Long id) {
        PersonalModel personal = obterPersonalPorId(id);
        return personal != null;
    }

    public PersonalModel atualizarPersonal(PersonalModel detalhesPersonal) {
        try {
            validarPersonal(detalhesPersonal); // Valida os detalhes do personal
        } catch (Exception e) {
            // Trate a exceção aqui
            throw new IllegalArgumentException("Erro ao validar os detalhes do personal: " + e.getMessage());
=======
    public PersonalModel atualizarPersonal(Long id, PersonalModel detalhesPersonal) {
        PersonalModel personalExistente = obterPersonalPorId(id);
        if (personalExistente != null) {
            try {
                validarPersonal(detalhesPersonal);
                personalExistente.setNome(detalhesPersonal.getNome());
                personalExistente.setUsername(detalhesPersonal.getUsername());
                personalExistente.setEmail(detalhesPersonal.getEmail());
                personalExistente.setEmail2(detalhesPersonal.getEmail2());
                personalExistente.setSenha(detalhesPersonal.getSenha());
                personalExistente.setCpf(detalhesPersonal.getCpf());
                personalExistente.setDtNasc(detalhesPersonal.getDtNasc());
                personalExistente.setGenero(detalhesPersonal.getGenero());
                return personalExistente;
            } catch (Exception e) {
                throw new IllegalArgumentException("Erro ao atualizar personal: " + e.getMessage());
            }
        } else {
            return null;
>>>>>>> fd49bcc327a8e63ede2ddc3dc430ab34102c685c:nhyira/src/main/java/API/nhyira/Service/PersonalService.java
        }

        PersonalModel personalExistente = obterPersonalPorId(detalhesPersonal.getId()); // Obtém o personal existente pelo ID

        if (personalExistente == null) {
            throw new NoSuchElementException("Personal não encontrado");
        }

        personalExistente.setNome(detalhesPersonal.getNome());
        personalExistente.setEmail(detalhesPersonal.getEmail());
        personalExistente.setSenha(detalhesPersonal.getSenha());
        personalExistente.setCpf(detalhesPersonal.getCpf());
        personalExistente.setTelefone(detalhesPersonal.getTelefone());
        personalExistente.setEndereco(detalhesPersonal.getEndereco());
        personalExistente.setEspecialidade(detalhesPersonal.getEspecialidade());

        return personalExistente;
    }

    public void excluirPersonal(Long id) {
        PersonalModel personal = obterPersonalPorId(id);
        if (personal != null) {
            personals.remove(personal);
        } else {
            throw new NoSuchElementException("Personal não encontrado");
        }
    }

    public List<PersonalModel> obterTodosPersonals() {
        return new ArrayList<>(personals);
    }

    public PersonalModel obterPersonalPorId(Long id) {
        for (PersonalModel personal : personals) {
            if (personal.getIdPersonal() == id.intValue()) {
                return personal;
            }
        }
        return null;
    }

    public void validarPersonal(PersonalModel personal) throws Exception {
        Map<String, Predicate<String>> validacoes = criarValidacoes();

        for (Map.Entry<String, Predicate<String>> entry : validacoes.entrySet()) {
            String atributo = entry.getKey();
            Predicate<String> validacao = entry.getValue();
            String valor = obterValor(personal, atributo);

            if (!validacao.test(valor)) {
                throw new IllegalArgumentException("Erro de validação para: " + atributo);
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

    private String obterValor(PersonalModel personal, String atributo) {
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
                return String.valueOf(personal.getDtNasc());
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

<<<<<<< HEAD:nhyira/src/main/java/API/nhyira/PersonalService.java
    private boolean validarSenha(String senha) {
        if (StringUtils.isEmpty(senha) || senha.length() < 6) {
            return false;
        }

        return senha.matches("^(?=.*[A-Z])(?=.*\\d).+$");
=======
    public boolean validarEmail(String email) {
        return email != null && !email.isEmpty() && email.matches("^(.+)@(.+)$") && email.length() <= 100;
    }

    public boolean validarEmail2(String email2) {
        return email2 == null || email2.isEmpty() || email2.matches("^(.+)@(.+)$") && email2.length() <= 100;
    }


    public boolean validarSenha(String senha) {
        if (senha == null || senha.isEmpty() || senha.length() < 6) {
            return false;
        }


        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";
        return senha.matches(regex);
>>>>>>> fd49bcc327a8e63ede2ddc3dc430ab34102c685c:nhyira/src/main/java/API/nhyira/Service/PersonalService.java
    }

    private boolean validarCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    public boolean validarGenero(String genero) {
        return genero != null && (genero.equals("M") || genero.equals("F") || genero.equals("N/A"));
    }

    private boolean validarDtNasc(String dtNascStr) {
        if (dtNascStr == null) {
            return false;
        }

        try {
            LocalDate dtNasc = LocalDate.parse(dtNascStr);

            LocalDate dataAtual = LocalDate.now();

            return dtNasc.isBefore(dataAtual);
        } catch (Exception e) {
            return false;
        }
    }
}
