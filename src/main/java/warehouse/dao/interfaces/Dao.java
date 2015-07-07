package warehouse.dao.interfaces;

import warehouse.exeption.OperationNotSupported;

/**
 * Created by Олег on 02.06.2015.
 */
public interface Dao<T> {

    T creat(T model);
    T select(String login);
    T update(T model) throws Exception;
    T del(T model) throws OperationNotSupported;
}
