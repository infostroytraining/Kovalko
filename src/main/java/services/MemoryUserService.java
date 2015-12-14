package services;

import dao.UserDAO;
import dao.exception.DAOException;
import domain.User;
import services.exception.ServiceException;

import java.util.List;

public class MemoryUserService implements UserService {

    private UserDAO userDAO;

    public MemoryUserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User add(User user) throws ServiceException {
        try {
            return userDAO.create(user);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    @Override
    public User getById(int id) throws ServiceException {
        try {
            return userDAO.getById(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(User user) throws ServiceException {
        try {
            return userDAO.update(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User delete(int id) throws ServiceException {
        try {
            return userDAO.delete(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userDAO.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }
}