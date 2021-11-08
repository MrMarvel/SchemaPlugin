package io.github.mrmarvel.schemabuild.test19;

/**
 * Called when an NBT block has the wrong id
 */
public class WrongIdException extends Exception {

    public WrongIdException(String message) {
        super(message);
    }
}
