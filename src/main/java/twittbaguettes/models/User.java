package twittbaguettes.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Antoine Cusset
 * @TODO ajouter des constantes de classe pour les roles
 */
 
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String username;
    private String password;
    private String email;
    private String apiKey;
    private LocalDateTime createdAt;
    
    // Admin ou pas admin
    private String role;
    
    /**
     * Empty Constructor for JPA usage
     */
    public User() {}
 
    /**
     * 
     */
    public User(String username, String password, String email, LocalDateTime createdAt) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
    }
    
    
    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createdAt = LocalDateTime.now();
    }
 
    public getUsername() { return this.username }
    
    public getPassword() { return this.password }
    
    public getEmail() { return this.email }
    
    public getApiKey() { return this.apiKey }
    
    public getCreatedAt() { return this.createdAt }
    
    
    public setUsername() { this.username = username }
    
    public setPassword() { this.password = password }
    
    public setEmail() { this.email = email }
    
    public setApiKey() { this.apiKey = apiKey }
    
    public setDtCreate() { this.createdAt = createdAt }
    
    public long getId() { return id; }

    // L'id est géré par JPA et ne doit pas être setter
    // public void setId(long id) { this.id = id; }
    
}