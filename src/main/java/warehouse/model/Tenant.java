package warehouse.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Олег on 01.06.2015.
 */

//Арендатор

@Entity
@Table(name = "Tenants")
public class Tenant {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "CompanyName")
    private String companyName;

    @OneToMany(mappedBy = "tenant",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    private Set<Product> products;

    /*
     *конструкторы
     */

    public Tenant() {
    }

    public Tenant(String companyName) {
        this.companyName = companyName;
    }

    /*
     *Геттеры и сеттеры
     */

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
