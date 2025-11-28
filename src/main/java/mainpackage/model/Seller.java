package mainpackage.model;

import java.util.ArrayList;
import java.util.List;

public class Seller extends Users {
    private List<Client> clients; 

    public Seller(String username, String password, String name, String surname, String role) {
        super(username, password, name, surname, role);
        this.clients = new ArrayList<>();
    }

   
    public Client registerCustomer(String username, String password, String name, String surname, 
                                 String vatNumber, PhoneNumber phoneNumber) {
        Client newClient = new Client(username, password, name, surname, "client", vatNumber, phoneNumber);
        clients.add(newClient);
        System.out.println("New customer registered: " + name + " " + surname);
        return newClient;
    }

    
    public void issueCustomerAccount(String username) {
        for (Client client : clients) {
            if (client.getUsername().equals(username)) {
                System.out.println("Customer Details:");
                System.out.println("Name: " + client.getName() + " " + client.getSurname());
                System.out.println("Phone: " + client.getPhoneNumber().getNumber());
                System.out.println("VAT Number: " + client.getVatNumber());
                System.out.println("Total Bills: " + client.getBills().size());
                return;
            }
        }
        System.out.println("Customer not found!");
    }

    
    public void changeCustomerProgram(String username, Program newProgram) {
        for (Client client : clients) {
            if (client.getUsername().equals(username)) {
                client.getPhoneNumber().setProgram(newProgram);
                System.out.println("Program updated for customer: " + client.getUsername());
                return;
            }
        }
        System.out.println("Customer not found!");
    }

    public List<Client> getClients() {
        return clients;
    }
}
