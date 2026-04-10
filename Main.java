import java.util.Scanner;

public class Main {

    // store registered user details
    static String registeredUsername;
    static String registeredPassword;
    static String registeredCell;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=== USER REGISTRATION ===");

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter cell number (+27...): ");
        String cell = input.nextLine();

        // register user and show result
        System.out.println(registerUser(username, password, cell));

        System.out.println("User program finished");

        input.close();
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

        // store data
        registeredUsername = username;
        registeredPassword = password;
        registeredCell = cell;

        return "User successfully registered";
    }
}
