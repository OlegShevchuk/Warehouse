package warehouse.dao.impliments;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import warehouse.dao.interfaces.ProductDao;
import warehouse.exeption.OperationNotSupported;
import warehouse.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Created by Олег on 10.07.2015.
 */
@Repository
public class ProductDaoHibernate implements ProductDao {
    private final Logger LOG=Logger.getLogger("ProductDaoHibernate");

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Product creat(Product model) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        try{
            LOG.info("Начало транзакции");
            entityTransaction.begin();
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
        Product product =entityManager.createQuery("From User u Where u.articleProduct= :articleProduct",Product.class)
                .setParameter("articleProduct", articleProduct)
                .getSingleResult();
        LOG.info(product);
        return product;
    }

    public Product update(Product model) throws Exception {
        return null;
    }

    public Product del(Product model) throws OperationNotSupported {
        return null;
    }
}
