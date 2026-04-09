import java.util.Scanner;

public class Main{
    public static void main(String[] args){

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("===Day1:USER VALIDATION SYSTEM===");
            
            //USER INPUT
            System.out.print("Enter username: ");
            String username = input.nextLine();

            System.out.print("Enter password: ");
            String password = input.nextLine();

            System.out.println("Enter cell number (+27 followed by 9 digits): ");
            String cell = input.nextLine();

            //Username check// 
            
            if (Validation.checkUsername(username)){
                System.out.println("Username successfully captured");
            }else{
                System.out.println("Username is not correctly formatted (min 5 chars, only letters and numbers)");
            }

            //Password check//
            if (Validation.checkPassword(password)){
                System.out.println("Password successfully captured");
            }else{
                System.out.println("Password is not correctly formatted (min 8 chars, 1 capital letter, 1 number, 1 special char)");
            }
           // cell phone number check//
            if (Validation.checkCellPhone(cell)){
                System.out.println("Cell number successfully captured");
            }else{
                System.out.println("Cell number is not correctly formatted (must start with +27 followed by 9 digits)");
            }
        }
    }
}