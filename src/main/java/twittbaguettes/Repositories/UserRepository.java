package twittbaguettes.repositories;

import twittbaguettes.models.User;

import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

/**
 * Repository Interface
 * TODO : implémenter une classe et les fonctions ci-dessous
 * @author Antoine Cusset
 */
 
@Transactional
public interface UserRepository extends CrudRepository<User,Long>{
    
    public User findByUsername(String username);
    
    public User findByEmail(String email);
    
    public User findByApiKey(String apiKey);
    
    public List<User> findByRole(int role);
    
    public List<User> findAdmins()
}