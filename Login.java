import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

@SuppressWarnings("serial")
public class Login extends HttpServlet {
 
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        
        
        try{
            Connection con = connection.initializeDatabase();
             PreparedStatement st = con 
                   .prepareStatement("select * from hello where email=? and pass=?"); 
             st.setString(1, email);
             st.setString(2, pass);
             ResultSet k = st.executeQuery();
            if( k.next()){
             String username = k.getString("name");
             String useremail = k.getString("email");
             HttpSession ses = req.getSession();
             ses.setAttribute("username", username);
             ses.setAttribute("useremail", useremail);
             RequestDispatcher rs = req.getRequestDispatcher("UserProfile.html");
             rs.include( req, res); 
            }else {
            	  
                RequestDispatcher rs = req.getRequestDispatcher("index.html");
                 out.println("<script>alert('Username or password is incorrect');</script>");
                rs.include( req, res);
            }
             
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }  
    }
   
    }

