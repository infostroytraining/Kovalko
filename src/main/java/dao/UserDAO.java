package dao;

import domain.User;

public interface UserDAO<User> extends DAO<User> {
    User checkUserData(String email, String password);
}