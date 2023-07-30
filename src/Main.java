import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Encrypt press 1.\nDecrypt press 2.\nCrack the cipher press 3.");
        int choice = scanner.nextInt();
        if(choice == 1) {
            scanner.nextLine();
            System.out.println("Enter the file path:");
            String pathFile = scanner.nextLine();
            System.out.println("The encryption key ranges from -59 to 59:");
            int key = scanner.nextInt();
            Cipher.encryption(pathFile, key);
        }else if(choice == 2){
            scanner.nextLine();
            System.out.println("Enter the file path:");
            String pathFile = scanner.nextLine();
            System.out.println("The encryption key ranges from -59 to 59:");
            int key = scanner.nextInt();
            key = (-1) * key;
            Cipher.encryption(pathFile, key);
        } else if (choice == 3) {
            scanner.nextLine();
            System.out.println("Enter the file path:");
            String pathFile = scanner.nextLine();
            Cipher.bruteForce(pathFile);
        }else{
            System.out.println("There is no such option. Make the right choice!");
        }
    }
}