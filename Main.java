import java.util.Scanner;

public class Main {

    static String registeredUsername;
    static String registeredPassword;
    static String registeredCell;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== USER REGISTRATION ===");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter cell number (+27...): ");
        String cell = scanner.nextLine();

        System.out.println(registerUser(username, password, cell));

        System.out.println("User program finished");

        scanner.close();
    }

    public static String registerUser(String username, String password, String cell) {

        if (!Validation.checkUsername(username)) {
            return "Username is not correctly formatted";
        }

        if (!Validation.checkPassword(password)) {
            return "Password is not correctly formatted";
        }

        if (!Validation.checkCellPhone(cell)) {
            return "Cell phone number incorrectly formatted";
        }

        registeredUsername = username;
        registeredPassword = password;
        registeredCell = cell;

        return "User successfully registered";
    }
}