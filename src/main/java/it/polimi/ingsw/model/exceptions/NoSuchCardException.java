package it.polimi.ingsw.model.exceptions;

/**
 * Exception thrown when we call a method on a not existing card
 */
public class NoSuchCardException extends Exception{
    public NoSuchCardException(String message){
        super(message);
    }
}
