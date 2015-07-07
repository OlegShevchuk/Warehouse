package warehouse.model;

import javax.persistence.*;

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

    @Column(name="size", length = 10, nullable = false)
    private int sizeWarehouse;

    public Warehouse() {
    }



    public Warehouse(String warehousName, int sizeWarehouse) {
        this.warehousName = warehousName;
        this.sizeWarehouse = sizeWarehouse;
    }

    public String getWarehousName() {
        return warehousName;
    }

    public void setWarehousName(String warehousName) {
        this.warehousName = warehousName;
    }

    public int getSizeWarehouse() {
        return sizeWarehouse;
    }

    public void setSizeWarehouse(int sizeWarehouse) {
        this.sizeWarehouse = sizeWarehouse;
    }

    @Override
    public String toString() {
        return "warehouse{" +
                ", warehousName='" + warehousName + '\'' +
                ", sizeWarehouse=" + sizeWarehouse +
                '}';
    }
}
