package warehouse.model;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Олег on 02.06.2015.
 */
@Entity
@Table(name="Warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",unique = true)
    private int id;

    @Column(name="name", length = 30, unique = true, nullable = false)
    private String warehousName;

    @OneToMany(mappedBy = "warehouse",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    private Set<WarehouseSpace> wSpases;

    public Warehouse() {
    }

    public Warehouse(String warehousName, Set<WarehouseSpace> wSpases) {
        this.warehousName = warehousName;
        this.wSpases = wSpases;
    }

    public Warehouse(String warehousName) {
        this.warehousName = warehousName;
    }

    public String getWarehousName() {
        return warehousName;
    }

    public void setWarehousName(String warehousName) {
        this.warehousName = warehousName;
    }

    public Set<WarehouseSpace> getwSpases() {
        return wSpases;
    }

    public void setwSpases(TreeSet<WarehouseSpace> wSpases) {
        this.wSpases = wSpases;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", warehousName='" + warehousName + '\'' +
                ", wSpases=" + wSpases +
                '}';
    }
}
