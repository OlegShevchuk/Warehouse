package warehouse.servise.interfaces;

import warehouse.exeption.RecordNotFound;
import warehouse.model.User;

/**
 * Created by Олег on 06.07.2015.
 */
public interface UserServise {
    User authorization(String login, String pass) throws RecordNotFound;
    void exit(User user);
}
