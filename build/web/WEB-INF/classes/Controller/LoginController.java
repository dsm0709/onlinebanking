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

public class LoginController extends HttpServlet {

    private User user = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        if (action.equals("login") && checkUser(request, response) && user != null) {
            session.setAttribute("user", user);
            Cookie cookie = new Cookie("_token","common");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            response.sendRedirect("Home");
        } else {
            response.sendRedirect("");
        }

    }

    private boolean checkUser(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        ResultSet rs = null;
        String stmt = "select * from users where email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(stmt);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    user = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getDouble("balance"));
                    return true;
                }
            }
            pool.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);

        }
        pool.freeConnection(conn);
        return false;

    }

}
