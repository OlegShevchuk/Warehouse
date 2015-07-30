package warehouse.dao.impliments;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import warehouse.dao.interfaces.ProductDao;
import warehouse.dao.interfaces.TenantDao;
import warehouse.exeption.OperationNotSupported;
import warehouse.exeption.RecordNotFound;
import warehouse.model.Product;
import warehouse.model.Tenant;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by Олег on 10.07.2015.
 */
@Repository
public class ProductDaoHibernate implements ProductDao {
    private final Logger LOG=Logger.getLogger("ProductDaoHibernate");

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private TenantDao tenantDao;

    @Override
    public List<Product> findForTenant(Tenant tenant) {

        EntityManager entityManager=entityManagerFactory.createEntityManager();

        Tenant tenantBase= null;
        try {
            tenantBase = tenantDao.select(tenant.getCompanyName());
        } catch (RecordNotFound recordNotFound) {
            return null;
        }

        List <Product> products=entityManager.createQuery("From Product p Where p.tenant", Product.class).
                setParameter("tenant", tenantBase).getResultList();
        return products;
    }

    public Product creat(Product model) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        try{
            LOG.info("Начало транзакции");
            entityTransaction.begin();
//            Tenant tenant=tenantDao.select(model.getTenant().getCompanyName());
//            if (tenant==null) {
//                tenant = tenantDao.creat(model.getTenant());
//            }
//            model.setTenant(tenant);
            entityManager.persist(model);
            entityTransaction.commit();
            LOG.info("Транзакция успешно завершилась");
            return model;
        }catch (Exception e){
            LOG.info("Транзакция прервана");
            entityTransaction.rollback();
            LOG.error(e);
        }
        finally {
            entityManager.close();
        }
        return null;
    }

    public Product select(String articleProduct) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Product product =entityManager.createQuery("From Product p Where p.articleProduct= :articleProduct",Product.class)
                .setParameter("articleProduct", articleProduct)
                .getSingleResult();
        LOG.info(product);
        return product;
    }

    public Product update(Product model) throws Exception {

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction= entityManager.getTransaction();
        LOG.info("начало транзакции");
        try {
            transaction.begin();
            Product oldProduct = select(model.getName());
            entityManager.merge(model);
            transaction.commit();
            LOG.info("транзакция завершилась успешно");
            return oldProduct;
        } catch (Exception e){
            LOG.error(e);
            transaction.rollback();
            LOG.info("Транзакция прервана!");
            throw e;
        }
        finally {
            entityManager.close();
        }

    }

    public Product del(Product model) throws OperationNotSupported {
        return null;
    }
}
