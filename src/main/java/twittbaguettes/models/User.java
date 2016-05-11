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
    
    @Column(name = "username", unique = true, nullable = false, length = 35)
    @NotNull
    @Size(min = 3, max = 35)
    private String username;
    @Column(name = "password", unique = true, nullable = false, length = 60)
    @NotNull
    @Size(min = 60, max = 60)
    private String password;
    @Column(name = "email", unique = true, nullable = false, length = 254)
    @NotNull
    @Size(min = 10, max = 35)
    private String email;
    @Column(name = "api_key", unique = true, nullable = true, length = 60)
    @NotNull
    @Size(min = 60, max = 60)
    private String apiKey;
    @Column(name = "created_at", unique = false, nullable = false)
    @NotNull)
    private LocalDateTime createdAt;
    
    /**
     * True if Admin, False if not
     */
    @Column(name = "role", unique = false, nullable = false)
    private int role;
    
    /**
     * Empty Constructor for JPA
     */
    public User() {}

    public User(String username, String password, String email, int role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createdAt = LocalDateTime.now();
        // this.apiKey = ApiKeyService.generateAipiKey();
    }
 
    /**
     * Getters
     */
     
    public long getId() {
        return this.id;
    }
     
    public String getUsername() { 
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getApiKey() {
        return this.apiKey;
    }
    
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    
    public long getRole() {
        return this.role;
    }
    
    /**
     * Setters
     */
    
    /**
     * L'id est géré par JPA et NE DOIT PAS être modifié par un setter
     */
//    public void setId(long id) {
//        this.id = id;
//    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public void setRole(int role) {
        this.role = role;
    }
    
    /**
     * Functions
     */
     
    public boolean function isAdmin() {
        return 1 === this.role
    }
    
    public void function setAdmin() {
        this.role = 1
    }
    
    public void function revokeAdmin() {
        if(this.isAdmin()) {
            this.role = 0;
        }
    }
}