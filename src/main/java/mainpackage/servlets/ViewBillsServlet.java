package mainpackage.servlets;

import mainpackage.dao.BillDAO;
import mainpackage.dao.PhoneNumberDAO;
import mainpackage.model.Bill;
import mainpackage.model.PhoneNumber;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/viewBills")
public class ViewBillsServlet extends HttpServlet {
    private final PhoneNumberDAO phoneDao = new PhoneNumberDAO();
    private final BillDAO billDao = new BillDAO();

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

            
            Map<String, List<Bill>> billsMap = new LinkedHashMap<>();
            for (PhoneNumber phone : phones) {
                billsMap.put(
                    phone.getNumber(),
                    billDao.findByPhoneNumber(phone.getNumber())
                );
            }

           
            req.setAttribute("billsMap", billsMap);
            req.getRequestDispatcher("/ViewBills.jsp")
               .forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException("Eroare la încărcarea facturilor.", e);
        }
    }
}
