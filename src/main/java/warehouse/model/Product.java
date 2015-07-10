package warehouse.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Олег on 10.07.2015.
 */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "articleSpace", nullable = false, unique = true)
    private String articleProduct;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "product_space",
            joinColumns=@JoinColumn(name="product"),
            inverseJoinColumns = @JoinColumn(name = "space"))
    private Set<WarehouseSpace> warehouseSpaces;

    public Product() {
    }

    public Product(String articleProduct, String name, Set<WarehouseSpace> warehouseSpaces) {
        this.articleProduct = articleProduct;
        this.name = name;
        this.warehouseSpaces = warehouseSpaces;
    }

    public Product(String articleProduct, Set<WarehouseSpace> warehouseSpaces) {
        this.articleProduct = articleProduct;
        this.warehouseSpaces = warehouseSpaces;
    }

    public String getArticleProduct() {
        return articleProduct;
    }

    public void setArticleProduct(String articleProduct) {
        this.articleProduct = articleProduct;
    }

    public Set<WarehouseSpace> getWarehouseSpaces() {
        return warehouseSpaces;
    }

    public void setWarehouseSpaces(Set<WarehouseSpace> warehouseSpaces) {
        this.warehouseSpaces = warehouseSpaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        return articleProduct.equals(product.articleProduct);

    }

    @Override
    public int hashCode() {
        return articleProduct.hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "articleProduct='" + articleProduct + '\'' +
                ", name='" + name + '\'' +
                ", warehouseSpaces=" + warehouseSpaces.size() +
                '}';
    }
}
