package be.joman.jomanquest;

import be.joman.jomanquest.controller.ActionController;
import be.joman.jomanquest.domain.*;
import be.joman.jomanquest.domain.action.ActionRule;
import be.joman.jomanquest.domain.action.ActionType;

import java.util.Arrays;
import java.util.Scanner;

public class JoManQuest {

    private static ActionController actionController = ActionController.getInstance();

    public static void main(String[] args) {
        Game game = initGame();

        ActionType actionType = null;
        Scanner reader = new Scanner(System.in);
        while (actionType == null || !ActionType.QUIT.equals(actionType)) {
            String command = reader.nextLine();

            String[] splited = command.split("\\s+");
            if (splited.length == 1) {
                actionType = actionController.findActionType(splited[0]);
                game.findActionRule(actionType).execute(game, actionController.getAction(actionType));
            } else if (splited.length == 2) {
                actionType = actionController.findActionType(splited[0]);
                Item directObject = findDirectObject(game, splited[1]);
                directObject.findActionRule(actionType).execute(game, actionController.getAction(actionType));
            }
        }

    }

    private static Item findDirectObject(Game game, String directOjectName) {
        Item directObject = null;
        if(game.getCurrentRoom().getName().toLowerCase().equals(directOjectName.toLowerCase())){
            directObject = game.getCurrentRoom();
        } else if(game.getPlayer().getName().toLowerCase().equals(directOjectName.toLowerCase())){
            directObject = game.getPlayer();
        } else {
            directObject = game.getCurrentRoom().findItem(directOjectName);
        }
        return directObject;
    }

    private static Game initGame() {
        Game game = new Game("JoManQuest", "Adventure in the life of JoMan.", "A textbased game developped by Jonas Mangelschots");

        Player player = new Player();

        //First Room Setup
        Room firstRoom = new Room("MasterRoom","This is the first room and there are a number of nice items in here", "inspect the room and us everything you see, och yeah and try to get out of the room");
        Item chest = new Item("Chest", "A beautiful antique chest, seems to be locked", "Try to move the chest", false, true, false);
        Item letter = new Item("Letter", "An old letter is lying on the place where once the chest stood.", "Dear Sir, if you happen to find me, I am not important, yours sincerely", false, false, true);
        Item[] hiddenItems = {letter};
        ActionRule moveChestActionRule = new ActionRule(ActionType.MOVE, chest, Arrays.asList(hiddenItems));
        chest.getActionRules().add(moveChestActionRule);
        Item key = new Item("Key", "There is a key in the middle of the room", "DUH HUH !!!!", true, false, false);
        firstRoom.getItems().add(chest);
        firstRoom.getItems().add(key);
        firstRoom.getItems().add(letter);

        //Second room
        Room secondRoom = new Room("BedRoom","This is the bedroom and there are a number of nice items in here", "inspect the room and us everything you see, och yeah and try to get out of the room");

        //Define gateways between the rooms
        Gateway gateway = new Gateway("Door", "There is a door on the lefthand side of the room", "Try openg the door with whatever object you can find or create in the room", firstRoom, secondRoom, true );
        Item[] openGatewayItems = {key};
        ActionRule openGatewayActionRule = new ActionRule(ActionType.OPEN, gateway, Arrays.asList(openGatewayItems));
        gateway.getActionRules().add(openGatewayActionRule);

        game.setCurrentRoom(firstRoom);
        game.getRooms().add(firstRoom);
        game.getRooms().add(secondRoom);
        game.setPlayer(player);

        return game;
    }
}
