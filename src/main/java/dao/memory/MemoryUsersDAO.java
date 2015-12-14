package dao.memory;

import domain.User;

import java.util.ArrayList;
import java.util.List;

public class MemoryUsersDAO {

    public MemoryUsersDAO() {}

    private static List<User> allUsers = new ArrayList<User>();

    static {
        allUsers.add(new User(1, "test@gmail.com", "1234", "Test", "Testov"));
        allUsers.add(new User(2, "user@gmail.com", "1111", "User", "User"));
        allUsers.add(new User(3, "vasya@mail.ru", "vasya", "Vasya", "Pupkin"));
    }

    public static List<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(List<User> allUsers) {
        MemoryUsersDAO.allUsers = allUsers;
    }
}