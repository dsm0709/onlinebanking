/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import Util.ConnectionPool;
import Util.CookieUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author July
 */
public class TransferController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action != null && action.equals("transfer")) {
            getServletContext().getRequestDispatcher("/transfer.jsp").forward(request, response);
        } 
        else if (action != null && action.equals("confirm") && request.getAttribute("secure").equals("yes")) {
            String email = request.getParameter("email");
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            double amount = -1;
            if(request.getParameter("amount").matches("\\d+"))
                amount = Double.valueOf(request.getParameter("amount"));
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn = pool.getConnection();
            ResultSet rs = null;
            User user = (User) session.getAttribute("user");
            if(user == null)
                user = new User();
            String withdrawstmt = "update users set balance = balance - ? where email = ?";
            String depositstmt = "update users set balance = balance + ? where email = ? and first_name = ? and last_name = ?";
            try {
                conn.setAutoCommit(false);
                PreparedStatement wd = conn.prepareStatement(withdrawstmt);
                PreparedStatement dp = conn.prepareStatement(depositstmt);
                wd.setDouble(1, amount);
                wd.setString(2, user.getEmail());
                dp.setDouble(1, amount);
                dp.setString(2, email);
                dp.setString(3, firstName);
                dp.setString(4, lastName);
                if (wd.executeUpdate() == dp.executeUpdate()) {
                    conn.commit();
                    session.setAttribute("amount", amount);
                    session.setAttribute("recipient_first_name", firstName);
                    session.setAttribute("recipient_last_name", lastName);
                    double balance = user.getBalance();
                    user.setBalance(balance - amount);
                    response.sendRedirect("Transfer?action=finished");
                } else {
                    throw new SQLException();
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                if (conn != null) {
                    try {
                        conn.rollback();
                    } catch (SQLException ex1) {
                        Logger.getLogger(TransferController.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
                response.sendRedirect("Transfer?action=transfer");
            } finally {
                pool.freeConnection(conn);
            }

        } else if (action != null && action.equals("finished")) {
            getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
        }

    }

}
