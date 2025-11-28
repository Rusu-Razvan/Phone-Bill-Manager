package mainpackage.model;

import java.util.ArrayList;
import java.util.List;

public class Client extends Users {
    private String vatNumber;
    private PhoneNumber phoneNumber;
    private List<Bill> bills; 

    public Client(String username, String password, String name, String surname, String role, 
                  String vatNumber, PhoneNumber phoneNumber) {
        super(username, password, name, surname, role);
        this.vatNumber = vatNumber;
        this.phoneNumber = phoneNumber;
        this.bills = new ArrayList<>();
    }

    
    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Bill> getBills() {
        return bills;
    }

    
    public void addBill(Bill bill) {
        bills.add(bill);
        System.out.println("Bill added for client: " + getUsername());
    }

    
    public void viewBill(int index) {
        if (index >= 0 && index < bills.size()) {
            System.out.println(bills.get(index));
        } else {
            System.out.println("Invalid bill index!");
        }
    }

    
    public void viewAllBills() {
        if (bills.isEmpty()) {
            System.out.println("No bills found for client: " + getUsername());
            return;
        }
        System.out.println("Bills for client: " + getUsername());
        for (int i = 0; i < bills.size(); i++) {
            System.out.println("Bill #" + (i + 1) + ": " + bills.get(i));
        }
    }

    
    public void payBill(int index) {
        if (index >= 0 && index < bills.size()) {
            bills.get(index).setPaid(true);
            System.out.println("Bill #" + (index + 1) + " paid for client: " + getUsername());
        } else {
            System.out.println("Invalid bill index!");
        }
    }
}
