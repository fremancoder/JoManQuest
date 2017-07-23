package be.joman.jomanquest;

import be.joman.jomanquest.domain.*;
import be.joman.jomanquest.domain.action.ActionRule;
import be.joman.jomanquest.domain.action.ActionType;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class JoManQuest {

    public static void main(String[] args) {
        Game game = initGame();

        System.out.println(game.getDescription());
        System.out.println(game.getInfo());

        game.playMusic();

        Scanner reader = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String command = reader.nextLine();
            if(command != null){
                // split command into lowercase words
                command = command.toLowerCase();
                List<String> words = Arrays.asList(command.split("\\s+"));

                exit = game.executeValidActionRules(words);
            }
        }

    }

    private static Game initGame() {
        Game game = new Game();

        Player player = new Player();

        //First Room Setup
        Room firstRoom = new Room("MasterRoom","This is the first room and there are a number of nice items in here", "inspect the room and us everything you see, och yeah and try to get out of the room");

        Item chest = new Item("Chest", "A beautiful antique chest, seems to be locked", "Try to move the chest", false, true);
        Item letter = new Item("Letter", "An old letter is lying on the place where once stood a chest.", "Dear Sir, if you happen to find me, I am not important, yours sincerely", false, false, true, false);
        Item[] hiddenItems = {letter};
        ActionRule moveChestActionRule = new ActionRule(ActionType.MOVE, chest, Arrays.asList(hiddenItems));
        chest.addActionRules(moveChestActionRule);

        Item key = new Item("Key", "There is a key in the middle of the room", "DUH HUH !!!!", true, false);

        firstRoom.getItems().add(chest);
        firstRoom.getItems().add(key);
        firstRoom.getItems().add(letter);

        //Second room
        Room secondRoom = new Room("BedRoom","This is the bedroom and there are a number of nice items in here", "inspect the room and us everything you see, och yeah and try to get out of the room");

        //Define gateways between the rooms
        Gateway door = new Gateway("Door", "There is a door on the lefthand side of the room", "Try openg the door with whatever object you can find or create in the room", firstRoom, secondRoom, true);
        Item[] opendoor = {key};
        ActionRule openGatewayActionRule = new ActionRule(ActionType.UNLOCK, door, Arrays.asList(opendoor));
        door.addActionRules(openGatewayActionRule);
        firstRoom.getGateways().add(door);
        secondRoom.getGateways().add(door);

        game.setCurrentRoom(firstRoom);
        game.getRooms().add(firstRoom);
        game.getRooms().add(secondRoom);
        game.setPlayer(player);

        return game;
    }
}
