package services;

import dao.UserDAO;
import db.TransactionManager;
import db.exception.TransactionException;
import domain.User;
import services.exception.ServiceException;

import java.sql.Connection;
import java.util.List;

public class TransactionUserService implements UserService {

    private TransactionManager transactionManager;
    private UserDAO userDAO;

    public TransactionUserService(TransactionManager transactionManager, UserDAO userDAO) {
        this.transactionManager = transactionManager;
        this.userDAO = userDAO;
    }

    @Override
    public User add(final User user) throws ServiceException {
        try {
            transactionManager.doTask(() -> userDAO.create(user), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public User getById(int id) throws ServiceException {
        try {
            transactionManager.doTask(() -> userDAO.getById(id), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public User update(User user) throws ServiceException {
        try {
            transactionManager.doTask(() -> userDAO.update(user), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public User delete(int id) throws ServiceException {
        try {
            transactionManager.doTask(() -> userDAO.delete(id), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            transactionManager.doTask(() -> userDAO.getAll(), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return null;
    }
}