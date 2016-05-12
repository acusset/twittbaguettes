package twittbaguettes.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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
    @Size(min = 5, max = 254)
    private String url;
    
    @Column(name = "img", unique = false, nullable = true, length = 254)
    @Size(min = 5, max = 254)
    private String img;

    @Column(name = "created_at", unique = false, nullable = false)
    private LocalDateTime createdAt;

    public Message() { }

    public Message(String content) {
        this.content = content;
    }

    public Message(String content, String img, String url) {
        this.content = content;
        this.img = img;
        this.url = url;
        this.createdAt = LocalDateTime.now();

    }

    public Message(String content, String img, String url, LocalDateTime createdAt) {
        this.content = content;
        this.img = img;
        this.url = url;
        this.createdAt = createdAt;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

//    public void setId(long id) {
//         this.id = id;
//    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public void setUrl(String content) {
        this.url = url;
    }
    
    public void setImg(String img) {
        this.img = img;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
