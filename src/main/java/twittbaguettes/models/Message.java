package twittbaguettes.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import twittbaguettes.repositories.UserRepository;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Message Model
 */
@Entity
@Table(name = "messages")
public class Message implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    /**
     * Le contenu du message / texte
     */
    private String content;

    /**
     * Si un message contient un lien externe
     */
    private String url;

    /**
     * Si on veut ajouter une image au message
     */
    private String img;

    /**
     * Timestamp de création
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DateTime createdAt;

    /**
     * Timestamp d'édition
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DateTime updatedAt;

    /**
     * L'auteur du message
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonManagedReference
    private User user;

    private UserRepository userRepository;

    /**
     * Constructors
     */

    public Message() {
    }

    public Message(String content, User user) {
        this.content = content;
        this.createdAt = DateTime.now();
        this.user = user;
        this.img = null;
        this.url = null;
    }

    public Message(String content, String img, String url) {
        this.content = content;
        this.img = img;
        this.url = url;
        this.createdAt = DateTime.now();
    }

    /**
     * Getters
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    @Column(name = "content", nullable = false, length = 254)
    public String getContent() {
        return content;
    }

    @Column(name = "url", length = 254)
    public String getUrl() {
        return url;
    }

    @Column(name = "img", length = 254)
    public String getImg() {
        return img;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    /**
     * Setters
     */

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Désinfecte le message avant de l'enregistrer
     * - Suppression des espaces superflux
     * - Injection de code
     * - Echapement des characteres
     * - Etc.
     */
    private void sanitize() {

    }

    /**
     * TODO : error avec message.getUser() qui renvoit systématiquement le 1er user trouvé en base
     * @param user
     * @return
     */
    public boolean isAuthor(User user) {
//        return this.user.equals(user);
//        return this.user.getId() == user.getId();
        return true;
    }
}
