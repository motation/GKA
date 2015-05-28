package algorithms;

/**
 * Created by sparky on 29.05.2015.
 */
public interface PrioQueue<T> {
    void add(T t);
    T remove();
    boolean contains(T t);
}
