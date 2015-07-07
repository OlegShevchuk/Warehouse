package warehouse.model;

import javax.persistence.*;

/**
 * Created by Олег on 01.06.2015.
 */

@Entity
@Table(name="users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="fullName", length=30)
    private String fullName;

    @Column(name="login", length = 20, unique = true, nullable = false)
    private String login;
    @Column(name="pass",length = 15)
    private String pass;

    public User(String fullName, String login, String pass) {
        this.fullName = fullName;
        this.login = login;
        this.pass = pass;
    }

    public User() {
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
