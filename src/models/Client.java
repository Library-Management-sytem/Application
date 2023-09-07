package models;

import java.util.List;

public class Client {

    private Integer Id;
    private String Name;
    private String Email;
    private List<Client> clients;
    private Service service;
    public Client(){}

    public Client(String name, String email, Service service) {
        this.Name = name;
        this.Email = email;
        this.service = service;
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

    public List<Client> getClients() {return this.clients;}

    public void setClients(List<Client> clients) {this.clients = clients;}

    public Service getService() {return service;}

    public void setService(Service service) {this.service = service;}

}
