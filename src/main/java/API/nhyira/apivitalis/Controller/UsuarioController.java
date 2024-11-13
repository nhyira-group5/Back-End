package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Midia.MidiaExibitionDto;
import API.nhyira.apivitalis.DTO.Midia.MidiaMapper;
import API.nhyira.apivitalis.DTO.Usuario.*;
import API.nhyira.apivitalis.Entity.*;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Pagamento.Service.PagamentoService;
import API.nhyira.apivitalis.Service.CsvService;
import API.nhyira.apivitalis.Service.FichaService;
import API.nhyira.apivitalis.Service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService uService;
    private final FichaService fichaService;
    private final CsvService csvService;
    private final PagamentoService pagSrv;

    @PostMapping
    public ResponseEntity<UsuarioExibitionDto> create(
            @RequestBody @Valid UsuarioCreateEditDto newUser
    ) {
        Usuario user = UsuarioMapper.toDto(newUser);
        Usuario userNovo = this.uService.createUser(user, newUser.getAcademiaId());
        UsuarioExibitionDto exibitionDto = UsuarioMapper.toExibition(userNovo);
        URI uri = URI.create("/usuarios/" + exibitionDto.getId());
        return ResponseEntity.created(uri).body(exibitionDto);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioExibitionDto>> showAll() {
        List<UsuarioExibitionDto> dtos = new ArrayList<>(0);
        List<Usuario> users = uService.showAllUsers();
        for (Usuario u : users) {
            Meta meta = uService.searchMetaUsuario(u);
            Boolean pagamentoAtivo = pagSrv.verifyUserPagamento(u);
            dtos.add(UsuarioMapper.toExibition(u, meta, pagamentoAtivo));
        }
        return dtos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(dtos);
    }

    @GetMapping("/usuario-afiliado/{id}")
    public ResponseEntity<List<UsuarioExibitionDto>> showUserAfiliando(
            @PathVariable int id
    ) {
        List<UsuarioExibitionDto> dtos = new ArrayList<>(0);
        List<Usuario> users = uService.showUsserAfiliado(id);
        for (Usuario u : users) {
            Meta meta = uService.searchMetaUsuario(u);
            Boolean pagamentoAtivo = pagSrv.verifyUserPagamento(u);
            dtos.add(UsuarioMapper.toExibition(u, meta, pagamentoAtivo));
        }
        return dtos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(dtos);
    }

    @GetMapping("/personal/{userId}")
    public ResponseEntity<UsuarioExibitionDto> buscarPersonalPorUsuario(
            @PathVariable Integer userId
    ) {
        UsuarioExibitionDto dto = uService.buscarPersonalPorUsuario(userId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/personais")
    public ResponseEntity<List<PersonalEspecialidadeDto>> showAllPersonal() {
        List<PersonalEspecialidadeDto> dtos = new ArrayList<>(0);
        List<Usuario> users = uService.showAllUsersPersonal();
        for (Usuario u : users) {
            List<EspecialidadePorPersonal> personal = uService.buscarEspecialidade(u);
            dtos.add(UsuarioMapper.toDtoPersonal(u, personal));
        }
        return dtos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(dtos);
    }

    @GetMapping("/personais-por-meta/{idMeta}")
    public ResponseEntity<List<PersonalEspecialidadeDto>> showTrainersByMeta(
            @PathVariable int idMeta
    ) {
        if (idMeta <= 0) throw new ErroClienteException("ID Meta");
        List<PersonalEspecialidadeDto> dtos = new ArrayList<>(0);
        List<Usuario> users = uService.showTrainersByMeta(idMeta);
        for (Usuario u : users) {
            List<EspecialidadePorPersonal> personal = uService.buscarEspecialidade(u);
            dtos.add(UsuarioMapper.toDtoPersonal(u, personal));
        }
        return dtos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(dtos);
    }

    @GetMapping("/export/csv")
    public ResponseEntity<String> exportToCsv() {
        try {
            csvService.exportUsersToCsv();
            return ResponseEntity.status(200).body("Arquivo CSV gerado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao gerar o arquivo CSV.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioExibitionDto> showUser(
            @PathVariable int id
    ) {
        if (id <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Usuario user = uService.showUserById(id);
        Meta meta = uService.searchMetaUsuario(user);
        Boolean pagamentoAtivo = pagSrv.verifyUserPagamento(user);
        UsuarioExibitionDto exibitionDto = UsuarioMapper.toExibition(user, meta, pagamentoAtivo);
        return ResponseEntity.ok(exibitionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioExibitionDto> update(
            @PathVariable int id,
            @RequestBody @Valid UsuarioCreateEditDto updtUser
    ) {
        if (id <= 0) throw new NaoEncontradoException("ID");
        Usuario updatedUser = uService.updtUser(id, updtUser);
        UsuarioExibitionDto exibitionDto = UsuarioMapper.toExibition(updatedUser);
        return ResponseEntity.status(200).body(exibitionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable int id
    ) {
        if (id <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        boolean user = uService.delUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/por-username")
    public ResponseEntity<UsuarioExibitionDto> buscarUsuarioPorUsername(
            @RequestBody @Valid UsuarioDto user
    ) {
        UsuarioExibitionDto usuario = uService.findUserByUsername(user.getNickname());
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar-imc/{id}")
    public ResponseEntity<UsuarioFichaDto> showImc(
            @PathVariable int id
    ) {
        if (id <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Usuario user = uService.showUserById(id);
        Ficha ficha = fichaService.showFicha(id);
        UsuarioFichaDto exibitionDto = UsuarioMapper.toExibitionIMC(user, ficha.getIMC());
        return ResponseEntity.ok(exibitionDto);
    }

    @PatchMapping("atualizar-midia/{idUsuario}")
    public MidiaExibitionDto updateMidia(@RequestBody UsuarioUpdateMidia usuarioUpdateMidia, @PathVariable int idUsuario){
        Midia midia = uService.AtualizarFoto(idUsuario, usuarioUpdateMidia);
        return MidiaMapper.toDTO(midia);
    }
}
