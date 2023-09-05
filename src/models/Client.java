package models;

import java.util.List;

public class Client {

    private Integer Id;
    private String Name;
    private String Email;
    private List<Client> clients;

    public Client(String name, String email){
        this.Name = name;
        this.Email = email;
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
