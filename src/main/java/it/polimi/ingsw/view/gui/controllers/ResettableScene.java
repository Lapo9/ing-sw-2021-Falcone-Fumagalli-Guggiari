package it.polimi.ingsw.view.gui.controllers;


/**
 * A ResettableScene is a scene that can be be modified by the user/server, and can be took back to an initial state
 */
public interface ResettableScene {

    /**
     * Takes the scene back to the original state defined by the scene itself
     */
    public void reset();


    /**
     * Modify the scene using the info in the argument
     * @param depots How to modify the scene. In particular it is very often used to show what depots are active, hence the name of the argument.
     */
    public void setActive(String... depots);

}
