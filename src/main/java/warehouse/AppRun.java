package warehouse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import warehouse.dao.impliments.*;
import warehouse.dao.interfaces.*;
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


        //WarehouseSpace warehouseSpace =new WarehouseSpace("abc",warehouseDao.select("street"));
        WarehouseSpaceDao warehouseSpaceDao=applicationContext.getBean(WarehouseSpaceDaoHibernate.class);
        WarehouseSpace warehouseSpace=warehouseSpaceDao.select("abc");

        BatchDao batchDao=applicationContext.getBean(BatchDaoHibernate.class);
        Tenant tenant=new Tenant("Причал торпедных катеров");
        TenantDao tenantDao=applicationContext.getBean(TenantDaoHibernate.class);
        tenantDao.creat(tenant);
        tenant=tenantDao.select(tenant.getCompanyName());


        ProductDao productDao=applicationContext.getBean(ProductDaoHibernate.class);
        Product product=new Product("123","Буйки");
        product.setTenant(tenant);
        productDao.creat(product);
        product=productDao.select(product.getArticleProduct());

        Batch batch=new Batch(product, 10);
       // batch.setSpace(warehouseSpace);
        batch.setSpace(warehouseSpace);
        batch=batchDao.creat(batch);


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
