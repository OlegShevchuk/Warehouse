package warehouse.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Олег on 10.07.2015.
 */
@Table(name = "product_space")
public class Product_Space {

    @Id
    private long id;

    @Column(name = "product", nullable = false)
    private Product product;

    @Column(name = "space", nullable = false)
    private WarehouseSpace warehouseSpace;


}
