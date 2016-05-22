package twittbaguettes.models;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

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
    private DateTime createdDate;
    private User author;

    /**
     * Constructors
     */

    public Message() { }

    public Message(String content) {
        this.content = content;
    }

    public Message(String content, String img, String url) {
        this.content = content;
        this.img = img;
        this.url = url;
        this.createdDate = DateTime.now();
//        this.author = new User("test","test","test");
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
    public DateTime getCreatedDate() {
        return createdDate;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    public User getAuthor() {
        return author;
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

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setAuthor(User author) {
        this.author = author;
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
}
