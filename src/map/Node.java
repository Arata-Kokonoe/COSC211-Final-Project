package map;

public class Node {
    private int value;
    public Node next, prev;

    public Node(int v) {
        value = v;
        next = null;
        prev = null;
    }

    public int getValue(){
        return value;
    }
}
