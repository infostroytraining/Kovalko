package dao.memory;

import dao.UserDAO;
import domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class MemoryUserDAOImpl implements UserDAO {

    public User checkUserData(String email, String password) {
        User dbUser = null;
        try {
            dbUser = MemoryUsersDAO.getAllUsers()
                    .stream()
                    .filter(user -> user.getEmail().equals(email) || user.getPassword().equals(password))
                    .collect(Collectors.toList()).get(0);
        } catch (Exception ex) {
            return null;
        }
        return dbUser;
    }

    public User create(User user) {
        int id = getAll().get(getAll().size() - 1).getId() + 1;
        user.setId(id);
        MemoryUsersDAO.getAllUsers().add(user);
        return user;
    }

    public User getById(final int id) {
        return MemoryUsersDAO.getAllUsers().stream().filter(item -> item.getId() == id).collect(Collectors.toList()).get(0);
    }

    public User update(User user) {
        User dbUser = MemoryUsersDAO.getAllUsers().get(user.getId());
        dbUser.setName(user.getName());
        dbUser.setLastName(user.getLastName());
        dbUser.setEmail(user.getEmail());
        dbUser.setPassword(user.getPassword());
        dbUser.setAvatar(user.getAvatar());
        MemoryUsersDAO.getAllUsers().remove(user);
        MemoryUsersDAO.getAllUsers().add(dbUser);
        return dbUser;
    }

    public User delete(int id) {
        User user = getById(id);
        MemoryUsersDAO.getAllUsers().remove(user);
        return user;
    }

    public List<User> getAll() {
        return MemoryUsersDAO.getAllUsers();
    }
}