package it.polimi.ingsw.view.cli;

public interface Viewable {

    /**
     * Updates every viewable using array from the getStatus
     * @param update array of int used to update the Viewable(s)
     * @throws NoSuchMethodException if the class does not implement update
     */
    public default void update(int[] update) throws NoSuchMethodException {
        throw new NoSuchMethodException("This class doesn't implement this method, try to call the other overload");
    }

    /**
     * Updates using some offlineInfo
     * @param update array of int used to update the Viewable(s)
     * @param oi info about the player and his dashboard
     * @throws NoSuchMethodException if the class does not implement update
     */
    public default void update(int[] update, OfflineInfo oi) throws NoSuchMethodException {
        update(update);
    }

    /**
     * Allows to transform every viewable in a String ready to be printed
     * @return the viewable in String type
     */
    public String toString();
}
