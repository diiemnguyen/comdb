package com.comdb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.comdb.util.User;




@WebServlet(name = "Login", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(LoginServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		String errorMsg = null;
		if(email == null || email.equals("")){
			errorMsg ="User Email can't be null or empty";
		}
                
		
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		}else{
		
                    Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                    PreparedStatement ps = null;
                    ResultSet rs = null;
                    
                    try {
                            ps = con.prepareStatement("select cust_ID, cust_Name, cust_Email from customer where cust_Email=? limit 1");
                            ps.setString(1, email);
                            rs = ps.executeQuery();

                            if(rs != null){
                                    rs.next();
                                    User user = new User(rs.getString("cust_Name"), rs.getString("cust_Email"), rs.getInt("cust_ID"));
                                    logger.info("User found with details="+user);
                                    HttpSession session = request.getSession();
                                    session.setAttribute("User", user);
                                    response.sendRedirect("home.jsp");
                            }else{
                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                                    PrintWriter out= response.getWriter();
                                    logger.error("User not found with email="+email);
                                    out.println("<font color=red>No user found with given email id, please register first.</font>");
                                    rd.include(request, response);
                            }
                            
                            
                            
                    } catch (SQLException e) {
                            e.printStackTrace();
                            logger.error("Database connection problem");
                            throw new ServletException("DB Connection problem.");
                    }finally{
                            try {
                                    rs.close();
                                    ps.close();
                            } catch (SQLException e) {
                                    logger.error("SQLException in closing PreparedStatement or ResultSet");;
                            }

                    }
                    
                    
                    
                                       
                    
                    
		}
	}

        
        
        
        
        
}
