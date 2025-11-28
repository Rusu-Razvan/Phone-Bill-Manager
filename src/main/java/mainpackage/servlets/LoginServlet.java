package mainpackage.servlets;

import mainpackage.dao.UserDAO;
import mainpackage.model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserDAO userDao = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Users user = userDao.authenticate(username, password);
        if (user == null) {
            resp.sendRedirect("login.jsp?error=credentials");
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());

        switch (user.getRole()) {
            case "admin":
                resp.sendRedirect("AdminHome.jsp");
                break;
            case "seller":
                resp.sendRedirect("SellerHome.jsp");
                break;
            case "client":
                resp.sendRedirect("ClientHome.jsp");
                break;
            default:
                resp.sendRedirect("login.jsp?error=norole");
        }
    }
}
