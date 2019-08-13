package ru.ogrezem.springkotlinwithvue1.exception.message;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MessageAlreadyExistsException extends RuntimeException {

    public MessageAlreadyExistsException() {
        super();
    }

    public MessageAlreadyExistsException(String message) {
        super(message);
    }
}
