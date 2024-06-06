package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("\uD83C\uDF9F\uFE0F - Testes de contratoService")
class FichaServiceTest {


    @Mock
    private FichaService fichaService;


    @InjectMocks
    private UsuarioRepository usuarioRepository;


}