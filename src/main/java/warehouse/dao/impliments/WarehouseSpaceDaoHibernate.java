package warehouse.dao.impliments;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import warehouse.dao.interfaces.WarehouseSpaceDao;
import warehouse.exeption.OperationNotSupported;
import warehouse.exeption.RecordNotFound;
import warehouse.model.WarehouseSpace;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Created by Олег on 08.07.2015.
 */

@Repository
public class WarehouseSpaceDaoHibernate implements WarehouseSpaceDao {
    private final Logger LOG=Logger.getLogger("WarehouseSpaceDaoHibernate");

    @Autowired
    private EntityManagerFactory entityManagerFactory;


   public WarehouseSpace creat(WarehouseSpace model) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(model);
            entityTransaction.commit();
        }
        catch (Exception e){
            entityTransaction.rollback();
            LOG.info(e);
        }
        finally {
            entityManager.close();
        }
        return model;
    }

    public WarehouseSpace select(String login) throws RecordNotFound {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try{
        WarehouseSpace warehouseSpace=entityManager.createQuery("from WarehouseSpace w Where w.articleSpace=:articleSpace", WarehouseSpace.class).
                setParameter("articleSpace", login).
                getSingleResult();
        return warehouseSpace;}
        catch (Exception e){
            LOG.error(e);
            LOG.info("Запись не найдена");
            throw new RecordNotFound("Места с таким артикулем не существует");
        }finally {
            entityManager.close();
        }
    }

    public WarehouseSpace update(WarehouseSpace model) throws Exception {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        LOG.info("Начало транзакции");
        try{
            WarehouseSpace warehouseSpace=select(model.getArticleSpace());
            entityTransaction.begin();
            entityManager.merge(model);
            entityTransaction.commit();
            return warehouseSpace;

        }catch (Exception e){
            LOG.info("Транзакция прервана");
            entityTransaction.rollback();
            LOG.error(e);
            throw e;
        }finally {
            entityManager.close();
        }

    }

    public WarehouseSpace del(WarehouseSpace model) throws OperationNotSupported {
        return null;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
}
