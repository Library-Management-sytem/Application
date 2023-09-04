package main;

public class Client {

    private Integer Id;
    private String Name;
    private String Email;
    private static Client instance;

    private Client(String name, String email){
        this.Name = name;
        this.Email = email;
    }
    public static Client getInstance(String name, String email) {
        if (instance == null)
            instance = new Client(name, email);
        return instance;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        this.Name = name;
    }


    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        this.Email = email;
    }

}
