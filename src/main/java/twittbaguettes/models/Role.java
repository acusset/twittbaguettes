package twittbaguettes.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Twittbaguettes
 *
 * @author Antoine on 23/05/2016
 */

@Entity
@Table(name = "roles")
public class Role {

    private long id;
    @JsonIgnore
    private User user;
    private String role;

    public final static String ROLE_ADMIN = "ADMIN";
    public final static String ROLE_USER = "USER";

    public Role() { }

    public Role(User user, String role) {
        this.role = role;
        this.user = user;
    }

    /**
     * Getters
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    @Column(name = "role", nullable = false, unique = true, length = 50)
    public String getRole() {
        return role;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    /**
     * Setters
     */
    public void setRole(String role) {
        this.role = role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
