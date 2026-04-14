package net.dbproj.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dbproj.usermanagement.dao.UserDAO;
import net.dbproj.usermanagement.model.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);  // List users, possibly sorted
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	    String sortBy = request.getParameter("sortBy");
    	    String sortOrder = request.getParameter("sortOrder");

    	    // Default to sorting by 'name' if no sortBy is specified
    	    if (sortBy == null || sortBy.isEmpty()) {
    	        sortBy = "name";
    	    }
    	    
    	    // Default to ascending order if no sortOrder is specified
    	    if (sortOrder == null || sortOrder.isEmpty()) {
    	        sortOrder = "asc";
    	    }

    	    List<User> listUser = userDAO.selectAllUsers(sortBy, sortOrder);
    	    request.setAttribute("listUser", listUser);
    	    RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
    	    dispatcher.forward(request, response);
    	}


    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
    	        dispatcher.forward(request, response);
    	    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        User existingUser = userDAO.selectUser(id);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
    	        request.setAttribute("user", existingUser);
    	        dispatcher.forward(request, response);
    	    }


    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String name = request.getParameter("name");
        String artist = request.getParameter("artist");
        String genre = request.getParameter("genre");
        String album = request.getParameter("album");

        // Handle the checkbox value
        String likedParam = request.getParameter("liked");
        Boolean liked = likedParam != null && likedParam.equals("on"); // Checkbox returns "on" if checked

        User newUser = new User(name, artist, genre, album, liked);
        userDAO.insertUser(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String artist = request.getParameter("artist");
        String genre = request.getParameter("genre");
        String album = request.getParameter("album");

        // Handle the checkbox value
        String likedParam = request.getParameter("liked");
        Boolean liked = likedParam != null && likedParam.equals("on"); // Checkbox returns "on" if checked

        // Create a User object with the updated details
        User user = new User(id, name, artist, genre, album, liked);

        // Update the user in the database
        userDAO.updateUser(user);

        // Redirect to the list page
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("list");
    }
}
