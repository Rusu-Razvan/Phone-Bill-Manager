package mainpackage.dao;

import mainpackage.model.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SellerDAO {
   
    public void insert(String username) throws SQLException {
        String sql = "INSERT INTO seller(username) VALUES(?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.executeUpdate();
        }
    }
}
