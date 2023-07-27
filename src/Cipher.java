import java.io.*;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            if(key < 0){
                return engAlphabetAbc.charAt(((engAlphabetAbc.indexOf(symbol) + key) % engAlphabetAbc.length()) + engAlphabetAbc.length());
            }
            return engAlphabetAbc.charAt((engAlphabetAbc.indexOf(symbol) + key) % engAlphabetAbc.length());
        } else {
            return symbol;
        }
    }
    private static char encryptionSymbolABC(char symbol, int key){
        if(engAlphabetABC.indexOf(symbol) != -1){
            if(key < 0){
                return engAlphabetABC.charAt(((engAlphabetABC.indexOf(symbol) + key) % engAlphabetABC.length()) + engAlphabetABC.length());
            }
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
    private String dictionary[] = {"hello", "world"};
    public static void bruteForce(String pathFile) throws  IOException{
        try (FileInputStream fis = new FileInputStream(pathFile)) {
            String encryptionText = "";
            int a = 0;
            while ((a = fis.read()) != -1) {
                encryptionText = encryptionText + String.valueOf((char) a);
            }
            System.out.println(encryptionText);
            int key = 0;
            for (int i = 0; i < engAlphabetAbc.length(); i++) {
                int b = 0;
                String decryptionText = "";
                while (b < encryptionText.length()) {
                    if(Character.isUpperCase(encryptionText.charAt(b))) {
                        decryptionText = decryptionText + String.valueOf(encryptionSymbolABC((char) encryptionText.charAt(b), key));
                    }else{
                        decryptionText = decryptionText + String.valueOf(encryptionSymbolAbc((char) encryptionText.charAt(b), key));
                    }
                    b++;
                }
                decryptionText = decryptionText.toLowerCase();
                Pattern pattern = Pattern.compile("hello(\\w*)");
                Matcher matcher = pattern.matcher(decryptionText);
                while(matcher.find()) {
                    System.out.println(matcher.group() + " key: " + key);
                    encryption(pathFile, key);
                }
                key++;
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}