package twittbaguettes.models;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;

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

    @Column(name = "password", unique = false, nullable = false, length = 60)
    @NotNull
    @Size(min = 10, max = 60)
    private String password;

    @Column(name = "email", unique = true, nullable = false, length = 254)
    @NotNull
    @Size(min = 10, max = 35)
    private String email;

    @Column(name = "api_key", unique = true, nullable = true, length = 60)
    @Size(min = 0, max = 60)
    private String apiKey = "";

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDate;

    /**
     * True if Admin, False if not
     */
    @Column(name = "role", unique = false, nullable = false)
    private int role;

    /**
     * Empty Constructor for JPA
     */
    public User() { }

    public User(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = 0;
        this.createdDate = DateTime.now();
        // TODO : générer la clé aléatoirement dans un KeyService
        this.apiKey = new BigInteger(60, new SecureRandom()).toString(60);
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

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
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

    public void setRole(int role) {
        this.role = role;
    }

    /**
     * Functions
     */

    /**
     * Test if user is admin
     * @return boolean
     */
//    public boolean isAdmin() {
//        return 1 == this.role;
//    }

    /**
     * Promote an user to admin
     */
    public void promoteAdmin() {
        this.role = 1;
    }

    /**
     * Revoke admin rights from an user
     */
    public void revokeAdmin() {
        if (this.role == 1) {
            this.role = 0;
        }
    }
}