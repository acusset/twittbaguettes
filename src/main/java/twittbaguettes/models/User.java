package twittbaguettes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Antoine Cusset
 * @TODO ajouter des constantes de classe pour les roles
 */

@Entity
@Table(name = "users")
public class User {

    private long id;
    private String username;
    private String password;
    private boolean enabled;
    private String email;
    private Set<Role> role = new HashSet<Role>(0);
    private String apiKey = "";

    private DateTime createdAt;

    /**
     * Empty Constructor for JPA
     */
    public User() { }

    public User(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = true;
        this.createdAt = DateTime.now();
        // TODO : générer la clé aléatoirement dans un KeyService
        this.apiKey = new BigInteger(60, new SecureRandom()).toString(60);
    }

    /**
     * Getters
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return this.id;
    }

    @Column(name = "username", unique = true, nullable = false, length = 35)
    public String getUsername() {
        return this.username;
    }

    @Column(name = "password", unique = false, nullable = false, length = 60)
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    @Column(name = "email", unique = true, nullable = false, length = 254)
    public String getEmail() {
        return this.email;
    }

    @Column(name = "api_key", unique = true, nullable = true, length = 60)
    @JsonIgnore
    public String getApiKey() {
        return this.apiKey;
    }

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "created_at", unique = false, nullable = false)
    public DateTime getCreatedAt() {
        return createdAt;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Role> getRole() {
        return this.role;
    }

     /**
     * Setters
     */

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    /**
     * Public functions
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

}