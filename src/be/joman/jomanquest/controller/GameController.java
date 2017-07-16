package be.joman.jomanquest.controller;

import be.joman.jomanquest.domain.Player;
import be.joman.jomanquest.domain.Room;

/**
 * Created by Freddy on 15-7-2017.
 */
public class GameController {

    private Room currentRoom;

    private Player player;

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
