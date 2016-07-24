package twittbaguettes.models;


import com.fasterxml.jackson.annotation.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * Twittbaguettes
 *
 * @author Antoine on 23/05/2016
 */

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    public final static String ROLE_ADMIN = "ADMIN";
    public final static String ROLE_USER = "USER";
    @JsonIgnore
    private long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String authority;
    @JsonBackReference
    private Collection<User> users = new HashSet<>(0);

    public Role() { }

    /**
     * Getters
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    @Override
    @Column(name = "authority", nullable = false, length = 50)
    public String getAuthority() {
        return authority;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authorities")
    public Collection<User> getUsers() {
        return this.users;
    }

    /**
     * Setters
     */

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}