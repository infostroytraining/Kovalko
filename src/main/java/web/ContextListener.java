package web;

import dao.UserDAO;
import dao.factory.DAOFactory;
import services.UserService;
import services.ValidatorService;
import web.factory.ServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    private static final String STORAGE_INIT_PARAMETER = "storage";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        String storageMode = servletContext.getInitParameter(STORAGE_INIT_PARAMETER);

        UserService userService = ServiceFactory.getUserService(storageMode);
        servletContext.setAttribute("userService", userService);

        UserDAO userDAO = DAOFactory.getUserDAO(storageMode);
        if (userDAO != null) {
            ValidatorService validatorService = new ValidatorService(userDAO);
            servletContext.setAttribute("validatorService", validatorService);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}