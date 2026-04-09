public class User{
    String username;
    String password;
    String cellNumber;

    public User(String username, String password ,String cellNumber){
        this.username=username;
        this.password= password;
        this.cellNumber=cellNumber;

    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getCellNumber(){
        return this.cellNumber;
    }
}