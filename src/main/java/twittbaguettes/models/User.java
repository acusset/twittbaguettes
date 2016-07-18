package twittbaguettes.models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Antoine Cusset
 */

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements UserDetails {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean enabled;

    @JsonIgnore
    private boolean accountNonExpired;
    @JsonIgnore
    private boolean accountNonLocked;
    @JsonIgnore
    private boolean credentialsNonExpired;

    private String email;
    @JsonManagedReference
    private Collection<Role> authorities = new HashSet<>(0);
    @JsonBackReference
    private Set<Message> messages = new HashSet<>(0);
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String apiKey = "";
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DateTime updatedAt;

    /**
     * Empty Constructor for JPA
     */
    public User() {
    }

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

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "updated_at")
    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", nullable = false, updatable = false)})
    public Collection<Role> getAuthorities() {
        return this.authorities;
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

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAuthorities(Collection<Role> authorities) {
        this.authorities = authorities;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
    /**
     * Override functions
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Public functions
     */

    /**
     * @return true if user has at least one authorities
     */
    public boolean hasRole() {
        return 0 < this.authorities.size();
    }

    /**
     * @param role Role à chercher
     * @return true if user has specific authorities
     */
    public boolean hasRole(String role) {
        boolean tmp = false;
        for (Role r : this.authorities) {
            if (r.getAuthority().equals(role)) {
                tmp = true;
                break;
            }
        }
        return tmp;
    }

    public void addRole(Role role) {
        this.authorities.add(role);
    }

    /**
     * Generate a new API KEY
     *
     * @return String new Api Key
     */
    public String generateApiKey() {
        this.apiKey = new BigInteger(60, new SecureRandom()).toString(60);
        return this.apiKey;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {

    }
}