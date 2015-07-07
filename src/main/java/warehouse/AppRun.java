package warehouse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import warehouse.dao.impliments.UsersDaoHibernate;
import warehouse.dao.impliments.WarehouseDaoHibernate;
import warehouse.dao.interfaces.UserDao;
import warehouse.exeption.RecordNotFound;
import warehouse.model.User;
import warehouse.model.Warehouse;
import warehouse.servise.impliments.UserServiceImpl;
import warehouse.servise.interfaces.UserServise;

/**
 * Created by Олег on 07.07.2015.
 */
public class AppRun {
    public static void main(String[] args) {


        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("\\context.xml");
//        UserServise userServise=applicationContext.getBean(UserServiceImpl.class);
//
//        try {
//            User user =userServise.authorization("oleg","12345");
//        } catch (RecordNotFound recordNotFound) {
//            System.out.println(recordNotFound);
//        }
        //UserDao userDao=applicationContext.getBean(UsersDaoHibernate.class);
        //User user=new User("Mudak","mudak","asdf");
        //userDao.creat(user);

        //System.out.println(userDao.select("oleg"));
        Warehouse warehouse=new Warehouse("street3", 10);

        WarehouseDaoHibernate warehouseDaoHibernate=applicationContext.getBean(WarehouseDaoHibernate.class);
        //warehouseDaoHibernate.creat(warehouse);
        //System.out.println(warehouseDaoHibernate+"Бин получен");
        //warehouseDaoHibernate.creat(warehouse);
        System.out.println(warehouseDaoHibernate.select("street"));



    }
}
