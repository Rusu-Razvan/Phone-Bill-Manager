package mainpackage.dao;

import mainpackage.model.DBConnection;
import java.sql.*;
import mainpackage.model.PhoneNumber;
import java.util.ArrayList;
import java.util.List;

public class PhoneNumberDAO {

    public void link(String number, String clientUsername, int programId) throws SQLException {
        String sql = "INSERT INTO phonenumber(number, client_username, program_id) VALUES(?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, number);
            ps.setString(2, clientUsername);
            ps.setInt(3, programId);
            ps.executeUpdate();
        }
    }


    public void updateProgram(String number, int programId) throws SQLException {
        String sql = "UPDATE phonenumber SET program_id = ? WHERE number = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, programId);
            ps.setString(2, number);
            ps.executeUpdate();
        }
    }
    
    public PhoneNumber findByNumber(String number) throws SQLException {
     
    	
    			String sql = "SELECT * FROM phonenumber WHERE number = ?";
		try (Connection c = DBConnection.getConnection();
			 PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, number);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				PhoneNumber phone = new PhoneNumber();
				phone.setNumber(rs.getString("number"));
				
				phone.setProgram(new ProgramDAO().findById(rs.getInt("program_id")));
				return phone;
			}
		}
		return null; 
    }
    
    public List<PhoneNumber> findByClientUsername(String clientUsername) throws SQLException {
        String sql = "SELECT * FROM phonenumber WHERE client_username = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, clientUsername);
            try (ResultSet rs = ps.executeQuery()) {
                List<PhoneNumber> list = new ArrayList<>();
                ProgramDAO programDao = new ProgramDAO();
                while (rs.next()) {
                    PhoneNumber phone = new PhoneNumber();
                    phone.setNumber(rs.getString("number"));
                    phone.setProgram(programDao.findById(rs.getInt("program_id")));
                    list.add(phone);
                }
                return list;
            }
        }
    }
    
}
