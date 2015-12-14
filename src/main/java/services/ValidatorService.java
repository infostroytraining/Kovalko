package services;

import dao.UserDAO;
import dao.exception.DAOException;
import domain.User;
import services.exception.ServiceException;

public class ValidatorService {
    private UserDAO userDAO;

    public ValidatorService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean validateUserData(User user) throws ServiceException {
        try {
            return userDAO.checkUserData(user.getEmail(), user.getPassword()) == null
                    && !user.getName().isEmpty() && !user.getLastName().isEmpty()
                    && !user.getEmail().isEmpty() && !user.getPassword().isEmpty();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    public boolean checkCaptcha(String captcha, String code) {
        return captcha != null && code != null && captcha.equals(code);
    }
}