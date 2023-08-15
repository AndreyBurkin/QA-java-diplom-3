package pens;


//Сериализация для работы с пользователем
public class PensUserAPI {

    private String email;
    private String password;
    private String name;


    public PensUserAPI(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public PensUserAPI(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public PensUserAPI() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}