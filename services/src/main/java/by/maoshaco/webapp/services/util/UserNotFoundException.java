package by.maoshaco.webapp.services.util;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Profile")
public class UserNotFoundException extends RuntimeException {
}
