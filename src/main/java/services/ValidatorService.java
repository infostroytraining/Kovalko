package services;

import dao.UserDAO;
import dao.UserDAOImpl;
import domain.User;

import javax.servlet.http.HttpSession;

public class ValidatorService {
    private UserDAO userDAO;

    public ValidatorService() {
        userDAO = new UserDAOImpl();
    }

    public boolean validateUserData(User user) {
        return userDAO.checkUserData(user.getEmail(), user.getPassword()) == null
                && !user.getName().isEmpty() && !user.getLastName().isEmpty()
                && !user.getEmail().isEmpty() && !user.getPassword().isEmpty();
    }
}