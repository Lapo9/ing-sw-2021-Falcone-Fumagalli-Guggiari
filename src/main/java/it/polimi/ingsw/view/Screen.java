package it.polimi.ingsw.view;

import it.polimi.ingsw.view.cli.UserInterpreter;
import it.polimi.ingsw.view.cli.exceptions.ViewException;

public interface Screen {

    public void show(String sceneID) throws ViewException;

    public void setMessage(String message);

    public void refresh();

    public void setPlayers(String players);

}
