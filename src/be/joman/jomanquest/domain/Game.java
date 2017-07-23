package be.joman.jomanquest.domain;

import be.joman.jomanquest.controller.ActionController;
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

    private final static String INFO = "menu: " +
                                        "\n Inspect (object): will give you information on whatever object you want to inspect." +
                                        "\n music: will play music. " +
                                        "\n mute: will stop the music. " +
                                        "\n open object: will try to open an object. " +
                                        "\n unlock object: will try to unlock an object. " +
                                        "\n info (object): will give you a bit more info, it is also the way to call this menu again. " +
                                        "\n save: will save the game. " +
                                        "\n load: will load the last saved game. " +
                                        "\n quit: will save and quit. " +
                                        "\n these are not all commands just play with it and see what is possible, have fun";

    private List<Room> rooms;

    private Room currentRoom;

    private Player player;

    private transient MusicController musicController;

    public Game() {
        super("JoManQuest", "Welcome in the adventurous world of JoMan. You will be helping our main character Bob, He is desperately trying to escape this old mansion.", INFO, false, false);

        addActionRules(new ActionRule(ActionType.SAVE, this));
        addActionRules(new ActionRule(ActionType.LOAD, this));
        addActionRules(new ActionRule(ActionType.MUSIC, this));
        addActionRules(new ActionRule(ActionType.MUTE, this));
        addActionRules(new ActionRule(ActionType.QUIT, this));

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
        if (rooms == null) {
            rooms = new ArrayList<>();
        }
        return rooms;
    }

    public void saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream("E:/tmp/JoManQuest.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Game saved!");
        } catch (IOException i) {
            System.out.println("Can't seem to save your game, I'm sorry, continue playing until you have reached the end");
            i.printStackTrace();
        }
    }

    public void loadGame() {
        try {
            FileInputStream fileIn = new FileInputStream("/tmp/JoManQuest.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Game loadGame = (Game) in.readObject();
            this.currentRoom = loadGame.getCurrentRoom();
            this.rooms = loadGame.getRooms();
            this.player = loadGame.getPlayer();
            in.close();
            fileIn.close();
            System.out.println("Game loaded!");
        } catch (IOException i) {
            System.out.println("No Saved Game Found");
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
    }

    public void playMusic() {
        musicController.play();
    }

    public void muteMusic() {
        musicController.stop();
    }

    public void quit() {
        saveGame();
        System.out.println("Your status has been saved, if you want to continue where you left of last time, type load at startup.");
        System.out.println("Bye for now, hope to see you soon.");
    }

    @Override
    public void inspect() {
        System.out.println(getName() + ": " + getDescription());
        System.out.println("welcome: " + player.getName());
        currentRoom.inspect();
    }

    public List<Item> findObjects(final List<String> words) {
        List<Item> objects = new ArrayList<>();

        if (words.contains(player.getLowerCaseName())) objects.add(player);
        if (words.contains(currentRoom.getLowerCaseName())) objects.add(currentRoom);
        objects.addAll(player.findObjects(words));
        objects.addAll(currentRoom.findObjects(words));

        return objects;
    }

    public boolean executeValidActionRules(List<String> words) {
        boolean exit = false;

        //Find relevant verbs and available objects in the sentence
        List<ActionType> actionTypes = ActionController.getInstance().findActionTypes(words);
        List<Item> objects = findObjects(words);

        //Find possible action Rules and execute them
        List<ActionRule> actionRules = findActionRules(actionTypes, objects);

        if(actionRules.isEmpty()){
            System.out.println("I'm sorry, but that is simply not allowed.");
        } else if(actionRules.size() > 1){
            System.out.println("I'm sorry, to much information, try to speak in simple sentences, I'm a little bit slow.");
        } else {
            actionRules.get(0).execute(this);
            if(ActionType.QUIT.equals(actionRules.get(0).getActionType())) exit = true;
        }

        return exit;
    }

    private List<ActionRule> findActionRules(List<ActionType> actionTypes, List<Item> objects) {
        List<ActionRule> actionRules = new ArrayList<>();
        if(objects.isEmpty()) objects.add(this);

        for (ActionType actionType : actionTypes ) {
            for (Item item : objects) {
                ActionRule actionRule = item.findActionRule(actionType, objects);
                if(actionRule != null) actionRules.add(actionRule);
            }
        }

        return actionRules;
    }

}
