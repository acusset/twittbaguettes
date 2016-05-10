package twittbaguettes.models;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Twittbaguettes
 */
@Transactional
public interface MessageRepository extends CrudRepository<Message, Long>{

    public Message findByContent(String content);

}
