package mainpackage.servlets;

import mainpackage.dao.CallDAO;
import mainpackage.dao.PhoneNumberDAO;
import mainpackage.model.Call;
import mainpackage.model.PhoneNumber;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/viewCalls")
public class ViewCallsServlet extends HttpServlet {
    private final PhoneNumberDAO phoneDao = new PhoneNumberDAO();
    private final CallDAO       callDao  = new CallDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        
        if (session == null || !"client".equals(session.getAttribute("role"))) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        try {
            
            List<PhoneNumber> phones = phoneDao.findByClientUsername(username);

           
            Map<String, List<Call>> callsMap = new LinkedHashMap<>();
            for (PhoneNumber phone : phones) {
                callsMap.put(
                    phone.getNumber(),
                    callDao.findByPhoneNumber(phone.getNumber())
                );
            }

            
            req.setAttribute("callsMap", callsMap);
            req.getRequestDispatcher("/ViewCalls.jsp")
               .forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException("Error loading call history", e);
        }
    }
}
