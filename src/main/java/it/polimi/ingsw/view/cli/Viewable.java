package it.polimi.ingsw.view.cli;

public interface Viewable {

    public default void update(int[] update) throws NoSuchMethodException {
        throw new NoSuchMethodException("This class doesn't implement this method, try to call the other overload");
    }

    public default void update(int[] update, OfflineInfo oi) throws NoSuchMethodException {
        update(update);
    }

    public String toString();
}
