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
 * TODO : compteur d'édition, compteur de likes, mention & tags
 */
@Entity
@Table(name = "messages")
public class Message implements Serializable {

    @Transient
    private UserRepository userRepository;

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

    /**
     * Empty constructor for JPA
     */
    public Message() {}

    /**
     * Constructor
     * @param content Le contenu du message
     * @param user L'auteur du message
     */
    public Message(String content, User user) {
        this.content = content;
        this.createdAt = DateTime.now();
        this.user = user;
        this.img = null;
        this.url = null;
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

    @ManyToOne(fetch = FetchType.EAGER)
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
     * Teste si l'utisateur est l'auteur du message
     *
     * @param user L'utilisateur à comparer
     * @return true s'il s'agit de l'auteur, false sinon
     */
    public boolean isAuthor(User user) {
        return this.getUser().getId() == user.getId();
    }
}
