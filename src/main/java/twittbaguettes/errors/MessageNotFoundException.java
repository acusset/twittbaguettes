package twittbaguettes.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Antoine on 24/07/2016.
 * Thrown when message is not found in database
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Message not found")
public class MessageNotFoundException extends RuntimeException {

    public MessageNotFoundException(String message) {
        super(message);
    }
}
