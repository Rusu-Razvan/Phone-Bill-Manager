package mainpackage.dao;

import mainpackage.model.DBConnection;

import java.sql.*;

public class ClientDAO {

    
    public void insert(String username, String vatNumber) throws SQLException {
        String sql = "INSERT INTO client(username, VATNumber) VALUES(?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, vatNumber);
            ps.executeUpdate();
        }
    }
}
