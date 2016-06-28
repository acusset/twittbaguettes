package twittbaguettes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import twittbaguettes.repositories.UserRepository;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Message Model
 *
 * @Column est destiné à la base de données
 * @NotNull et @Size pour les validateurs Spring (à venir...)
 */
@Entity
@Table(name = "messages")
public class Message implements Serializable {

    private long id;
    private String content;
    private String url;
    private String img;
    private DateTime createdAt;
    @JsonIgnore
    private User user;

    private UserRepository userRepository;

    /**
     * Constructors
     */

    public Message() { }

    public Message(String content, User user) {
        this.content = content;
        this.createdAt = DateTime.now();
        this.user = user;
        this.img = null;
        this.url = null;
    }

    public Message(String content) {
        this.content = content;
        this.createdAt = DateTime.now();
        this.user = userRepository.findOne(new Long(1));
        this.img = null;
        this.url = null;
    }

    public Message(String content, String img, String url) {
        this.content = content;
        this.img = img;
        this.url = url;
        this.createdAt = DateTime.now();
//        this.user = user;
    }

    /**
     * Getters
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    @Column(name = "content", unique = false, nullable = false, length = 254)
    public String getContent() {
        return content;
    }

    @Column(name = "url", unique = false, nullable = true, length = 254)
    public String getUrl() {
        return url;
    }

    @Column(name = "img", unique = false, nullable = true, length = 254)
    public String getImg() {
        return img;
    }

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "created_at", unique = false, nullable = false)
    public DateTime getCreatedAt() {
        return createdAt;
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

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * TODO : déplacer dans un MessageService
     * Désinfecte le message avant de l'enregistrer
     * - Suppression des espaces superflux
     * - Injection de code
     * - Echapement des characteres
     * - Etc.
     */
    private void sanitize() {

    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", img='" + img + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                '}';
    }
}
