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

@WebServlet(name = "AdminUpdateInventory", urlPatterns = { "/AdminUpdateInventory" })
public class AdminUpdateQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(AdminUpdateQuantityServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
            
		String pro_no = request.getParameter("pro_no");	
		String pro_qty = request.getParameter("pro_qty");
		
		String errorMsg = null;
		if(pro_no == null || pro_no.equals("")){
			errorMsg = "Product No can't be null or empty.";
		}
		
		if(pro_qty == null || pro_qty.equals("")){
			errorMsg = "Product Quantity can't be null or empty.";
		}
				
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateInventory.jsp");
			PrintWriter out= response.getWriter();  
			rd.include(request, response);
                        out.println("<font color=red>"+errorMsg+"</font>");
		}else{
		
                        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                        PreparedStatement ps = null;
                        try {
                                ps = con.prepareStatement("update inventory set prod_Case_Qty = ? where prod_No = ?");
                                ps.setString(1, pro_qty);
                                ps.setString(2, pro_no);
                                ps.execute();

                                logger.info("Addmin update inventory = " + pro_no + " :: " + pro_qty);
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateInventory.jsp");
                                PrintWriter out= response.getWriter();
                                rd.include(request, response);
                                out.println("<br><font color=green>Quantity is updated successfully: product no " 
                                        + pro_no + " = " + pro_qty + " cases.</font>");
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
