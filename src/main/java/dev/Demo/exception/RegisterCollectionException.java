package dev.Demo.exception;

import java.io.Serial;

public class RegisterCollectionException extends Exception {

    @Serial
    private static  final  long serialVersionUID = 1L;

    public RegisterCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Register with " + id + "not found!";
    }

    public static String RegisterAlreadyExists() {
        return "Register with given name already exists";
    }
}
