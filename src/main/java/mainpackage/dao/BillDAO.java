package mainpackage.dao;

import mainpackage.model.DBConnection;
import mainpackage.model.Bill;
import java.sql.*;
import java.time.YearMonth;
import java.util.List;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;


public class BillDAO {

	private static final DateTimeFormatter ENGLISH_MONTH_FORMAT =
            DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
	
    
	public void insert(Bill bill) throws SQLException {
	    String sql = 
	      "INSERT INTO bill (`phoneNumber`, `month`, `amount`, `is_paid`) "
	    + "VALUES (?, ?, ?, false)";
	    try (Connection c = DBConnection.getConnection();
	         PreparedStatement ps = c.prepareStatement(sql)) {
	        ps.setString(1, bill.getPhoneNumber().getNumber());
	        ps.setString(2, bill.getMonth().toString());   // e.g. "2025-05"
	        ps.setDouble(3, bill.getAmount());
	        ps.executeUpdate();
	    }
	}

	public List<Bill> findByPhoneNumber(String phone) throws SQLException {
        String sql = "SELECT id, month, amount, is_paid, paid_amount "
                   + "FROM bill WHERE phoneNumber = ? ORDER BY month DESC";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                List<Bill> list = new ArrayList<>();
                while (rs.next()) {
                    Bill b = new Bill();
                    b.setId(rs.getInt("id"));

                    String monthStr = rs.getString("month");
                    YearMonth ym;
                    try {
                        
                        ym = YearMonth.parse(monthStr);
                    } catch (DateTimeParseException ex) {
                        
                        ym = YearMonth.parse(monthStr, ENGLISH_MONTH_FORMAT);
                    }
                    b.setMonth(ym);
                    

                    b.setAmount(rs.getDouble("amount"));
                    b.setPaid(rs.getBoolean("is_paid"));
                    b.setPaidAmount(rs.getDouble("paid_amount"));
                    list.add(b);
                }
                return list;
            }
        }
    }

	public void markAsPaid(int billId, double paidAmount) throws SQLException {
        String sql = 
            "UPDATE bill " +
            "SET is_paid = TRUE, paid_amount = ? " +
            "WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, paidAmount);
            ps.setInt   (2, billId);
            ps.executeUpdate();
        }
    }
	
}
