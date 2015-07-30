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

    @OneToMany(mappedBy = "product",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    private Set<Batch> batches;

    @ManyToOne
    @JoinColumn(name = "tenants_id", referencedColumnName = "Id")
    private Tenant tenant;

    /*
     *Конструкторы
     */
    public Product() {
    }

    public Product(String articleProduct, String name) {
        this.articleProduct = articleProduct;
        this.name = name;

    }

    public Product(String articleProduct) {
        this.articleProduct = articleProduct;

    }

    /*
     *Геттеры и Сеттеры
     */

    public String getArticleProduct() {
        return articleProduct;
    }

    public void setArticleProduct(String articleProduct) {
        this.articleProduct = articleProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Batch> getBatches() {
        return batches;
    }

    public void setBatches(Set<Batch> batches) {
        this.batches = batches;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
