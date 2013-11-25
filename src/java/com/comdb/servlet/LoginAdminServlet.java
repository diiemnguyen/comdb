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




@WebServlet(name = "AdminLogin", urlPatterns = { "/AdminLogin" })
public class LoginAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(LoginServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String ad_email = request.getParameter("admin_email");
		
		String errorMsg = null;
		if(ad_email == null || ad_email.equals("")){
			errorMsg ="Admin Email can't be null or empty";
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
                            /** check admin user **/
                            ps = con.prepareStatement("select id, admin_Name, admin_Email from adminuser where admin_Email=? limit 1");
                            ps.setString(1, ad_email);
                            rs = ps.executeQuery();


                            if(rs != null){
                                    rs.next();
                                    User user = new User(rs.getString("admin_Name"), rs.getString("admin_Email"), rs.getInt("id"));
                                    logger.info("Admin found with details="+user);
                                    HttpSession session = request.getSession();
                                    session.setAttribute("User", user);
                                    response.sendRedirect("adminHome.jsp");
                            }else{
                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                                    PrintWriter out= response.getWriter();
                                    logger.error("Admin not found with email=" + ad_email);
                                    out.println("<font color=red>No admin found with given email id.</font>");
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




