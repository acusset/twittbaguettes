package twittbaguettes.repositories;

import org.springframework.data.repository.CrudRepository;
import twittbaguettes.models.Role;

/**
 * Twittbaguettes
 *
 * @author Antoine on 23/05/2016
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findById(Long id);

    Role findByAuthority(String role);

}