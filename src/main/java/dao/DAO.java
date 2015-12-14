package dao;

import dao.exception.DAOException;

import java.util.List;

public interface DAO<T> {
    T create(T t) throws DAOException;
    T getById(int id) throws DAOException;
    T update(T t) throws DAOException;
    T delete(int id) throws DAOException;
    List<T> getAll() throws DAOException;
}