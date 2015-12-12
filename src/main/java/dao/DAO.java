package dao;

import java.util.List;

public interface DAO<T> {
    void create(T t);
    T getById(int id);
    T update(T t);
    T delete(int id);
    List<T> getAll();
}