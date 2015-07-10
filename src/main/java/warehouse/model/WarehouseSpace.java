package warehouse.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Олег on 01.06.2015.
 */

@Entity
@Table(name="WarehouseSpaces")
public class WarehouseSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name="articleSpace", length = 20, unique = true, nullable = false)
    private String articleSpace;

    @ManyToMany
    @JoinTable(name = "product_space",
        joinColumns = @JoinColumn(name = "space"),
        inverseJoinColumns = @JoinColumn(name = "product"))
    private Set<Product> products;


    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "Id", nullable = false)
    private Warehouse warehouse;

    public WarehouseSpace() {
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public WarehouseSpace(String article, Warehouse warehouse) {
        this.articleSpace = article;
        this.warehouse = warehouse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticleSpace() {
        return articleSpace;
    }

    public void setArticleSpace(String article) {
        this.articleSpace = article;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return "WarehouseSpace{" +
                "articleSpace='" + articleSpace + '\'' +
                ", products=" + products.size() +
                ", warehouse=" + warehouse.getWarehousName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarehouseSpace)) return false;

        WarehouseSpace that = (WarehouseSpace) o;

        return articleSpace.equals(that.articleSpace);

    }

    @Override
    public int hashCode() {
        return articleSpace.hashCode();
    }
}
