package web.factory;

import dao.UserDAO;
import dao.memory.MemoryUserDAOImpl;
import dao.memory.MemoryUsersDAO;
import dao.postgresql.PostgreUserDAO;
import db.TransactionManager;
import services.MemoryUserService;
import services.TransactionUserService;
import services.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ServiceConfigurationError;

public class ServiceFactory {

    private static final String MEMORY = "memory";
    private static final String DB = "db";
    private static final String POSTGRE_DRIVER = "org.postgresql.Driver";

    public static UserService getUserService(String type) {
        if (type.equals(MEMORY)) return initMemoryService();
        else if (type.equals(DB)) {
            loadPostgreDriver();
            //initDBConfiguration();
            return initTransactionalService();
        } else throw new ServiceConfigurationError("Could initialize application with source type [" + type + "]");
    }

    private static UserService initMemoryService() {
        MemoryUsersDAO memoryUsersDAO = new MemoryUsersDAO();
        UserDAO userDAO = new MemoryUserDAOImpl();
        return new MemoryUserService(userDAO);
    }

    private static UserService initTransactionalService() {
        TransactionManager transactionManager = new TransactionManager();
        UserDAO userDAO = new PostgreUserDAO();
        return new TransactionUserService(transactionManager, userDAO);
    }

    private static void loadPostgreDriver() {
        try {
            Class.forName(POSTGRE_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServiceConfigurationError("Can't load " + POSTGRE_DRIVER + " driver");
        }
    }

    private static void initDBConfiguration() {
        Properties properties = new Properties();
        InputStream inputStream = ServiceFactory.class.getClassLoader().getResourceAsStream("config.properties");
        if (inputStream != null)
            try {
                properties.load(inputStream);
                System.setProperties(properties);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

}