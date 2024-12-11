package map;

import java.util.ArrayList;
import java.util.Random;

import main.GameState;

public class MapGraph {

    private GameState state;
    private boolean arr[][];
    private ArrayList<Room> roomList;
    private List[] adjList;
    private Random rng;
    private int numRooms;
    private int edges;

    public MapGraph(GameState s, Random rng){
        this.rng = rng;
        state = s;
        arr = new boolean[30][30]; //max floor will lie on a 30x30 2d array
        roomList = new ArrayList<Room>();
        edges = 0;
    }

    public void makeGraph(){

        roomList.add(new Room(0));
        state.setCurrentRoom(roomList.get(0));

        numRooms = rng.nextInt(10, 12);
        adjList = new List[numRooms];
        for(int i = 0; i < numRooms; i++){
            roomList.add(new Room(rng.nextInt(1, Room.MAX_ROOMS)));
            adjList[i] = new List();
        }

        while(!this.reachable(0, numRooms-1)){
            int source = (int) (Math.random() * numRooms);
            int destination = (int) (Math.random() * numRooms);
            if (!this.hasEdge(source, destination) && adjList[source].size() < 4) {
                this.addEdge(source, destination);
                edges++;
            }
        }

        for(int i = 0; i < adjList.length; i++){
            for(int j = 0; j < adjList[i].size(); j++){
                if(j == 0) roomList.get(i).up = roomList.get(adjList[i].get(j).getValue());
                else if(j == 1) roomList.get(i).down = roomList.get(adjList[i].get(j).getValue());
                else if(j == 2) roomList.get(i).left = roomList.get(adjList[i].get(j).getValue());
                else if(j == 3) roomList.get(i).right = roomList.get(adjList[i].get(j).getValue());
            }
        }

    }

    public void addEdge(int i, int j) {
        // YOUR CODE HERE
            adjList[i].add(j);
        }
    
        public void removeEdge(int i, int j) {
        // YOUR CODE HERE
            adjList[i].remove(j);
        }
    
        public boolean hasEdge(int i, int j) {
        // YOUR CODE HERE
            if (adjList[i].find(j) != null) return true;
            else return false;
        }
    
        public List outEdges(int i) {
        // YOUR CODE HERE
            return adjList[i];
        }
    
        public List inEdges(int i) {
        // YOUR CODE HERE
            List output = new List();
            for (int j = 0; j < numRooms; j++){
                if(adjList[j].find(i) != null) output.add(j); 
            }
            return output;
        }

    private boolean reachable(int source, int destination) {
        // YOUR CODE HERE 
        // Should true if destination can be reached from source,
        // or false otherwise 
            List searchList = new List(); List seenList = new List();
            seenList.add(source);
            //fill searchList and seenList with the outEdges of the source vertex
            for (int i  = 0; i < adjList[source].size(); i++){
                Node popped = adjList[source].pop();
                seenList.add(popped.getValue());
                searchList.add(popped.getValue());
                adjList[source].add(popped.getValue());
            }
    
            //while there is still some unchecked vertex in searchList...
            while (searchList.size() > 0) {
                Node currentNode = searchList.pop();
                if(currentNode.getValue() == destination) return true;
                else{
                    for (int i  = 0; i < adjList[currentNode.getValue()].size(); i++){
                        Node popped = adjList[currentNode.getValue()].pop();
                        
                        int[] seenArr = seenList.listToArr();
                        boolean seen = false;
                        for (int j : seenArr) {
                            if (j == popped.getValue()) seen = true;
                        }
                        if(seen == false){
                            searchList.add(popped.getValue());
                        }
        
                        seenList.add(popped.getValue());
                        adjList[currentNode.getValue()].add(popped.getValue());
                    }
                }
            }
            return false;
        }

}
