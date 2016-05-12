package twittbaguettes.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import twittbaguettes.models.Message;

/**
 * Message Crud Repository
 * TODO : implémenter une classe et les fonctions ci-dessous
 * @author Antoine Cusset
 */
public interface MessageRepository extends PagingAndSortingRepository<Message, Long>{

    public Message findByContent(String content);

}
