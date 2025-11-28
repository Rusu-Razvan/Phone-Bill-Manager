
package mainpackage.model;

import java.time.YearMonth;
import java.util.List;
import java.time.Duration;
import mainpackage.model.Call;
import java.util.ArrayList;

public class Bill {
    private int        id;
    private YearMonth  month;
    private PhoneNumber phoneNumber;
    private double     amount;
    private boolean    isPaid;
    private double     paidAmount;
    private List<Call> calls;
 
    public Bill() { }

   
    public Bill(YearMonth month, PhoneNumber phoneNumber) {
        this.month       = month;
        this.phoneNumber = phoneNumber;
        this.amount      = 0.0;
        this.isPaid      = false;
        this.paidAmount  = 0.0;
    }

    
    public int getId()                         { return id; }
    public void setId(int id)                  { this.id = id; }

    public YearMonth getMonth()                { return month; }
    public void setMonth(YearMonth month)      { this.month = month; }

    public PhoneNumber getPhoneNumber()        { return phoneNumber; }
    public void setPhoneNumber(PhoneNumber pn) { this.phoneNumber = pn; }

    public double getAmount()                  { return amount; }
    public void setAmount(double amount)       { this.amount = amount; }

    public boolean isPaid()                    { return isPaid; }
    public void setPaid(boolean paid)          { isPaid = paid; }

    public double getPaidAmount()              { return paidAmount; }
    public void setPaidAmount(double pa)       { paidAmount = pa; }
    
    public void addCall(Call c) {
        if (calls == null) calls = new ArrayList<>();
        calls.add(c);
    }
    
    public int getTotalCallDuration() {
        return calls.stream()
            .mapToInt(c -> (int) Duration.between(c.getStartTime(), c.getEndTime()).getSeconds())
            .sum();
    }
    
}
