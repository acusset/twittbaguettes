package twittbaguettes.models;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Message Model
 * @Column est destiné à la base de données
 * @NotNull et @Size pour les validateurs Spring (à venir...)
 */
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "content", unique = false, nullable = false, length = 254)
    @NotNull
    @Size(min = 1, max = 254)
    private String content;
    
    @Column(name = "url", unique = false, nullable = true, length = 254)
    private String url;
    
    @Column(name = "img", unique = false, nullable = true, length = 254)
    private String img;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDate;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User author;

    public void setAuthor(User author) {
        this.author = author;
    }

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

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
    
    public String getImg() {
        return img;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public void setUrl(String content) {
        this.url = url;
    }
    
    public void setImg(String img) {
        this.img = img;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
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
