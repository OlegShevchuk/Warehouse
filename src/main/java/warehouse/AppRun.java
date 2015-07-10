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
import warehouse.model.Product;
import warehouse.model.User;
import warehouse.model.Warehouse;
import warehouse.model.WarehouseSpace;
import warehouse.servise.impliments.UserServiceImpl;
import warehouse.servise.interfaces.UserServise;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Олег on 07.07.2015.
 */
public class AppRun {
    public static void main(String[] args) {


        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("\\context.xml");
//
//        Set<WarehouseSpace> warehouseSpaceSet=new HashSet<WarehouseSpace>();
//        Warehouse warehouse=new Warehouse("street", warehouseSpaceSet);
//        for (int i=0;i<10;i++){
//            warehouseSpaceSet.add(new WarehouseSpace(warehouse.getWarehousName()+"."+i,warehouse));
//        }
//
//        WarehouseDaoHibernate warehouseDaoHibernate=applicationContext.getBean(WarehouseDaoHibernate.class);
//        warehouseDaoHibernate.creat(warehouse);
        WarehouseSpaceDao warehouseSpaceDao=applicationContext.getBean(WarehouseSpaceDaoHibernate.class);
        WarehouseSpace warehouseSpace=warehouseSpaceDao.select("street.8");
       // Product product=new Product("aaacb","Панталоны", new HashSet<WarehouseSpace>());

        System.out.println();
        System.out.println(warehouseSpace);

        for(Product product:warehouseSpace.getProducts())
        System.out.println(product);


       // ProductDao productDao=applicationContext.getBean(ProductDaoHibernate.class);
       // product.getWarehouseSpaces().add(warehouseSpace);
        //warehouseSpace.getProducts().add(product);
       // productDao.creat(product);
        //System.out.println(warehouseDaoHibernate+"Бин получен");
       //Warehouse warehouse=warehouseDaoHibernate.select("street");
       // System.out.println("Типа работает!!!");
        //WarehouseSpace warehouseSpace=new WarehouseSpace("2", warehouse);
      // WarehouseSpaceDao warehouseSpaceDao=applicationContext.getBean(WarehouseSpaceDaoHibernate.class);
       // warehouseSpaceDao.creat(warehouseSpace);
       // System.out.println(warehouseSpaceDao.select("street3.1"));



    }
}
