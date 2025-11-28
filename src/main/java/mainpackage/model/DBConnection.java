package mainpackage.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String url = "jdbc:mysql://localhost:3306/web_project";
    private static final String user = "root";
    private static final String password = "student";
	
	 public static Connection getConnection() throws SQLException {
         return DriverManager.getConnection(url, user, password);
     }
	
    public static void main(String[] args) {
        

       
        
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL!");
            
            Statement stmt = conn.createStatement();
            
            ResultSet users = stmt.executeQuery("SELECT * FROM Users");
            System.out.println("\n Users:");
            while (users.next()) {
                String username = users.getString("username");
                String name = users.getString("name");
                String surname = users.getString("surname");
                System.out.printf("  - %s (%s %s)%n", username, name, surname);
            }
            
            conn.close();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }
}

