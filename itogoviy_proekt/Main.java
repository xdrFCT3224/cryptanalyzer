import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("""
                    
                    1 - Encrypt
                    2 - Decrypt
                    3 - Brute Force
                    4 - Statistical Analysis
                    0 - Exit
                    
                    """);

            System.out.print("выберите действие: ");

            int choice = Integer.parseInt(scanner.nextLine());//читаем выбор пользователя

            switch (choice) {//выыбираем действие

                case 1 -> encrypt();

                case 2 -> decrypt();

                case 3 -> bruteForce();

                case 4 -> statisticalAnalysis();

                case 0 -> {
                    System.out.println("программа закрыта");//завершаем программу
                    return;
                }

                default -> System.out.println("нету такого действия");
            }
        }
    }

    private static void encrypt() {//шифровка

        try {

            System.out.print("введите путь к файлу с текстом: ");
            String inputPath = scanner.nextLine();

            System.out.print("введите путь к файлу в который записать результат : ");
            String outputPath = scanner.nextLine();

            System.out.print("Key: ");
            int key = Integer.parseInt(scanner.nextLine());

            String text = FileManager.readFile(inputPath);

            String encrypted = CaesarCipher.encrypt(text, key);

            FileManager.writeFile(outputPath, encrypted);

            System.out.println("файл зашифрован");

        } catch (Exception e) {//обработка ошибок чтоб программа не сломалась
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void decrypt() {//расшиифровка

        try {

            System.out.print("введите путь к файлу с текстом: ");
            String inputPath = scanner.nextLine();

            System.out.print("введите путь к файлу в который записать результат: ");
            String outputPath = scanner.nextLine();

            System.out.print("Key: ");
            int key = Integer.parseInt(scanner.nextLine());

            String text = FileManager.readFile(inputPath);

            String decrypted = CaesarCipher.decrypt(text, key);

            FileManager.writeFile(outputPath, decrypted);

            System.out.println("файл расшифрован");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



private static void bruteForce() {

    try {

        System.out.print("введите путь к файлу с текстом: ");
        String inputPath = scanner.nextLine();

        System.out.print("введите путь к файлу в который записать результат: ");
        String outputPath = scanner.nextLine();

        String text = FileManager.readFile(inputPath);

        StringBuilder result = new StringBuilder();

        for (int key = 0; key < CaesarCipher.ALPHABET.length(); key++) {

            String decrypted = CaesarCipher.decrypt(text, key);

            result.append("KEY = ")
                    .append(key)
                    .append("\n");//вывод ключа

            result.append(decrypted)
                    .append("\n\n");
        }

        FileManager.writeFile(outputPath, result.toString());//запись в файл

        System.out.println("Brute force results saved to file");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

    private static void statisticalAnalysis() {//анализатор

        try {

            System.out.print("введите путь к файлу с текстом: ");
            String inputPath = scanner.nextLine();

            String encryptedText = FileManager.readFile(inputPath);

            int key = StatisticalAnalysis.findKey(encryptedText);

            String decrypted = CaesarCipher.decrypt(encryptedText, key);

            System.out.println("Possible key: " + key);
            System.out.println("\nDecrypted text:");
            System.out.println(decrypted);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}