package twittbaguettes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Antoine Cusset
 */

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean enabled;
    private String email;
    private Set<Role> role = new HashSet<>(0);
    @JsonIgnore
    private Set<Message> messages = new HashSet<>(0);
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String apiKey = "";
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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

    @Column(name = "password", nullable = false, length = 60)
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

    @Column(name = "api_key", unique = true, length = 60)
    @JsonIgnore
    public String getApiKey() {
        return this.apiKey;
    }

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "created_at", nullable = false)
    public DateTime getCreatedAt() {
        return createdAt;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Role> getRole() {
        return this.role;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Message> getMessages() {
        return messages;
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

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    /**
     * Public functions
     */

    /**
     * @return true if user has at least one role
     */
    public boolean hasRole() {
        return 0 < this.role.size();
    }

    /**
     * @param role Role à chercher
     * @return true if user has specific role
     */
    public boolean hasRole(String role) {
        boolean tmp = false;
        for(Role r : this.role) {
            if(r.getRole().equals(role)) {
                tmp = true;
                break;
            }
        }
        return tmp;
    }

    /**
     * Generate a new API KEY
     * @return
     */
    public String generateApiKey() {
        this.apiKey = new BigInteger(60, new SecureRandom()).toString(60);
        return this.apiKey;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                '}';
    }
}