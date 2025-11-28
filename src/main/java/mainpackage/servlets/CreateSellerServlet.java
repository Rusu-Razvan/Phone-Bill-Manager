package mainpackage.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

import mainpackage.dao.UserDAO;
import mainpackage.dao.SellerDAO;
import mainpackage.model.Users;
import mainpackage.util.PasswordUtil;

@WebServlet("/createSeller")
public class CreateSellerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDAO   userDAO   = new UserDAO();
    private final SellerDAO sellerDAO = new SellerDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.getRequestDispatcher("/CreateSeller.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name     = req.getParameter("name");
        String surname  = req.getParameter("surname");

        
        if (username == null || password == null || name == null || surname == null ||
            username.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty()) {
            req.setAttribute("error", "All fields are required.");
            doGet(req, resp);
            return;
        }

        try {
            
            String salt   = PasswordUtil.generateSalt();
            String hashed = PasswordUtil.hash(password, salt);

           
            Users u = new Users();
            u.setUsername(username);
            u.setSalt(salt);
            u.setPasswordHash(hashed);
            u.setName(name);
            u.setSurname(surname);
            u.setRole("seller");

            
            userDAO.insert(u);
            sellerDAO.insert(username);

            
            resp.sendRedirect(req.getContextPath()
                + "/createSeller?success=Seller+created");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Database error: " + e.getMessage());
            doGet(req, resp);
        }
    }
}
