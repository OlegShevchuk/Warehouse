package warehouse.dao.interfaces;

import warehouse.model.Tenant;

import java.util.List;

/**
 * Created by Олег on 10.06.2015.
 */
public interface TenantDao extends Dao<Tenant> {
    List<Tenant> allSelect();
}
