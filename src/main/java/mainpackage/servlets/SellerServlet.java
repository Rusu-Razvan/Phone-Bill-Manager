package mainpackage.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.List;

import mainpackage.model.*;
import mainpackage.service.SellerService;

@WebServlet("/SellerServlet")
public class SellerServlet extends HttpServlet {
    private final SellerService service = new SellerService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession(false);
        
        if (session == null || !"seller".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try {
            switch (action) {
                case "registerCustomer":
                    registerCustomer(req, resp);
                    break;
                case "linkCustomerProgram":
                    linkCustomerProgram(req, resp);
                    break;
                case "changeCustomerProgram":
                    changeCustomerProgram(req, resp);
                    break;
                case "issueBill":
                    issueBill(req, resp);
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void registerCustomer(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        service.addCustomer(
            req.getParameter("newUsername"),
            req.getParameter("newPassword"),
            req.getParameter("name"),
            req.getParameter("surname"),
            req.getParameter("vatNumber")
        );
        resp.sendRedirect("SellerHome.jsp?msg=Customer+added+successfully");
    }

    private void linkCustomerProgram(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        service.linkPhone(
            req.getParameter("phoneNumber"),
            req.getParameter("clientUsername"),
            Integer.parseInt(req.getParameter("programId"))
        );
        resp.sendRedirect("SellerHome.jsp?msg=Phone+linked");
    }

    private void changeCustomerProgram(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        service.changePhoneProgram(
            req.getParameter("phoneNumber"),
            Integer.parseInt(req.getParameter("newProgramId"))
        );
        resp.sendRedirect("SellerHome.jsp?msg=Program+changed");
    }

    private void issueBill(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        
        YearMonth period;
        try {
            period = YearMonth.parse(req.getParameter("billingPeriod"));
        } catch (DateTimeParseException e) {
            resp.sendRedirect("IssueBill.jsp?error=Invalid+period+format.+Use+YYYY-MM");
            return;
        }

        String phoneNumber = req.getParameter("phoneNumber");

        
        try {
            
            PhoneNumber pn = service.getClientPhone(phoneNumber);
            if (pn == null) {
                resp.sendRedirect("IssueBill.jsp?error=Phone+number+not+found");
                return;
            }

            
            List<Call> calls = service.getCallsForPeriod(phoneNumber, period);
            if (calls.isEmpty()) {
                resp.sendRedirect("IssueBill.jsp?error=No+calls+for+that+period");
                return;
            }

            
            Bill bill = new Bill(period, pn);
            calls.forEach(bill::addCall);

            
            double total = service.calculateAmount(bill);
            bill.setAmount(total);

            
            service.issueBill(bill);

            
            resp.sendRedirect("SellerHome.jsp?msg=Bill+issued+successfully");

        } catch (SQLException ex) {
           
            throw new ServletException("Database error issuing bill", ex);
        }
    }


}
