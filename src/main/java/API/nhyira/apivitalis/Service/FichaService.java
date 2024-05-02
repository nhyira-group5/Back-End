package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.DTO.Ficha.FichaCreateEditDto;
import API.nhyira.apivitalis.DTO.Ficha.FichaExibitionDto;
import API.nhyira.apivitalis.DTO.Ficha.FichaMapper;
import API.nhyira.apivitalis.Entity.Ficha;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Repository.FichaRepository;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import API.nhyira.apivitalis.utils.ListaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FichaService {

    @Autowired
    private FichaRepository fichaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public FichaExibitionDto create(FichaCreateEditDto dto) {
        try {
            if (dto != null) {
                Optional<Usuario> optUsuario = usuarioRepository.findById(dto.getUsuarioId());
                if (optUsuario.isEmpty()){
                    return null;
                }
                Ficha ficha = FichaMapper.toDto(dto);
                ficha.setFkUsuario(optUsuario.get());
                fichaRepository.save(ficha);
                return FichaMapper.toEntity(ficha);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao criar ficha: " + e.getMessage());
        }
        return null;
    }

    public FichaExibitionDto showFicha(int id){
        try {
            if (id >= 1){
                Optional<Usuario> usuario = usuarioRepository.findById(id);
                if (usuario.isPresent()){
                    Optional<Ficha> ficha = fichaRepository.findByFkUsuarioIs(usuario.get());
                    if (ficha.isPresent()){
                        return FichaMapper.toEntity(ficha.get());
                    }
                }

            }
        }catch (RuntimeException e) {
            throw new RuntimeException("Erro a mostrar ficha " + e.getMessage());
        }
        return null;
    }

    public FichaExibitionDto updtFicha(int id , FichaCreateEditDto dto) {
        try {
            if (dto != null || id >= 1) {
                Optional<Usuario> optUsuario = usuarioRepository.findById(id);
                if (optUsuario.isPresent()){
                    Optional<Ficha> optFicha = fichaRepository.findByFkUsuarioIs(optUsuario.get());
                    if (optFicha.isPresent()){
                        Ficha ficha = FichaMapper.toEdit(optFicha.get() ,dto);
                        fichaRepository.save(ficha);
                        return FichaMapper.toEntity(ficha);
                    }
                }


            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro a atualizar ficha: " + e.getMessage());
        }
        return null;
    }


    public Boolean delUser(int id){
        try {
            Optional<Usuario> optUsuario = usuarioRepository.findById(id);
            if (optUsuario.isPresent()){
                Optional<Ficha> optFicha = fichaRepository.findByFkUsuarioIs(optUsuario.get());
                optFicha.ifPresent(ficha -> fichaRepository.delete(ficha));
                return true;
            }
            return false;
        }catch (RuntimeException e) {
            throw new RuntimeException("Erro ao deletar ficha: " + e.getMessage());
        }

    }

    public List<FichaExibitionDto> ordenarTodasFichasPorDeficiencias() {
        List<Ficha> fichas = fichaRepository.findAll();

        List<Ficha> fichasComDeficiencias = fichas.stream()
                .filter(ficha -> {
                    String deficiencia = ficha.getDeficiencias();
                    return deficiencia != null && !normalize(deficiencia).equals("nao") && !deficiencia.isEmpty();
                })
                .collect(Collectors.toList());

        if (fichasComDeficiencias.isEmpty()) {
            return null;
        }

        fichasComDeficiencias.sort(Comparator.comparing(ficha -> normalize(ficha.getDeficiencias())));

        return FichaMapper.toDtoList(fichasComDeficiencias);
    }

    private String normalize(String input) {
        return Normalizer.normalize(input.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

}
