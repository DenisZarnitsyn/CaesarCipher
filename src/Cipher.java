import java.io.*;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cipher {
    //English alphabet
    private static final String  engAlphabet = new String(new char[] //
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                    '.', ',', '"', ':', '-', '!', '?', ' '});
    private static char encryptionSymbol(char symbol, int key) {
        if (engAlphabet.indexOf(symbol) != -1) {
            if(key < 0){
                return engAlphabet.charAt(((engAlphabet.indexOf(symbol) + key) + engAlphabet.length()) % engAlphabet.length());
            } else {
                return engAlphabet.charAt((engAlphabet.indexOf(symbol) + key) % engAlphabet.length());
            }
        } else {
            return symbol;
        }
    }
    public static void encryption(String pathFile, int key) throws IOException {
        if(key > engAlphabet.length()) {
            key = key % engAlphabet.length();
        }
        try (FileInputStream fis = new FileInputStream(pathFile)) {
            String encryptionText = "";
            int i = 0;
            while ((i = fis.read()) != -1) {
                    encryptionText = encryptionText + String.valueOf(encryptionSymbol((char) i, key));
            }
            CreateWriteFile.createWriteFile(encryptionText, pathFile);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static String[] dictionary = {"the ", "were ", "they ", "you ", "can ", "use ", "for ", "when ", "from ", "that ", "about ", "! ", " ", ", ", "\\s"};
    public static void bruteForce(String pathFile) throws  IOException{
        try (FileInputStream fis = new FileInputStream(pathFile)) {
            String encryptionText = "";
            int a = 0;
            while ((a = fis.read()) != -1) {
                encryptionText = encryptionText + String.valueOf((char) a);
            }
            int key = 0;
            for (int i = 0; i < engAlphabet.length(); i++) {
                int b = 0;
                String decryptionText = "";
                while (b < encryptionText.length()) {
                        decryptionText = decryptionText + String.valueOf(encryptionSymbol((char) encryptionText.charAt(b), key));
                    b++;
                }
                for (int j = 0; j < dictionary.length; j++) {
                    Pattern pattern = Pattern.compile(dictionary[j], Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(decryptionText);
                    if(matcher.find()) {
                        encryption(pathFile, key);
                        break;
                    }
                }
                key++;
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}