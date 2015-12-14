package services;

import domain.User;
import services.exception.ServiceException;

import java.util.List;

public interface UserService {
    User add(User user) throws ServiceException;
    User getById(int id) throws ServiceException;
    User update(User user) throws ServiceException;
    User delete(int id) throws ServiceException;
    List<User> getAll() throws ServiceException;
}