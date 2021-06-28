package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * Allows to receive the status of every object which implements this interface in the form of an ArrayList of Integer
 */
public interface HasStatus {

    ArrayList<Integer> getStatus();

}
