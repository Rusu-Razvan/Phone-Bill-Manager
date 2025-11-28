package mainpackage.servlets;

import mainpackage.dao.BillDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/payBill")
public class PayBillsServlet extends HttpServlet {
    private final BillDAO billDao = new BillDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"client".equals(session.getAttribute("role"))) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try {
            int    billId     = Integer.parseInt(req.getParameter("billId"));
            double paidAmount = Double.parseDouble(req.getParameter("paidAmount"));
            billDao.markAsPaid(billId, paidAmount);
            
            resp.sendRedirect(req.getContextPath() + "/viewBills");
        } catch (Exception e) {
            throw new ServletException("Unable to process payment", e);
        }
    }
}
