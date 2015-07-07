package warehouse.dao.impliments;

import org.springframework.stereotype.Service;
import warehouse.dao.interfaces.WarehouseDao;
import warehouse.exeption.OperationNotSupported;
import warehouse.exeption.RecordNotFound;
import warehouse.model.Warehouse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

/**
 * Created by Олег on 07.07.2015.
 */
@Repository
public class WarehouseDaoHibernate implements WarehouseDao {
    private final Logger LOG=Logger.getLogger("WarehouseDaoHibernate");

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

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
        Warehouse warehouse =entityManager.createQuery("From Warehouse w Where w.warehousName=:warehousName", Warehouse.class)
                .setParameter("warehousName", login)
                .getSingleResult();
        LOG.info(warehouse);
        return warehouse;

    }

    public Warehouse update(Warehouse model) throws Exception {

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        LOG.info("Начало транзакции");
        try{
            entityTransaction.begin();
            Warehouse warehouse=select(model.getWarehousName());
            if (warehouse!=null) {
                entityManager.merge(model);
            } else {
                LOG.info("Такого склада не существует");
                throw new RecordNotFound("Запись не найдена");
            }

        }catch (Exception e){
            LOG.info("Транзакция прервана");
            entityTransaction.rollback();
            LOG.error(e);
        }

        return select(model.getWarehousName());
    }

    public Warehouse del(Warehouse model) throws OperationNotSupported {

        throw new OperationNotSupported();

    }
}
