package map;

public class Node<T> {
    private T value;
    public Node<T> next, prev;

    public Node(T v) {
        value = v;
        next = null;
        prev = null;
    }

    public T getValue(){
        return value;
    }
}
