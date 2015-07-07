package warehouse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import warehouse.exeption.RecordNotFound;
import warehouse.model.User;
import warehouse.servise.impliments.UserServiceImpl;
import warehouse.servise.interfaces.UserServise;

/**
 * Created by Олег on 07.07.2015.
 */
public class AppRun {
    public static void main(String[] args) {


        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("\\context.xml");
        UserServise userServise=applicationContext.getBean(UserServiceImpl.class);

        try {
            User user =userServise.authorization("oleg","12345");
        } catch (RecordNotFound recordNotFound) {
            System.out.println(recordNotFound);
        }
    }
}
