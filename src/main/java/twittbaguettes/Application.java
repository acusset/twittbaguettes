package twittbaguettes;

/**
 * Twittbaguettes
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import twittbaguettes.models.Message;
import twittbaguettes.models.Role;
import twittbaguettes.models.User;
import twittbaguettes.repositories.MessageRepository;
import twittbaguettes.repositories.RoleRepository;
import twittbaguettes.repositories.UserRepository;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class Application {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        roleRepository.deleteAll();
        userRepository.deleteAll();
        messageRepository.deleteAll();

        User admin = new User("Admin", "admin@gmail.com", passwordEncoder.encode("password1"));
        User antoine = new User("Antoine", "antoine@gmail.com", passwordEncoder.encode("password1"));
        User jolan = new User("Jolan", "jolan@gmail.com", passwordEncoder.encode("password1"));
        User alexandre = new User("Alexandre", "alexandre@gmail.com", passwordEncoder.encode("password1"));

        Role userRole = new Role();
        userRole.setAuthority(Role.ROLE_USER);
        Role adminRole = new Role();
        adminRole.setAuthority(Role.ROLE_ADMIN);
        roleRepository.save(userRole);
        roleRepository.save(adminRole);

        admin.addRole(adminRole);
        antoine.addRole(userRole);
        jolan.addRole(userRole);
        alexandre.addRole(adminRole);

//        antoine.addFollower(jolan);
//        alexandre.addFollowing(antoine);
//        alexandre.addFollower(antoine);

        userRepository.save(admin);
        userRepository.save(antoine);
        userRepository.save(jolan);
        userRepository.save(alexandre);

        admin = userRepository.findByUsername("Admin");
        antoine = userRepository.findByUsername("Antoine");

        messageRepository.save(new Message("Salut, ça va ?", admin));
        messageRepository.save(new Message("message créé en trouvant un antoine tout seul", antoine));
        messageRepository.save(new Message("Bienvenue sur Twittbaguettes", jolan));

        // Ajoute des messages aléatoires
//        for (int i = 50; i <= 250; i++) {
//            String texte = new BigInteger(i, new SecureRandom()).toString(i);
//            if (i % 2 == 0) {
//                messageRepository.save(new Message(texte, admin));
//            } else {
//                messageRepository.save(new Message(texte, antoine));
//            }
//        }
    }

}