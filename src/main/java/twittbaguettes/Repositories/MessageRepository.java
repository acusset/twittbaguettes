package twittbaguettes.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

/**
 * Message Crud Repository
 */
@Transactional
public interface MessageRepository extends PagingAndSortingRepository<Message, Long>{

    public Message findByContent(String content);

}
