package db;

import dao.exception.DAOException;

public interface Transaction<T> {
    T execute() throws DAOException;
}