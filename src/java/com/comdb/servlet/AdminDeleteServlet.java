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

@WebServlet(name = "AdminDeleteHome", urlPatterns = { "/AdminDeleteHome" })
public class AdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(AdminDeleteServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
            
		String email = request.getParameter("email");	
		
		String errorMsg = null;
		if(email == null || email.equals("")){
			errorMsg = "Customer Email ID can't be null or empty.";
		}
				
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/delete.jsp");
			PrintWriter out= response.getWriter();
			rd.include(request, response);
                        out.println("<font color=red>"+errorMsg+"</font>");
		}else{
		
                        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                        PreparedStatement ps = null;
                        try {
                                ps = con.prepareStatement("delete from customer where cust_Email=?");
                                ps.setString(1, email);
                                ps.execute();

                                logger.info("Addmin delete customer = "+email);
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/delete.jsp");
                                PrintWriter out= response.getWriter();
                                rd.include(request, response);
                                out.println("<br><font color=green>No more customer " + email + "</font>");
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
