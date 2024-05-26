package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Entity.Usuario;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Service
public class CsvService {

    @Autowired
    private UsuarioService usuarioService;

    public void exportUsersToCsv() throws IOException {
        List<Usuario> usuarios = usuarioService.getAllUsers();


        usuarios.sort(Comparator.comparing(Usuario::getNome)
                .thenComparing(Usuario::getDtNasc));

        String[][] userDataMatrix = new String[usuarios.size()][5];

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            userDataMatrix[i][0] = usuario.getNome();
            userDataMatrix[i][1] = usuario.getEmail();
            userDataMatrix[i][2] = usuario.getCpf();
            userDataMatrix[i][3] = usuario.getDtNasc().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            userDataMatrix[i][4] = usuario.getSexo();
        }

        int[] columnWidths = calculateColumnWidths(userDataMatrix);

        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter("usuarios.csv"), CSVFormat.DEFAULT.withDelimiter(';'))) {
            for (String[] userData : userDataMatrix) {
                String[] formattedData = formatDataWithColumnWidths(userData, columnWidths);
                csvPrinter.printRecord((Object[]) formattedData);
            }
        }
    }

    private int[] calculateColumnWidths(String[][] data) {
        int[] columnWidths = new int[data[0].length];
        for (String[] row : data) {
            for (int i = 0; i < row.length; i++) {
                columnWidths[i] = Math.max(columnWidths[i], row[i].length());
            }
        }
        return columnWidths;
    }

    private String[] formatDataWithColumnWidths(String[] data, int[] columnWidths) {
        String[] formattedData = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            formattedData[i] = String.format("%-" + columnWidths[i] + "s", data[i]);
        }
        return formattedData;
    }
}
