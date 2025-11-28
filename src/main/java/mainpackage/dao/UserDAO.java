package mainpackage.dao;

import mainpackage.util.PasswordUtil;
import mainpackage.model.DBConnection;
import mainpackage.model.Users;

import java.sql.*;

public class UserDAO {

	public Users authenticate(String username, String password) {
        String sql = "SELECT salt, password_hash, name, surname, role "
                   + "FROM users WHERE username = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                String salt       = rs.getString("salt");
                String hashStored = rs.getString("password_hash");
                String hashInput  = PasswordUtil.hash(password, salt);
                if (!hashStored.equals(hashInput)) return null;

                Users u = new Users();
                u.setUsername(username);
                u.setSalt(salt);
                u.setPasswordHash(hashStored);
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setRole(rs.getString("role"));
                return u;
            }
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }
	
    public Users findByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? map(rs) : null;
        }
    }

    public void insert(Users u) throws SQLException {
        String sql = 
          "INSERT INTO users(username, salt, password_hash, name, surname, role) " +
          "VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getSalt());          
            ps.setString(3, u.getPasswordHash());  
            ps.setString(4, u.getName());
            ps.setString(5, u.getSurname());
            ps.setString(6, u.getRole());
            ps.executeUpdate();
        }
    }


    
    private Users map(ResultSet rs) throws SQLException {
        return new Users(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("role")
        );
    }
}
