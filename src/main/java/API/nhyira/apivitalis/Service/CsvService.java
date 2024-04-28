package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Entity.Usuario;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


@Service
public class CsvService {

    @Autowired
    private UsuarioService usuarioService;

    public void exportUsersToCsv() throws IOException {
        List<Usuario> usuarios = usuarioService.getAllUsers();

        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter("usuarios.csv"), CSVFormat.DEFAULT.withDelimiter(';').withHeader("ID", "Nome", "Email"))) {
            for (Usuario usuario : usuarios) {
                csvPrinter.printRecord(usuario.getIdUsuario(), usuario.getNome(), usuario.getEmail());
            }
        }
    }

}
