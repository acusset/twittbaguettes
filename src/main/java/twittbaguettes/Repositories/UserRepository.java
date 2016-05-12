package twittbaguettes.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import twittbaguettes.models.User;

/**
 * Repository Interface
 * TODO : implémenter une classe et les fonctions ci-dessous
 * @author Antoine Cusset
 */
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    
    public User findByUsername(String username);
    
    public User findByEmail(String email);
    
//    public User findByApiKey(String apiKey);
}