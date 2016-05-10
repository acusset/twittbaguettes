package twittbaguettes.repositories;

import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

/**
 * Twittbaguettes
 */
 
@Transactional
public interface UserRepository extends CrudRepository<User,Long>{
    
    public Message findByUsername(String username);
    
    public Message findByEmail(String email);
    
    public Message findByApiKey(String apiKey);
}