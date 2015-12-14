package dao.factory;

import dao.UserDAO;
import dao.memory.MemoryUserDAOImpl;
import dao.postgresql.PostgreUserDAO;

public class DAOFactory {

    private static final String MEMORY = "memory";
    private static final String DB = "db";

    public static UserDAO getUserDAO(String type) {
        if (type.equals(MEMORY)) return new MemoryUserDAOImpl();
        else if (type.equals(DB)) return new PostgreUserDAO();
        else return null;
    }
}