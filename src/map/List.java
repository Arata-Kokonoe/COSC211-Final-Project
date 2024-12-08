package map;

public class List<T> {
    // YOUR CODE HERE
    private Node<T> first;
    private Node<T> last;
    private int count;

    public List() {
        first = null;
        last = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public void add(T toAdd) {
        Node<T> nodeToAdd = new Node<T>(toAdd);
        
        //if there are no nodes, make it the first and last node as it is now the only node
        if (count == 0) {
            first = nodeToAdd;
            last = nodeToAdd;
        }

        //else, add it to the end
        else{
            last.next = nodeToAdd;
            nodeToAdd.prev = last;
            last = nodeToAdd;
        }

        count++;
    }

    public Node<T> get(int toGet){
        Node<T> current = first;
        int i = 0;

        while(i != toGet){
            current = current.next;
            i++;
        }

        return current;
    }

    public Node<T> find(T toFind) {
        Node<T> current = first;
        
        while(current != null && current.getValue() != toFind){
            current = current.next;
        }

        return current;
    }

    /*
    public void remove(int toRemove) {
        Node nodeToRemove = find(toRemove); //first, find the node with the string to remove
        
        //if there is more than just one node...
        if(count > 1){
            //if its the last, remove from list and make its prev the new last
            if(nodeToRemove == last){
                nodeToRemove.prev.next = null;
                last = nodeToRemove.prev;
            }

            //if its the first, remove from list and make its next the new first
            else if(nodeToRemove == first){
                nodeToRemove.next.prev = null;
                first = nodeToRemove.next;
            }

            //otherwise, just remove from list
            else{
                nodeToRemove.next.prev = nodeToRemove.prev;
                nodeToRemove.prev.next = nodeToRemove.next;
            }
        }
        //otherwise its the only node in the list
        else{
            first = null;
            last = null;
        }
        count--;
    }*/

    public Node<T> pop() {
        Node<T> nodeToRemove = first;
        
        //if there is more than just one node...
        if(count > 1){
            nodeToRemove.next.prev = null;
            first = nodeToRemove.next;
        }
        //otherwise its the only node in the list
        else{
            first = null;
            last = null;
        }
        count--;

        return nodeToRemove;
    }

    public Node<T> getFirst(){
        return first;
    }

    /*public T[] listToArr(){
        T[] outputArr = new T[count];
        Node<T> current = first;
        int i = 0;
        while (current != null){
            outputArr[i] = current.getValue();
            i++;
            current = current.next;
        }
        return outputArr;
    }*/

    public void print(){
        Node<T> current = first;
        while (current != null){
            System.out.print(current.getValue() + ", ");
            current = current.next;
        }
        System.out.println();
    }
}
