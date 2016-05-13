package twittbaguettes.models;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

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

    public Message() { }

    public Message(String content) {
        this.content = content;
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
    
    /**
     * TODO : déplacer dans un MessageService
     * Désinfecte le message avant de l'enregistrer
     * - Suppression des espaces superflux
     * - Injection de code
     * - Echapement des characteres
     * - Etc.
     */
    private function sanitize() {
        
    }
}
