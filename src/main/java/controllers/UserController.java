package controllers;

import memory.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users.getAllUsers().forEach(item -> {
            try {
                resp.getWriter().write(item.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}