import java.util.regex.*;

public class Validation{
  
  //Username:must contain"_" and be max 5 characters//
  public static boolean checkUsername(String username){
    return username.contains("_") && username.length() <= 5;
  }
  //Password:must contain at least 8 characters, a capital letter, a number and a special character//
   public static boolean checkPassword(String password){
    String pattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    return Pattern.matches(pattern, password);
   }
   //South African cell number:=+27 followed by 9 digits//
   public static boolean checkCellPhone(String number){
    String pattern = "^\\+27\\d{9}$";
    return Pattern.matches(pattern, number);
   }
}