package warehouse.dao.impliments;

import warehouse.dao.interfaces.UserDao;
import warehouse.exeption.OperationNotSupported;
import warehouse.exeption.RecordNotFound;
import warehouse.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Created by Олег on 30.06.2015.
 */
@Repository
public class UsersDaoHibernate implements UserDao {
    private static final Logger LOG=Logger.getLogger("UsersDaoHibernate");

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public UsersDaoHibernate() {
    }

    public UsersDaoHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public User creat(User model) {

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction=entityManager.getTransaction();
        LOG.info("начало транзакции");
        try {
            transaction.begin();
            entityManager.persist(model);
            transaction.commit();
            LOG.info("транзакция завершилась успешно");
            return model;
        }
        catch (Exception e){
            LOG.error(e);
            transaction.rollback();
            LOG.info("Транзакция прервана!");
        }
        finally {
            entityManager.close();
        }

        return null;
    }

    public User select(String login) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        User user =(User)entityManager.createQuery("From User u Where u.login= :login ")
                .setParameter("login", login)
                .getSingleResult();
        LOG.info(user);
        return user;
    }

    public User update(User model) throws Exception {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction= entityManager.getTransaction();
        LOG.info("начало транзакции");
        try {
            transaction.begin();
            User oldUser = select(model.getLogin());
            if (oldUser != null) {
                entityManager.merge(model);
            } else {
                throw new RecordNotFound();
            }
            transaction.commit();
            LOG.info("транзакция завершилась успешно");
        } catch (Exception e){
            LOG.error(e);
            transaction.rollback();
            LOG.info("Транзакция прервана!");
            throw e;
        }
        return select(model.getLogin());
    }

    public User del(User model) throws OperationNotSupported {

        throw new OperationNotSupported();

        //return null;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
}
