package dao;

import domain.User;
import memory.Users;

import java.util.List;
import java.util.stream.Collectors;

public class UserDAOImpl implements UserDAO<User> {

    public User checkUserData(String email, String password) {
        User dbUser = null;
        try {
            dbUser = Users.getAllUsers()
                    .stream()
                    .filter(user -> user.getEmail().equals(email) || user.getPassword().equals(password))
                    .collect(Collectors.toList()).get(0);
        } catch (Exception ex) {
            return null;
        }
        return dbUser;
    }

    public void create(User user) {
        int id = getAll().get(getAll().size() - 1).getId() + 1;
        user.setId(id);
        Users.getAllUsers().add(user);
    }

    public User getById(final int id) {
        return Users.getAllUsers().stream().filter(item -> item.getId() == id).collect(Collectors.toList()).get(0);
    }

    public User update(User user) {
        User dbUser = Users.getAllUsers().get(user.getId());
        dbUser.setName(user.getName());
        dbUser.setLastName(user.getLastName());
        dbUser.setEmail(user.getEmail());
        dbUser.setPassword(user.getPassword());
        dbUser.setAvatar(user.getAvatar());
        Users.getAllUsers().remove(user);
        Users.getAllUsers().add(dbUser);
        return dbUser;
    }

    public User delete(int id) {
        User user = getById(id);
        Users.getAllUsers().remove(user);
        return user;
    }

    public List<User> getAll() {
        return Users.getAllUsers();
    }
}