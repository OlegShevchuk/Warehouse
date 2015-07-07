package warehouse.servise.impliments;

import warehouse.dao.interfaces.UserDao;
import warehouse.exeption.RecordNotFound;
import warehouse.servise.interfaces.UserServise;
import warehouse.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Олег on 06.07.2015.
 */
@Service
public class UserServiceImpl implements UserServise{
    private final Logger LOG=Logger.getLogger("UserServiceImpl");

    @Autowired
    private UserDao userDao;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {

        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User authorization(String login, String pass) throws RecordNotFound {

        User user=userDao.select(login);
        if (user==null){
            LOG.info("Пользователь с таким именем не найден: "+ login);
            throw new RecordNotFound("Неверный логин!");
        }
        if (!user.getPass().equals(pass)) {
            LOG.info("Пароль не совпадает!!!");
            throw new RecordNotFound("Неверный пароль");
        }
        return user;
    }

    public void exit(User user) {


    }
}
