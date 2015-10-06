/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;

/**
 *
 * @author July
 */
public class CookieUtil {

    public static Cookie getCookieByName(Cookie[] cookies, String cookieName) {
        Cookie cookie = null;
        if (cookies != null) {

            for (Cookie c : cookies) {
                if ("_token".equals(c.getName())) {
                    cookie = c;
                }
            }
        }
        if (cookie == null) {
            cookie = new Cookie("_token", "common");
        }
        return cookie;
    }

    public static boolean saveCookie(Cookie cookie, User user) {
        if (user == null) {
            return false;
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        String insert_stmt = "insert into tokens values(?,?)";
        String delete_stmt = "delete from tokens where email = ?";
        try {
            PreparedStatement insert = conn.prepareStatement(insert_stmt);
            PreparedStatement delete = conn.prepareStatement(delete_stmt);
            insert.setString(1, user.getEmail());
            insert.setString(2, cookie.getValue());
            delete.setString(1, user.getEmail());
            delete.executeUpdate();
            insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CookieUtil.class.getName()).log(Level.SEVERE, null, ex);
            pool.freeConnection(conn);
            return false;
        }
        pool.freeConnection(conn);
        return true;
    }

    public static boolean checkCookie(Cookie cookie, User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        ResultSet rs = null;
        String tokenValue = "";
        String stmt = "select token_value from tokens where email = ?";
        try {
            PreparedStatement query = conn.prepareStatement(stmt);
            query.setString(1, user.getEmail());
            rs = query.executeQuery();
            if (!rs.next()) {
                return true;
            }

            tokenValue = rs.getString("token_value");

        } catch (SQLException ex) {
            Logger.getLogger(CookieUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            pool.freeConnection(conn);
        }
        if (tokenValue.equals(cookie.getValue())) {
            return true;
        } else {
            return false;
        }
    }

}
