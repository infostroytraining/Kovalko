package controllers;

import domain.User;
import services.RegistrationService;
import services.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "registration", urlPatterns = "/registration")
public class RegistrationController extends HttpServlet {
    private ValidatorService validatorService;
    private RegistrationService registrationService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.validatorService = new ValidatorService();
        this.registrationService = new RegistrationService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String captcha = (String) session.getAttribute("captcha");
        String code = (String) req.getParameter("code");

        if (captcha != null && code != null && captcha.equals(code)) {
            User user = new User();
            user.setName(req.getParameter("name"));
            user.setLastName(req.getParameter("lastName"));
            user.setEmail(req.getParameter("email"));
            user.setPassword(req.getParameter("password"));

            if (validatorService.validateUserData(user)) {
                registrationService.createNewUser(user);
                res.getWriter().write("You was registered successfully!");
            }
            else if (!validatorService.validateUserData(user))
                res.getWriter().write("Incorrect user data!");

        } else res.getWriter().write("Incorrect captcha!");
    }
}