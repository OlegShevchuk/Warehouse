package warehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Олег on 02.06.2015.
 */
@Entity
@Table(name="warehouse")
public class Warehouse {

    @Id
    private int id;

    @Column(name="warehousname", length = 30, unique = true, nullable = false)
    private String warehousName;

    @Column(name="size", length = 10, nullable = false)
    private int sizeWarehouse;

    public Warehouse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", warehousName='" + warehousName + '\'' +
                ", sizeWarehouse=" + sizeWarehouse +
                '}';
    }
}
