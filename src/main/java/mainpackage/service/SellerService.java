package mainpackage.service;

import java.sql.SQLException;
import java.time.YearMonth;
import java.util.List;
import mainpackage.util.PasswordUtil;

import mainpackage.dao.*;
import mainpackage.model.*;

public class SellerService {

    private final UserDAO userDB         = new UserDAO();
    private final ClientDAO clientDB     = new ClientDAO();
    private final ProgramDAO programDB   = new ProgramDAO();
    private final PhoneNumberDAO phoneDB = new PhoneNumberDAO();
    private final CallDAO callDB         = new CallDAO();
    private final BillDAO billDB         = new BillDAO();

    
    public Users login(String username, String password) throws SQLException {
        Users u = userDB.findByUsernameAndPassword(username, password);
        return (u != null && "seller".equalsIgnoreCase(u.getRole())) ? u : null;
    }

   
    public void addCustomer(String username, String rawPassword,
                            String name, String surname,
                            String vatNumber) throws SQLException {
        
        Users u = new Users();
        u.setUsername(username);
        u.setName(name);
        u.setSurname(surname);
        u.setRole("client");

        
        String salt = PasswordUtil.generateSalt();
        u.setSalt(salt);
        String hash = PasswordUtil.hash(rawPassword, salt);
        u.setPasswordHash(hash);

        
        userDB.insert(u);
        clientDB.insert(username, vatNumber);
    }

    
    public void linkPhone(String number, String clientUsername, int programId) throws SQLException {
        phoneDB.link(number, clientUsername, programId);
    }

    
    public void changePhoneProgram(String number, int newProgramId) throws SQLException {
        phoneDB.updateProgram(number, newProgramId);
    }

    
    public List<Program> listPrograms() throws SQLException {
        return programDB.findAll();
    }

    
    public List<Call> getCallsForPeriod(String number, YearMonth period) throws SQLException {
        return callDB.findByPhoneAndPeriod(number, period);
    }

    
    public double calculateAmount(Bill bill) {
        Program p = bill.getPhoneNumber().getProgram();
        
        double total = p.getPrice();
        
        int usedSeconds = bill.getTotalCallDuration();            
        int includedSeconds = p.getVoiceTime() * 60;
        if (usedSeconds > includedSeconds) {
            int extra = usedSeconds - includedSeconds;
            total += extra * p.getExtraVoice();
        }
        return total;
    }

    
    public void issueBill(Bill bill) throws SQLException {
        billDB.insert(bill);
    }

	public PhoneNumber getClientPhone(String phone) {
		try {
			return phoneDB.findByNumber(phone);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
