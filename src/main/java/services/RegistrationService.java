package services;

import dao.UserDAO;
import dao.UserDAOImpl;
import domain.User;

public class RegistrationService {
    private UserDAO userDAO;

    public RegistrationService() {
        this.userDAO = new UserDAOImpl();
    }

    public void createNewUser(User user) {
        this.userDAO.create(user);
    }
}