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

        User antoine = new User("Antoine","antoine.cusset@gmail.com","password1");
        User user = new User("User","user@gmail.com","password2");

        userRepository.save(antoine);
        roleRepository.save(new Role(userRepository.findByUsername("Antoine"), Role.ROLE_ADMIN));

        userRepository.save(user);
        roleRepository.save(new Role(userRepository.findByUsername("User"), Role.ROLE_USER));

//        Message message = new Message("Salut, ça va ?", userRepository.findByUsername("Antoine"));
//        messageRepository.save(message);
//        message = new Message("Salut, comment ça va ?", userRepository.findByUsername("User"));
//        messageRepository.save(message);
    }

}