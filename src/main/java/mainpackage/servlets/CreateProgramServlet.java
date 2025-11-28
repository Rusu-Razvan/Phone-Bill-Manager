package mainpackage.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

import mainpackage.dao.ProgramDAO;
import mainpackage.model.Program;

@WebServlet("/createProgram")
public class CreateProgramServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProgramDAO programDAO = new ProgramDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/CreateProgram.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       
        String vTime    = req.getParameter("voiceTime");
        String sms      = req.getParameter("sms");
        String data     = req.getParameter("data");
        String price    = req.getParameter("price");
        String eVoice   = req.getParameter("extraVoice");
        String eSms     = req.getParameter("extraSms");
        String eData    = req.getParameter("extraData");

       
        if (vTime==null||sms==null||data==null||price==null
         ||eVoice==null||eSms==null||eData==null
         ||vTime.isEmpty()||sms.isEmpty()||data.isEmpty()
         ||price.isEmpty()||eVoice.isEmpty()
         ||eSms.isEmpty()||eData.isEmpty()) {

            req.setAttribute("error", "All fields are mandatory.");
            doGet(req, resp);
            return;
        }

        try {
            
            int    iv  = Integer.parseInt(vTime);
            int    is  = Integer.parseInt(sms);
            int    id  = Integer.parseInt(data);
            float  pr  = Float.parseFloat(price);
            int    ev  = Integer.parseInt(eVoice);
            int    es  = Integer.parseInt(eSms);
            int    ed  = Integer.parseInt(eData);

            
            Program p = new Program(iv, is, id, pr, ev, es, ed);

           
            programDAO.insert(p);

            
            resp.sendRedirect(req.getContextPath() 
                + "/createProgram?success=Program+created+successfully");
        } catch(NumberFormatException ex) {
            req.setAttribute("error", "Numeric field invalid.");
            doGet(req, resp);
        } catch(SQLException ex) {
            req.setAttribute("error", "Eroare BD: " + ex.getMessage());
            doGet(req, resp);
        }
    }
}
