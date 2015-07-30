package warehouse.model;

import javax.persistence.*;
import java.util.Date;
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

    @OneToMany(mappedBy = "space",
                cascade = {CascadeType.ALL},
                fetch = FetchType.LAZY)
    private Set<Batch> products=new HashSet<>();

    @Temporal(TemporalType.DATE)
    private Date creationDate;


    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "Id", nullable = false)
    private Warehouse warehouse;


    /*
     * Методы
     */

    public void addBatch(Batch batch){

        if (products==null) products=new HashSet<>();
        products.add(batch);
    }

    /*
     *Конструкторы
     */

    public WarehouseSpace() {

    }

    public WarehouseSpace(String article, Warehouse warehouse) {
        this.articleSpace = article;
        this.warehouse = warehouse;
    }

    /*
     * Геттеры и сеттеры
     */

    public Set<Batch> getProducts() {
        return products;
    }

    public void setProducts(Set<Batch> products) {
        this.products = products;
    }

    public String getArticleSpace() {
        return articleSpace;
    }

    public void setArticleSpace(String articleSpace) {
        this.articleSpace = articleSpace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }


}
