package warehouse.dao.interfaces;

import warehouse.model.Product;
import warehouse.model.Tenant;

import java.util.List;

/**
 * Created by Олег on 10.07.2015.
 */
public interface ProductDao extends Dao<Product> {

    List<Product> findForTenant(Tenant tenant);
}
