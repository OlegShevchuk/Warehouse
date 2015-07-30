package warehouse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import warehouse.dao.impliments.ProductDaoHibernate;
import warehouse.dao.impliments.UsersDaoHibernate;
import warehouse.dao.impliments.WarehouseDaoHibernate;
import warehouse.dao.impliments.WarehouseSpaceDaoHibernate;
import warehouse.dao.interfaces.ProductDao;
import warehouse.dao.interfaces.UserDao;
import warehouse.dao.interfaces.WarehouseDao;
import warehouse.dao.interfaces.WarehouseSpaceDao;
import warehouse.exeption.RecordNotFound;
import warehouse.model.*;
import warehouse.servise.impliments.UserServiceImpl;
import warehouse.servise.interfaces.UserServise;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Олег on 07.07.2015.
 */
public class AppRun {
    public static void main(String[] args) throws RecordNotFound {


        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("\\context.xml");

        WarehouseDao warehouseDao=applicationContext.getBean(WarehouseDaoHibernate.class);


        WarehouseSpace warehouseSpace;
        WarehouseSpaceDao warehouseSpaceDao=applicationContext.getBean(WarehouseSpaceDaoHibernate.class);
        warehouseSpace=warehouseSpaceDao.select("2abc");

        Tenant tenant=new Tenant("Причал торпедных катеров");

        Product product=new Product("123","Буйки");
        product.setTenant(tenant);
        Batch batch=new Batch(product, 10);

        Set<Batch> set=new HashSet<>();
        set.add(batch);
        warehouseSpace.setProducts(set);

        try {
            warehouseSpaceDao.update(warehouseSpace);
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println();
        System.out.println();








    }
}
