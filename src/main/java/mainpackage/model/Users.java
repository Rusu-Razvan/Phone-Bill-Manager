package mainpackage.model;
import mainpackage.util.PasswordUtil;

public class Users {
    private String username;
    private String salt;
    private String passwordHash;
    private String name;
    private String surname;
    private String role;  

    
    public Users() {}

    public Users(String username, String password, String name, String surname, String role) {
		this.username = username;
		this.salt = PasswordUtil.generateSalt();
		this.passwordHash = PasswordUtil.hash(password, this.salt);
		this.name = name;
		this.surname = surname;
		this.role = role;
	}
    
    public String getUsername() { return username; }
    public void setUsername(String u) { username = u; }

    public String getSalt() { return salt; }
    public void setSalt(String s) { salt = s; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String h) { passwordHash = h; }

    public String getName() { return name; }
    public void setName(String n) { name = n; }

    public String getSurname() { return surname; }
    public void setSurname(String s) { surname = s; }

    public String getRole() { return role; }
    public void setRole(String r) { role = r; }
}
