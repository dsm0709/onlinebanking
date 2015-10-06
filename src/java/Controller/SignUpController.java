/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import Util.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author July
 */
public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = null;
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        if (!(email.contains("@") && !password.equals("") && password.equals(confirm))) {
            url = "/signup.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        } else {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn = pool.getConnection();
            String addStatement = "INSERT INTO users(first_name, last_name, "
                    + "email, password) VALUES(?,?,?,?)";
            try {
                conn.setAutoCommit(false);
                PreparedStatement ps = conn.prepareStatement(addStatement);
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, password);
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                if(conn != null)
                    try {
                        conn.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex1);
                }
                response.sendRedirect("signup.jsp");
                return;
            }finally{
            pool.freeConnection(conn);    
            }
            url = "Home";
            request.getSession().setAttribute("user", new User(firstName,lastName,email,0.0));
            response.sendRedirect(url);

        }
        
    }

    
    

}
