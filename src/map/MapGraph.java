package map;

import main.GameState;

public class MapGraph {


    private GameState state;
    private boolean arr[][];
    private List<Room> roomList;

    public MapGraph(GameState s){
        state = s;
        arr = new boolean[30][30]; //max floor will lie on a 30x30 2d array
        roomList = new List<Room>();
    }

    public void makeGraph(String floor){

        roomList.add(new Room(0));
        state.setCurrentRoom(roomList.getFirst().getValue());

        /*
        int roomAmount = state.rng.nextInt(7, 10);
        for(int i = 0; i < roomAmount; i++){
            roomList.add(new Room(state.rng.nextInt(1, Room.MAX_ROOMS)));
        }
        */

    }

}
