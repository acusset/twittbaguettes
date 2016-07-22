package twittbaguettes.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import twittbaguettes.models.Message;

/**
 * Message Repository
 * @author Antoine Cusset
 */
public interface MessageRepository extends PagingAndSortingRepository<Message, Long>{

    Message findByContent(String content);

}
