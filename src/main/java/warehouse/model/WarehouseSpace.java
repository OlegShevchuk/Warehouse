package warehouse.model;

import javax.persistence.*;

/**
 * Created by Олег on 01.06.2015.
 */

@Entity
@Table(name="WarehouseSpaces")
public class WarehouseSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="article", length = 20, unique = true, nullable = false)
    private String article;

//    @Column(name="batch", length = 35, unique = true)
//    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;

    public WarehouseSpace() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
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
                "article='" + article + '\'' +
                ", warehouse=" + warehouse +
                '}';
    }
}
