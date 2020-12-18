package com.example.task01;

import java.util.function.BiConsumer;

public class Pair<T, U> {
    private final T first;
    private final U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    public T getFirst() {
        /*if (this.first == null) {
            throw new NoSuchElementException("No first value present");
        }*/
        return this.first;
    }

    public U getSecond() {
        /*if (this.second == null) {
            throw new NoSuchElementException("No second value present");
        }*/
        return this.second;
    }

    public void ifPresent(BiConsumer<? super T, ? super U> action) {
        if (this.first != null && this.second != null) {
            action.accept(this.first, this.second);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        boolean firstEqual;
        if (this.first != null && ((Pair<T, U>) obj).first != null) {
            firstEqual = this.first.equals(((Pair<T, U>) obj).first);
        } else firstEqual = this.first == null && ((Pair<T, U>) obj).first == null;
        boolean secondEqual;
        if (this.second != null && ((Pair<T, U>) obj).second != null) {
            secondEqual = this.second.equals(((Pair<T, U>) obj).second);
        } else secondEqual = this.second == null && ((Pair<T, U>) obj).second == null;
        return firstEqual && secondEqual;
    }

    @Override
    public int hashCode() {
        int firstHashCode = 0;
        if (this.first != null) {
            firstHashCode = this.first.hashCode();
        }
        int secondHashCode = 0;
        if (this.second != null) {
            secondHashCode = this.second.hashCode();
        }
        return firstHashCode ^ secondHashCode;
    }
}
