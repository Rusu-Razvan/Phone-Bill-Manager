package mainpackage.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import mainpackage.dao.ProgramDAO;
import mainpackage.model.Program;

@WebServlet("/updateProgram")
public class UpdateProgramServlet extends HttpServlet {
    private final ProgramDAO dao = new ProgramDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            
            List<Program> programs = dao.findAll();
            req.setAttribute("programs", programs);
            req.getRequestDispatcher("/UpdateProgram.jsp")
               .forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException("Unable to load programs", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            req.setAttribute("error", "Please select a program.");
            doGet(req, resp);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Program existing = dao.findById(id);
            if (existing == null) {
                req.setAttribute("error", "Selected program does not exist.");
                doGet(req, resp);
                return;
            }

            
            String vTimeStr = req.getParameter("voiceTime");
            int voiceTime = (vTimeStr == null || vTimeStr.isEmpty())
                ? existing.getVoiceTime()
                : Integer.parseInt(vTimeStr);

            String smsStr = req.getParameter("sms");
            int sms = (smsStr == null || smsStr.isEmpty())
                ? existing.getSms()
                : Integer.parseInt(smsStr);

            String dataStr = req.getParameter("data");
            int data = (dataStr == null || dataStr.isEmpty())
                ? existing.getData()
                : Integer.parseInt(dataStr);

            String priceStr = req.getParameter("price");
            float price = (priceStr == null || priceStr.isEmpty())
                ? existing.getPrice()
                : Float.parseFloat(priceStr);

            String evStr = req.getParameter("extraVoice");
            int extraVoice = (evStr == null || evStr.isEmpty())
                ? existing.getExtraVoice()
                : Integer.parseInt(evStr);

            String esStr = req.getParameter("extraSms");
            int extraSms = (esStr == null || esStr.isEmpty())
                ? existing.getExtraSms()
                : Integer.parseInt(esStr);

            String edStr = req.getParameter("extraData");
            int extraData = (edStr == null || edStr.isEmpty())
                ? existing.getExtraData()
                : Integer.parseInt(edStr);

            Program updated = new Program(
                id, voiceTime, sms, data, price, extraVoice, extraSms, extraData
            );
            dao.update(updated);

            
            resp.sendRedirect(req.getContextPath() + "/updateProgram?success=1");

        } catch (NumberFormatException ex) {
            req.setAttribute("error", "One or more numeric fields are invalid.");
            doGet(req, resp);
        } catch (SQLException ex) {
            req.setAttribute("error", "Database error: " + ex.getMessage());
            doGet(req, resp);
        }
    }
}
