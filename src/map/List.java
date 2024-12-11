package map;

public class List {
    // YOUR CODE HERE
    private Node first;
    private Node last;
    private int count;

    public List() {
        first = null;
        last = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public void add(int toAdd) {
        Node nodeToAdd = new Node(toAdd);
        
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

    public Node get(int toGet){
        Node current = first;
        int i = 0;

        while(i != toGet){
            current = current.next;
            i++;
        }

        return current;
    }

    public Node find(int toFind) {
        Node current = first;
        
        while(current != null && current.getValue() != toFind){
            current = current.next;
        }

        return current;
    }

    
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
    }

    public Node pop() {
        Node nodeToRemove = first;
        
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

    public Node getFirst(){
        return first;
    }

    public int[] listToArr(){
        int[] outputArr = new int[count];
        Node current = first;
        int i = 0;
        while (current != null){
            outputArr[i] = current.getValue();
            i++;
            current = current.next;
        }
        return outputArr;
    }

    public void print(){
        Node current = first;
        while (current != null){
            System.out.print(current.getValue() + ", ");
            current = current.next;
        }
        System.out.println();
    }
}
