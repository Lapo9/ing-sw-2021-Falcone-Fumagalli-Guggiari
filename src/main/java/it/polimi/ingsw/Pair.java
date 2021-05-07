package it.polimi.ingsw;

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
