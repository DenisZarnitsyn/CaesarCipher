import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

public class Cipher {
    //English alphabet ABC
    private static final String  engAlphabetABC = new String(new char[]
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                    '.', ',', '"', ':', '-', '!', '?', ' '});
    //English alphabet abc
    private static final String  engAlphabetAbc = new String(new char[]
            {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                    '.', ',', '"', ':', '-', '!', '?', ' '});
    private static char encryptionSymbolAbc(char symbol, int key) {
        if (engAlphabetAbc.indexOf(symbol) != -1) {
            return engAlphabetAbc.charAt((engAlphabetAbc.indexOf(symbol) + key) % engAlphabetAbc.length());
        } else {
            return symbol;
        }
    }
    private static char encryptionSymbolABC(char symbol, int key){
            if(engAlphabetABC.indexOf(symbol) != -1){
                return engAlphabetABC.charAt((engAlphabetABC.indexOf(symbol) + key) % engAlphabetABC.length());
            }else {
                return symbol;
            }
    }
    public static void encryption(String pathFile, int key) throws IOException {
        try (FileInputStream fis = new FileInputStream(pathFile)) {
            String encryptionText = "";
            int i = 0;
            while ((i = fis.read()) != -1) {
                if(Character.isUpperCase(i)) {
                    encryptionText = encryptionText + String.valueOf(encryptionSymbolABC((char) i, key));
                }else{
                    encryptionText = encryptionText + String.valueOf(encryptionSymbolAbc((char) i, key));
                }
            }
            System.out.print(encryptionText);
            CreateWriteFile.createWriteFile(encryptionText, pathFile);
        }catch(IOException ex){
                System.out.println(ex.getMessage());
            }
    }
}
