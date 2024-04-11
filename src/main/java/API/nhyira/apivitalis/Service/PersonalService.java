package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Auth.Configuration.Security.TokenGenJwt;
import API.nhyira.apivitalis.Auth.Personal.DTO.PersonalLoginDto;
import API.nhyira.apivitalis.Auth.Personal.DTO.PersonalTokenDto;
import API.nhyira.apivitalis.DTO.Personal.PersonalCreateDto;
import API.nhyira.apivitalis.DTO.Personal.PersonalExibitionDto;
import API.nhyira.apivitalis.DTO.Personal.PersonalMapper;
import API.nhyira.apivitalis.Entity.Personal;
import API.nhyira.apivitalis.Repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalService {

    @Autowired
    private PersonalRepository pRep;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenGenJwt tokenGenJwt;

    public PersonalTokenDto autenticar(PersonalLoginDto personalLogin){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(personalLogin.getLogin(), personalLogin.getSenha());

        final Authentication auth = authenticationManager.authenticate(credentials);

        Optional<Personal> personalByEmail = pRep.findByEmailIgnoreCase(personalLogin.getLogin());
        Optional<Personal> personalByUsername = pRep.findByUsernameIgnoreCase(personalLogin.getLogin());

        final  String token = tokenGenJwt.generateToken(auth);
        Personal user = null;
        if (personalByEmail.isPresent()){
            user = personalByEmail.get();
        }else if (personalByUsername.isPresent()){
            user = personalByUsername.get();
        }else {
            throw new ResponseStatusException(404, "Credencial de login do personal não cadastrado!", null);
        }

        SecurityContextHolder.getContext().setAuthentication(auth);

        return PersonalMapper.of(user, token);
    }

    public PersonalExibitionDto createUser(PersonalCreateDto personal){
        try {
            if (personal != null){
                Personal newpersonal = PersonalMapper.toDto(personal);
                newpersonal.setSenha(encoder.encode(newpersonal.getSenha()));
                pRep.save(newpersonal);

                return PersonalMapper.toExibition(newpersonal);
            }
        }catch (RuntimeException e){
            throw new RuntimeException("Erro ao criar o personal: " + e.getMessage());
        }
        return null;
    }

    public List<PersonalExibitionDto> showAllPersonals(){
        List<PersonalExibitionDto> allPersonals;
        try {
            allPersonals = pRep.findAll().stream().map(PersonalMapper::toExibition).toList();
        }catch (RuntimeException e){
            throw new RuntimeException("Erro ao buscar os personais: " + e.getMessage());
        }
        return allPersonals;
    }

    public PersonalExibitionDto showUserById(int id){
        try{
            Optional<Personal> personal = pRep.findById(id);

            if (personal.isPresent()){
                return PersonalMapper.toExibition(personal.get());
            }
        }catch (RuntimeException e){
            throw new RuntimeException("Erro ao buscar os personais: " + e.getMessage());
        }
        return null;
    }

    public PersonalExibitionDto updPersonal(int id, PersonalCreateDto updatePersonal){
        try {
            if (pRep.existsById(id)){
                Personal personalSave = PersonalMapper.toEditDto(pRep.findById(id).get(), updatePersonal);
                personalSave.setSenha(encoder.encode(personalSave.getSenha()));

                PersonalExibitionDto personal = PersonalMapper.toExibition(personalSave);
                pRep.save(personalSave);
                return personal;
            }
        }catch (RuntimeException e){
            throw new RuntimeException("Erro ao atualizar o usuário: " + e.getMessage());
        }
        return null;
    }

    public boolean delPersonal(int id){
        try {
            if (pRep.existsById(id)){
                pRep.deleteById(id);
                return true;
            }
        }catch (RuntimeException e){
            throw new RuntimeException("Erro ao deletar usuário: " + e.getMessage());
        }
        return false;
    }

    public boolean nomeUnique(String username){
        return pRep.findByUsernameIgnoreCase(username).isPresent();
    }

    public boolean emailUnique(String email){
        return pRep.findByEmailIgnoreCase(email).isPresent();
    }

    public boolean cpfUnique(String cpf){
        return pRep.findByCpf(cpf).isPresent();
    }

}
