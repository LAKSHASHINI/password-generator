import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_=+[]{}|;:,.<>?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User Input
        System.out.print("Enter the desired length of the password: ");
        int passwordLength = scanner.nextInt();

        System.out.println("Select the character types to include in the password:");
        System.out.println("1. Uppercase letters");
        System.out.println("2. Lowercase letters");
        System.out.println("3. Numbers");
        System.out.println("4. Symbols");
        System.out.println("Enter the numbers corresponding to the character types (comma-separated): ");
        String charTypes = scanner.next();

        boolean includeUppercase = charTypes.contains("1");
        boolean includeLowercase = charTypes.contains("2");
        boolean includeNumbers = charTypes.contains("3");
        boolean includeSpecialChars = charTypes.contains("4");

        // Error Handling
        if (passwordLength < 1 || (!includeUppercase && !includeLowercase && !includeNumbers && !includeSpecialChars)) {
            System.out.println("Invalid inputs. Please try again.");
            return;
        }

        // Password Generation
        String password = generatePassword(passwordLength, includeUppercase, includeLowercase,
                includeNumbers, includeSpecialChars);

        // Display Result
        System.out.println("Generated Password: " + password);
    }

    public static String generatePassword(int length, boolean includeUppercase, boolean includeLowercase,
                                          boolean includeNumbers, boolean includeSpecialChars) {
        StringBuilder password = new StringBuilder();
        String validChars = "";

        if (includeUppercase) {
            validChars += UPPERCASE_CHARS;
            password.append(getRandomChar(UPPERCASE_CHARS));
        }

        if (includeLowercase) {
            validChars += LOWERCASE_CHARS;
            password.append(getRandomChar(LOWERCASE_CHARS));
        }

        if (includeNumbers) {
            validChars += NUMBERS;
            password.append(getRandomChar(NUMBERS));
        }

        if (includeSpecialChars) {
            validChars += SPECIAL_CHARS;
            password.append(getRandomChar(SPECIAL_CHARS));
        }

        SecureRandom random = new SecureRandom();
        int remainingLength = length - password.length();

        for (int i = 0; i < remainingLength; i++) {
            password.append(getRandomChar(validChars));
        }

        // Shuffle the password to ensure random distribution of characters
        for (int i = password.length() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(j));
            password.setCharAt(j, temp);
        }

        return password.toString();
    }

    private static char getRandomChar(String charSet) {
        SecureRandom random = new SecureRandom();
        int randomIndex = random.nextInt(charSet.length());
        return charSet.charAt(randomIndex);
    }
}