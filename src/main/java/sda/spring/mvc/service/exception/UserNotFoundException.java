package sda.spring.mvc.service.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User was not found !");
    }
}
