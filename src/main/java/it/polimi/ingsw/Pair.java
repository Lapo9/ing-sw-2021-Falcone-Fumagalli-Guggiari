package it.polimi.ingsw;

/**
 * Helper class defining Pair
 * @param <F> first
 * @param <S> second
 */
public class Pair<F, S> {

    public Pair(F first, S second){
        this.first = first;
        this.second = second;
    }

    public Pair(Pair<F, S> other){
        this.first = other.first;
        this.second = other.second;
    }

    public F first;
    public S second;
}
