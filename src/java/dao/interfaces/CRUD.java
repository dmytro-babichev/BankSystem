package dao.interfaces;

import dao.SQLConnection;

/**
 *
 * @author laory
 */

public interface CRUD <T> {
    
    public int create(T entity);

    public boolean delete(T entity);

    public T read(int id);

    public boolean update(T entity);
    
}