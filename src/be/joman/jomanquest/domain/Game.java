package be.joman.jomanquest.domain;

import be.joman.jomanquest.controller.MusicController;
import be.joman.jomanquest.domain.action.ActionRule;
import be.joman.jomanquest.domain.action.ActionType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Freddy on 15-7-2017.
 */
public class Game extends Item implements Serializable {

    private List<Room> rooms;

    private Room currentRoom;

    private Player player;

    private transient MusicController musicController;

    public Game(String name, String description, String info) {
        super(name, description, info, false, false, false);

        getActionRules().add(new ActionRule(ActionType.SAVE, this ));
        getActionRules().add(new ActionRule(ActionType.LOAD, this ));
        getActionRules().add(new ActionRule(ActionType.MUSIC, this ));
        getActionRules().add(new ActionRule(ActionType.MUTE, this ));
        getActionRules().add(new ActionRule(ActionType.QUIT, this ));

        musicController = new MusicController();
    }

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

    public List<Room> getRooms() {
        if(rooms == null){
            rooms = new ArrayList<>();
        }
        return rooms;
    }

    public void saveGame(){
        try {
            FileOutputStream fileOut = new FileOutputStream("E:/tmp/JoManQuest.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Game saved!");
        }catch(IOException i) {
            System.out.println("Can't seem to save your game, I'm sorry, continue playing until you have reached the end");
            i.printStackTrace();
        }
    }

    public void loadGame(){
        try {
            FileInputStream fileIn = new FileInputStream("/tmp/JoManQuest.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Game game = (Game) in.readObject();
            this.currentRoom = game.getCurrentRoom();
            this.rooms = game.getRooms();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            System.out.println("No Saved Game Found");
            return;
        }catch(ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
    }

    public void playMusic(){
        musicController.play();
    }

    public void muteMusic(){
        musicController.stop();
    }

    public void quit() {
        saveGame();
        System.out.println("Your status has been save, if you want to continue where you left of last time, type load at startup.");
        System.out.println("Bye for now, hope to see you soon.");
    }

    @Override
    public void inspect(){
        System.out.println(getName() + ": " + getDescription());
        System.out.println("welcome: ");
        player.inspect();
        System.out.println("You are now in the: ");
        currentRoom.inspect();
    }
}
