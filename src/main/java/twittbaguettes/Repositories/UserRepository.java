package twittbaguettes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import twittbaguettes.models.User;

/**
 * Repository Interface
 * Hibernate se charge d'implementer les fonctions de recherche simples comme celles là
 * @author Antoine Cusset
 */
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    
    User findByUsername(String username);
    
    User findByEmail(String email);
    
    User findByApiKey(String apiKey);
}