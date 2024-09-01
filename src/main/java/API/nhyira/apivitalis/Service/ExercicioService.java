package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.DTO.Exercicio.ExercicioExibition;
import API.nhyira.apivitalis.DTO.Exercicio.ExercicioExibitionDto;
import API.nhyira.apivitalis.DTO.Exercicio.ExercicioMapper;
import API.nhyira.apivitalis.Entity.Exercicio;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private ExercicioMapper exercicioMapper;

    @Autowired
    private MidiaService midiaService;


    private Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "Vitalis",
            "api_key", "679425973318895",
            "api_secret", "R-uaJBcdZUfUGu5TuYmiHodqBl4"));

    public String uploadImage(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return (String) uploadResult.get("url");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String uploadVideo(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "video"));
            return (String) uploadResult.get("url");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


//    public List<ExercicioExibition> getAllExercicios() {
//        return exercicioRepository.findAll().stream()
//                .map(exercicioMapper::toDto)
//                .collect(Collectors.toList());
//    }

    public List<Exercicio> getAllExercicios(){
        return exercicioRepository.findAll();
    }

    public ExercicioExibition getExercicioById(Integer id) {
        Exercicio exercicio = exercicioRepository.findById(id).orElseThrow(() -> new  NaoEncontradoException("Exercicio"));
        return exercicioMapper.toDto(exercicio);
    }

    public Exercicio showPorRotina(Integer id) {
        Exercicio exercicio = exercicioRepository.buscarPorTreino(id);
        return exercicio;
    }
}