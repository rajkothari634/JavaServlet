import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.*;
import java.sql.ResultSet;

public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        System.out.println("entered in update");
        PrintWriter out = res.getWriter();
        HttpSession ses = req.getSession();
        String q = req.getParameter("q");
        try {
        if(ses != null && q!= null && q.equals("setdata"))//condition of ses and from q = set data
        {
        	System.out.println("entered in update");
        	String name = (String)req.getParameter("name");
        	System.out.println("entered in update");
        	String email =  (String)req.getParameter("email");
        	System.out.println("entered in update");
        	String phone = (String)req.getParameter("phone");
        	System.out.println("entered in update");
        	String state = (String)req.getParameter("state");
        	System.out.println("entered in update");
        	String roll = (String)req.getParameter("roll");
        	System.out.println("entered in update");
        	String branch = (String)req.getParameter("branch");
        	System.out.println(name + email + phone);
        	System.out.println("entered in update");
        	if(name.equals("") || email.equals("")) {
        		out.println("Fill all data");
        	}else {
        		System.out.println("all set for update");
        		String username = (String) ses.getAttribute("username");
            	String useremail = (String) ses.getAttribute("useremail");
        		Connection con = connection.initializeDatabase();
        		PreparedStatement ps = con.prepareStatement("update hello set name= ? ,email= ? ,roll= ? ,branch= ? ,state= ? ,phone= ?  WHERE name=? and email=?");
        		ps.setString(1,name);
        		ps.setString(2,email)
        		;ps.setString(3,phone);
        		ps.setString(4,state);
        		ps.setString(5,roll);
        		ps.setString(6,branch);
        		ps.setString(7,username);
        		ps.setString(8,useremail);
        		ps.executeUpdate();
        		System.out.println("updated");
        		ses.setAttribute("username", name);
        		ses.setAttribute("useremail", email);
        		out.println("Data Inserted");
        	}
        }else
        {
	        String name = req.getParameter("name");
	        String email = req.getParameter("email");
	        String pass = req.getParameter("pass");
	        
	        if(name.equals("") || email.equals("") || pass.equals(""))
	        {
	        	RequestDispatcher rs = req.getRequestDispatcher("index.html");
	            rs.include( req, res);
	            out.println("<script>alert('Fill all inputs');</script>");
	        }
	        else
	        {
	        	Connection con = connection.initializeDatabase();
	        	 PreparedStatement ps = con.prepareStatement("insert into hello (name, email , pass) values (?,?,?)");
	             ps.setString(1, name);
	             ps.setString(2, email);
	             ps.setString(3, pass);
	             ps.executeUpdate();
	             RequestDispatcher rs = req.getRequestDispatcher("index.html");
	             out.println("<script>alert('Registration Completed. Login to see your profile');</script>");
	             rs.include( req, res);
	        }
	        
        }
        }catch(Exception e) {
        	System.out.println(e);
        }
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        try {
        Connection con = connection.initializeDatabase();
        PreparedStatement ps = con.prepareStatement("select * from hello where email=? and name=?");
        HttpSession ses = req.getSession();
        if(ses != null) {
        	String name = (String) ses.getAttribute("username");
        	String email = (String) ses.getAttribute("useremail");
        	ps.setString(1, email);
            ps.setString(2, name);
            ResultSet k = ps.executeQuery();
            if( k.next()){
             String username = k.getString("name");
             String useremail = k.getString("email");
             String userphone = k.getString("phone");
             String userroll = k.getString("roll");
             String userbranch = k.getString("branch");
             String userstate = k.getString("state");
             out.println(username + "~" + useremail + "~" + userroll + "~" + userbranch + "~" + userstate + "~" + userphone); 
            }else {
                out.println("<script>alert('Username or password is incorrect');</script>");
            }
        }else {
        	out.println("Need to do login");
        }
        }catch(Exception e) {
        	System.out.println(e);
        }
	}
}
