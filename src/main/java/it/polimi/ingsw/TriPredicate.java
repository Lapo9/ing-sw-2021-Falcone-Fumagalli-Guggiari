package it.polimi.ingsw;

import java.util.function.BiPredicate;

@FunctionalInterface
public interface TriPredicate<A, B, C> {

    boolean test(A a, B b, C c);

    default TriPredicate<A, B, C> and(TriPredicate<A, B, C> other){
        return ((a, b, c) -> this.test(a, b, c) && other.test(a, b, c));
    }

    default TriPredicate<A, B, C> or(TriPredicate<A, B, C> other){
        return ((a, b, c) -> this.test(a, b, c) || other.test(a, b, c));
    }

    default TriPredicate<A, B, C> negate(){
        return ((a, b, c) -> !this.test(a, b, c));
    }

}
