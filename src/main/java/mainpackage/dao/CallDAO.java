package mainpackage.dao;

import mainpackage.model.DBConnection;
import mainpackage.model.Call;
import java.sql.*;
import java.time.YearMonth;
import java.time.LocalDateTime;
import java.util.*;

public class CallDAO {

    
    public List<Call> findByPhoneAndPeriod(String number, YearMonth period) throws SQLException {
        String sql = "SELECT startTime, endTime, TIMESTAMPDIFF(SECOND, startTime, endTime) AS duration "
                   + "FROM `call` "
                   + "WHERE phoneNumber = ? "
                   + "  AND YEAR(startTime) = ? AND MONTH(startTime) = ?";
        List<Call> calls = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, number);
            ps.setInt(2, period.getYear());
            ps.setInt(3, period.getMonthValue());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int duration = rs.getInt("duration");
                    LocalDateTime start = rs.getTimestamp("startTime").toLocalDateTime();
                    LocalDateTime end   = rs.getTimestamp("endTime").toLocalDateTime();
                    calls.add(new Call(duration, start, end));
                }
            }
        }
        return calls;
    }
    
    
    public List<Call> findByPhoneNumber(String phoneNumber) throws SQLException {
        String sql =
            "SELECT id, duration, startTime, endTime, phoneNumber " +
            "FROM `call` " +                
            "WHERE phoneNumber = ? " +      
            "ORDER BY startTime DESC";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            
            ps.setString(1, phoneNumber);

            try (ResultSet rs = ps.executeQuery()) {
                List<Call> list = new ArrayList<>();
                while (rs.next()) {
                    Call call = new Call();
                    call.setId(rs.getInt("id"));
                    call.setDuration(rs.getFloat("duration"));
                    call.setStartTime(rs.getTimestamp("startTime")
                                        .toLocalDateTime());
                    call.setEndTime(rs.getTimestamp("endTime")
                                      .toLocalDateTime());
                    call.setPhoneNumber(rs.getString("phoneNumber"));
                    list.add(call);
                }
                return list;
            }
        }
    }
    
}
