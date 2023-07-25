import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class CreateWriteFile {
    public static void createWriteFile(String encryptionText, String pathFile) throws IOException {
        Path pathSave = Path.of(pathFile).getParent();
        Files.createFile(Path.of(pathSave + "\\encryption.txt"));
        try(FileWriter writer = new FileWriter(pathSave + "\\encryption.txt", false))
        {
            writer.write(encryptionText);
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
