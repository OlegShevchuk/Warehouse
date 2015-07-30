package warehouse.dao.impliments;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import warehouse.dao.interfaces.BatchDao;
import warehouse.dao.interfaces.ProductDao;
import warehouse.exeption.OperationNotSupported;
import warehouse.exeption.RecordNotFound;
import warehouse.model.Batch;
import warehouse.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Created by Олег on 30.07.2015.
 */
@Repository
public class BatchDaoHibernate implements BatchDao {

    private final Logger LOG=Logger.getLogger(BatchDaoHibernate.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private ProductDao productDao;

    @Override
    public Batch creat(Batch model) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        try{
            LOG.info("Начало транзакции");
            entityTransaction.begin();
//            Product product=productDao.select(model.getProduct().getArticleProduct());
//            if (product==null) {
//                product = productDao.creat(model.getProduct());
//            }
//            model.setProduct(product);
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

    @Override
    public Batch select(String login) throws RecordNotFound {


        return null;
    }

    @Override
    public Batch update(Batch model) throws Exception {
        return null;
    }

    @Override
    public Batch del(Batch model) throws OperationNotSupported {
        return null;
    }
}
