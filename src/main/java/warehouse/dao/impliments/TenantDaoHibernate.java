package warehouse.dao.impliments;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import warehouse.dao.interfaces.TenantDao;
import warehouse.exeption.OperationNotSupported;
import warehouse.exeption.RecordNotFound;
import warehouse.model.Tenant;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Created by Олег on 30.07.2015.
 */
@Repository
public class TenantDaoHibernate implements TenantDao {
    private final Logger LOG=Logger.getLogger("ProductDaoHibernate");

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Override
    public Tenant creat(Tenant model) {
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

    @Override
    public Tenant select(String login) throws RecordNotFound {

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Tenant tenant =entityManager.createQuery("From Tenant t Where t.companyName= :companyName",Tenant.class)
                .setParameter("companyName", login)
                .getSingleResult();
        LOG.info(tenant);
        return tenant;
    }

    @Override
    public Tenant update(Tenant model) throws Exception {

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction= entityManager.getTransaction();
        LOG.info("начало транзакции");
        try {
            transaction.begin();
            Tenant oldUser = select(model.getCompanyName());
            entityManager.merge(model);
            transaction.commit();
            LOG.info("транзакция завершилась успешно");
            return oldUser;
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

    @Override
    public Tenant del(Tenant model) throws OperationNotSupported {
        return null;
    }
}
