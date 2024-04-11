package API.nhyira.apivitalis.Auth.Personal;

import API.nhyira.apivitalis.Auth.Personal.DTO.DetailsPersonal;
import API.nhyira.apivitalis.Auth.Usuario.DTO.DetailsUsuario;
import API.nhyira.apivitalis.Entity.Personal;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class AuthPersonalService implements UserDetailsService {

    @Autowired
    private PersonalRepository pRep;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Personal> personalByUsername = pRep.findByUsernameIgnoreCase(login);

        if (personalByUsername.isEmpty()) {
            Optional<Personal> personalByEmail = pRep.findByEmailIgnoreCase(login);

            if (personalByEmail.isEmpty()) {
                throw new UsernameNotFoundException("Personal com a credencial [" + login + "] não encontrado!");
            }
            return new DetailsPersonal(personalByEmail.get());
        }
        return new DetailsPersonal(personalByUsername.get());
    }
}
