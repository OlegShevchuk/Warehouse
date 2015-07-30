package warehouse.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Олег on 01.06.2015.
 */
//Партия
    @Entity
    @Table(name="Batch")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "Id", nullable = false)
    private Product product;

    @Column(name = "count")
    private int count;

    @ManyToOne
    @JoinColumn(name = "warehousespace_id", referencedColumnName = "Id", nullable = false)
    private WarehouseSpace space;

    /*
     *Конструкторы
     */

    public Batch(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public Batch() {
    }

    /*
     *Геттеры и сеттеры
     */

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public WarehouseSpace getSpace() {
        return space;
    }

    public void setSpace(WarehouseSpace space) {
        this.space = space;
    }
}

