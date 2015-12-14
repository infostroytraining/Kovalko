package dao;

import dao.exception.DAOException;
import domain.User;

public interface UserDAO extends DAO<User> {
    User checkUserData(String email, String password) throws DAOException;
}