package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.DTO.Midia.MidiaCreateEditDto;
import API.nhyira.apivitalis.DTO.Midia.MidiaExibitionDto;
import API.nhyira.apivitalis.DTO.Midia.MidiaMapper;
import API.nhyira.apivitalis.Entity.Midia;
import API.nhyira.apivitalis.Repository.MidiaRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MidiaService {
    @Autowired
    private MidiaRepository midiaRepository;

    public List<MidiaExibitionDto> getAllMidias() {
        return midiaRepository.findAll().stream()
                .map(MidiaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Midia findById(int id) {
        return midiaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dpzjmq6x5",
            "api_key", "679425973318895",
            "api_secret", "R-uaJBcdZUfUGu5TuYmiHodqBl4"));

    public String uploadImage(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return (String) uploadResult.get("urxl");
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

    public MidiaExibitionDto getMidiaById(Integer id) {
        Midia midia = midiaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return MidiaMapper.toDTO(midia);
    }

    public MidiaExibitionDto createMidia(MidiaCreateEditDto midiaDTO) {
        Midia midia = MidiaMapper.toEntity(midiaDTO);
        midia = midiaRepository.save(midia);
        return MidiaMapper.toDTO(midia);
    }

    public MidiaExibitionDto updateMidia(Integer id, MidiaCreateEditDto midiaDTO) {
        Midia midia = midiaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        midia.setNome(midiaDTO.getNome());
        midia.setCaminho(midiaDTO.getCaminho());
        midia.setExtensao(midiaDTO.getExtensao());
        midia = midiaRepository.save(midia);
        return MidiaMapper.toDTO(midia);
    }

    public void deleteMidia(Integer id) {
        Midia midia = midiaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        midiaRepository.deleteById(id);
    }
}