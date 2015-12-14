package controllers;

import domain.User;
import services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserController extends HttpServlet {

    private ServletContext servletContext;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = (List<User>) servletContext.getAttribute("allUsers");
        if (allUsers != null)
        allUsers.forEach(item -> {
            try {
                resp.getWriter().write(item.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.servletContext = config.getServletContext();
        UserService userService = (UserService) servletContext.getAttribute("userService");
        if (this.servletContext.getAttribute("allUsers") == null)
            this.servletContext.setAttribute("allUsers", userService);
    }
}