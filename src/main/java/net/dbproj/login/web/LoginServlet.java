package net.dbproj.login.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.dbproj.login.bean.LoginBean;
import net.dbproj.login.database.LoginDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    public void init() {
        loginDao = new LoginDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            if (loginDao.validate(loginBean)) {
                // If login is successful, retrieve the user's role
                String role = loginDao.getUserRole(username); // New method in LoginDao to get role
                
                // Set user and role in session
                HttpSession session = request.getSession();
                session.setAttribute("user", username);
                session.setAttribute("role", role);

                // Redirect based on role
                if ("admin".equals(role)) {
                    response.sendRedirect("artist-list.jsp"); // Redirect to admin dashboard
                } else {
                    response.sendRedirect("user-list.jsp"); // Redirect to user dashboard
                }
            } else {
                // Login failed, redirect back to login page
                response.sendRedirect("login.jsp?error=true");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=true");
        }
    }
}
