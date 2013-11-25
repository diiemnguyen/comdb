package com.comdb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet(name = "AdminHome", urlPatterns = { "/AdminHome" })
public class AdminAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(AdminAddServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
            
		String email = request.getParameter("email");	
		String name = request.getParameter("name");
		
		String errorMsg = null;
		if(email == null || email.equals("")){
			errorMsg = "Customer Email ID can't be null or empty.";
		}
		
		if(name == null || name.equals("")){
			errorMsg = "Customer Name can't be null or empty.";
		}
				
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminHome.jsp");
			PrintWriter out= response.getWriter();  
			rd.include(request, response);
                        out.println("<font color=red>"+errorMsg+"</font>");
		}else{
		
                        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                        PreparedStatement ps = null;
                        try {
                                ps = con.prepareStatement("insert into customer(cust_Name, cust_Email) values (?,?)");
                                ps.setString(1, name);
                                ps.setString(2, email);
                                ps.execute();

                                logger.info("Addmin add user = "+email);
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminHome.jsp");
                                PrintWriter out= response.getWriter();
                                rd.include(request, response);
                                out.println("<br><font color=green>Admin adds a new user " + email + " successfully.</font>");
                        } catch (SQLException e) {
                                e.printStackTrace();
                                logger.error("Database connection problem");
                                throw new ServletException("DB Connection problem.");
                        }finally{
                                try {
                                        ps.close();
                                } catch (SQLException e) {
                                        logger.error("SQLException in closing PreparedStatement");
                                }
                        }
		}
		
	}

}
