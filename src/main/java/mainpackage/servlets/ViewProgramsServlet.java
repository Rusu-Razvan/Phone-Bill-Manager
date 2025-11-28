package mainpackage.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import mainpackage.model.Program;
import mainpackage.service.SellerService;

@WebServlet("/ViewProgramsServlet")
public class ViewProgramsServlet extends HttpServlet {

    private final SellerService service = new SellerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Program> list = service.listPrograms();
            req.setAttribute("programs", list);
            req.getRequestDispatcher("ViewPrograms.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
