import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    		throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        HttpSession ses =  req.getSession();
        ses.invalidate();
        if(ses!=null) {
        	System.out.println("logout is not working");
        }
        out.println("Successfully logged out!");
        
    }

}
