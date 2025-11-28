
package mainpackage.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter({"/admin/*", "/seller/*", "/client/*", "/AdminHome.jsp", "/SellerHome.jsp", "/ClientHome.jsp",
	"/InsertCustomer.jsp", "/LinkCustomerProgram.jsp", "/ViewProgram.jsp"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest  request  = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // 1) Prevent browser caching of every protected page
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
        response.setHeader("Pragma", "no-cache");                                   
        response.setDateHeader("Expires", 0);                                        

        // 2) Check for a valid session + role
        HttpSession session = request.getSession(false);
        String role = (session != null) 
                    ? (String) session.getAttribute("role")
                    : null;

        // 3) If not logged in, send to login page
        if (role == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // 4) If wrong-role for the current path, reject
        String path = request.getServletPath();
        if (path.startsWith("/Admin")  && !"admin".equals(role)
         || path.startsWith("/Seller") && !"seller".equals(role)
         || path.startsWith("/Client") && !"client".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=norole");
            return;
        }

        // 5) Everything’s OK: continue down the chain
        chain.doFilter(request, response);
    }
}
