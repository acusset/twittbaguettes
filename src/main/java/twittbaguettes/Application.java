package twittbaguettes;

/**
 * Twittbaguettes
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import twittbaguettes.models.Message;
import twittbaguettes.models.Role;
import twittbaguettes.models.User;
import twittbaguettes.repositories.MessageRepository;
import twittbaguettes.repositories.RoleRepository;
import twittbaguettes.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.security.SecureRandom;


@SpringBootApplication
public class Application {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        roleRepository.deleteAll();
        userRepository.deleteAll();
        messageRepository.deleteAll();

        User admin = new User("Admin", "admin@gmail.com", "password1");
        User user = new User("User", "user@gmail.com", "password2");

        userRepository.save(admin);
        roleRepository.save(new Role(admin, Role.ROLE_ADMIN));

        userRepository.save(user);
        roleRepository.save(new Role(userRepository.findByUsername("User"), Role.ROLE_USER));

        admin = userRepository.findByUsername("Admin");
        user = userRepository.findByUsername("User");

        messageRepository.save(new Message("Salut, ça va ?", admin));
        messageRepository.save(new Message("message créer en trouvant un user tout seul", user));

        // Ajoute des messages aléatoires
        for (int i = 50; i <= 250; i++) {
            String texte = new BigInteger(i, new SecureRandom()).toString(i);
            if (i % 2 == 0) {
                messageRepository.save(new Message(texte, admin));
            } else {
                messageRepository.save(new Message(texte, user));
            }
        }
    }

}