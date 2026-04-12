import java.util.Scanner;

public class Main {

    static String registeredUsername;
    static String registeredPassword;
    static String registeredCell;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // REGISTRATION
        System.out.println("=== USER REGISTRATION ===");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter cell number (+27...): ");
        String cell = scanner.nextLine();

        System.out.println(registerUser(username, password, cell));

        // LOGIN
        System.out.println("\n=== USER LOGIN ===");

        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();

        boolean loginSuccess = loginUser(loginUsername, loginPassword);
        System.out.println(returnLoginStatus(loginSuccess));

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

    public static boolean loginUser(String username, String password) {
        return username.equals(registeredUsername) && password.equals(registeredPassword);
    }

    public static String returnLoginStatus(boolean status) {
        if (status) {
            return "Welcome, it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}