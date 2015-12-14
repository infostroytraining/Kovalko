package dao.postgresql;

import dao.UserDAO;
import dao.exception.DAOException;
import db.ConnectionHolder;
import domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreUserDAO implements UserDAO {

    @Override
    public User create(User user) throws DAOException {
        final String INSERT_ANSWER = "INSERT INTO users(name, last_name, email, password) values(?,?,?,?);";
        Connection connection = null;
        try {
            connection = ConnectionHolder.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(INSERT_ANSWER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
        return user;
    }

    @Override
    public User getById(int id) throws DAOException {
        Connection connection = null;
        User user = new User();
        try {
            connection = ConnectionHolder.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DAOException(ex);
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
        return user;
    }

    @Override
    public User update(User user) throws DAOException {
        Connection connection = null;
        String query = "UPDATE users SET name=?, last_name=?, email=?, password=? WHERE id=?";
        try {
            connection = ConnectionHolder.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DAOException(ex);
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User delete(int id) throws DAOException {
        Connection connection = null;
        try {
            connection = ConnectionHolder.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DAOException(ex);
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
        return getById(id);
    }

    @Override
    public List<User> getAll() throws DAOException {
        Connection connection = null;
        List<User> users = new ArrayList<User>();
        try {
            connection = ConnectionHolder.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DAOException(ex);
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
        return users;
    }

    @Override
    public User checkUserData(String email, String password) throws DAOException {
        Connection connection = null;
        User user = new User();
        try {
            connection = ConnectionHolder.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email=? OR password=?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DAOException(ex);
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
        return user;
    }
}