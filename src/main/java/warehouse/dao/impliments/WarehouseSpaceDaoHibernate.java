package warehouse.dao.impliments;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import warehouse.dao.interfaces.WarehouseSpaceDao;
import warehouse.exeption.OperationNotSupported;
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


    //@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
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

    public WarehouseSpace select(String login) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        WarehouseSpace warehouseSpace=entityManager.createQuery("from WarehouseSpace w Where w.articleSpace=:articleSpace", WarehouseSpace.class).
                setParameter("articleSpace", login).
                getSingleResult();
        return warehouseSpace;
    }

    public WarehouseSpace update(WarehouseSpace model) throws Exception {
        return null;
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
