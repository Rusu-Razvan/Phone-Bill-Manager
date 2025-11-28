package mainpackage.model;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Users {
    private List<Seller> sellers;
    private List<Program> programs;

    public Admin(String username, String password, String name, String surname) {
        super(username, password, name, surname, "admin");
        this.sellers = new ArrayList<>();
        this.programs = new ArrayList<>();
    }

    public Seller createSeller(String username, String password, String name, String surname) {
        Seller newSeller = new Seller(username, password, name, surname, "seller");
        sellers.add(newSeller);
        System.out.println("Seller created: " + username);
        return newSeller;
    }

    
    public void deleteSeller(String username) {
        boolean removed = sellers.removeIf(seller -> seller.getUsername().equals(username));
        if (removed) {
            System.out.println("Seller deleted: " + username);
        } else {
            System.out.println("Seller not found: " + username);
        }
    }

    
    public Program createProgram(int voiceTime, int sms, int data, float price, int extraVoice, int extraSms, int extraData) {
        Program newProgram = new Program(voiceTime, sms, data, price, extraVoice, extraSms, extraData);
        programs.add(newProgram);
        System.out.println("New program created: " + price + " EUR/month");
        return newProgram;
    }

    
    public void viewSellers() {
        System.out.println("List of Sellers:");
        for (Seller seller : sellers) {
            System.out.println("- " + seller.getUsername() + " (" + seller.getName() + " " + seller.getSurname() + ")");
        }
    }

 
    public void viewPrograms() {
        System.out.println("Available Programs:");
        for (Program p : programs) {
            System.out.println("- " + p.getPrice() + " EUR/month: " + p.getVoiceTime() + " min, " + p.getSms() + " SMS, " + p.getData() + " GB");
        }
    }
}
