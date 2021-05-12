package it.polimi.ingsw.view;

import it.polimi.ingsw.view.cli.UserInterpreter;
import it.polimi.ingsw.view.cli.exceptions.ViewException;
import it.polimi.ingsw.view.gui.MessageType;

public interface Screen {

    public void show(String sceneID) throws ViewException;

    public void setMessage(String message, MessageType type);

    public void refresh();

    public void setPlayers(String players);

}
