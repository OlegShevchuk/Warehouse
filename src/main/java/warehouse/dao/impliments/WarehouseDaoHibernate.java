package warehouse.dao.impliments;

import warehouse.dao.interfaces.WarehouseDao;
import warehouse.exeption.OperationNotSupported;
import warehouse.model.Warehouse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Created by Олег on 07.07.2015.
 */
@Repository
public class WarehouseDaoHibernate implements WarehouseDao {
    private final Logger LOG=Logger.getLogger("WarehouseDaoHibernate");

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public WarehouseDaoHibernate() {
    }

    public WarehouseDaoHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Warehouse creat(Warehouse model) {

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        LOG.info("Начало транзакции");
        try{
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

    public Warehouse select(String login) {

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Warehouse warehouse =(Warehouse)entityManager.createQuery("From warehouse w Where w.warehousname= :warehousname ")
                .setParameter("warehousname", login)
                .getSingleResult();
        LOG.info(warehouse);
        return warehouse;

    }

    public Warehouse update(Warehouse model) throws Exception {
        return null;
    }

    public Warehouse del(Warehouse model) throws OperationNotSupported {
        return null;
    }
}
