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
        //INIT GAME OBJECTS
        Game game = new Game();
        Player player = new Player();

        //ITEMS
        Item mattress = new Item("mattress", "A dirty mattress is lying in a corner of the room", "There is nothing to tell, it is a mattress", false, true);
        Item pillow = new Item("pillow", "There seems to be a pillow on top of the mattress", "It is a pillow what did you expect to find?", true, true);
        Item creditCard = new Item("card", "A credit card, probably expired", "Try to pull of what you have seen in the movies over a thousand times by now", true, false);
        Item closet = new Item("closet", "A big antique closet", "It is a heavy closet, I don't think you can move it", false, true);
        Item lightBulb = new Item("light", "A dim light, coming from one small light bulb", "You could try to turn it of", false, false);
        Item washer = new Item("washer", "A old washer is standing in the corner, it is washing some workwear", "do not interrupt the washer,it is working", false, false);
        Item dryer = new Item("dryer", "A dryer which seems to be off duty for the moment", "You could try to turn it on, but nothing might happen, or you could unlock the door", false, false, false, true);
        Item linenBasket = new Item("basket", "A linen basket containing even more workwear, somebody likes to work in this house", "You could try to wear some of the clothes?", false, false);
        Item clothesRack = new Item("rack", "A clothes rack carrying some clean working clotes", "You could try on some of the clean clothes?", false, false);
        Item clothes = new Item("clothes", "Dirty clothes lying on the floor.", "I would not try wearing some of those dirty clothes, you might get infected", false, false);
        Item washingPowder = new Item("powder", "A old box of Omo, adds brightness to whiteness", "who still owns such an old box of washing powder", false, false);
        Item ironingBoard = new Item("board", "An ironing board", "You can't put that in your backpack, can you.", false, false);
        Item iron = new Item("iron", "A philips iron, that is product placement for you", "It would be stupid to put that in your backpack, it's way to heavy", true, false);
        Item smallWindow = new Item("window", "There is a small window, to narrow to escape, but it is the first light you have seen in a long time", "Don't waste your time on the window, try to get out of here", false, false);
        Item key = new Item("Key", "A key, lying in the dryer, perhaps it fel out of somebodies pants.", "DUH HUH !!!!", true, false);
        Item chest = new Item("Chest", "A beautiful antique chest, seems to be locked", "Try to move the chest", false, true);
        Item letter = new Item("Letter", "An old letter is lying on the place where once stood a chest.", "Dear Sir, This house is guarded by a giant who hates tickling. Signed AF.", false, false, true, false);
        Item window = new Item("Window", "There is a window on the right side of the room", "There are bars behind the window, you won't be able to escape through the window", false, false);
        Item glass = new Item("Glass", "Some glass scrap is lying on the floor, must be the result of you throwing a rock at the window.", "Use the glass scrap when needed", true, false, true, false);
        Item brokenWindow = new Item("BrokenWindow", "There is a broken window in the wall, I wonder what happened.", "There are still bars in the opening, no escape possible", false, false, true, false);
        Item rock = new Item("Rock", "A few rocks are lying in the corner of the room.", "Try playing with the rock", true, false);
        Item feather = new Item("Feather", "Feathers of the torn pillow.", "What do you want me to say?", true, false, true, false);
        Item cloth = new Item("Cloth", "Some torn cloth, remainders of the pillow.", "What do you want me to say?", true, false, true, false);
        Item foot = new Item("Foot", "There seems to be a foot of a giant sticking through the wall where the door is supposed to be", "It is obvious that you should try to move the foot, isn't it??", false, false);

        //ITEMS IN ITEMS
        dryer.getItems().add(key);
        pillow.getItems().add(feather);
        pillow.getItems().add(cloth);
        window.getItems().add(brokenWindow);
        window.getItems().add(glass);

        //ROOMS
        Room bedRoom = new Room("Bedroom","You seem to be in your bedroom now, it seems you do not have a lot of luxury", "inspect the room and use everything you see or don't see, och yeah and try to get out of the room");
        Room cellar = new Room("Basement","You seem to be in the basement now, it is cold and moist", "There is not much in the basement, try using whatever is in it");
        Room washingRoom = new Room("Washing-room","You seem to be in the washing room now, a lot of washing has been done here", "There is a lot of interesting stuff here, but I am not quite sure you can do something with it");
        Room living = new Room("Living","You seem to be in the Living room now,", "inspect the room and us everything you see, och yeah and try to get out of this rooom as well");
        Room hallWay = new Room("Hallway","You seem to be in the hallway now, it's almost completely empty", "Although it is empty you will have to find a way out");
        Room outDoors = new Room("outdoors","Hallelujah!!! You have beaten the system, good for you. Unfortunately this also means that you have reached the end of all the fun. Back to work now!", "it is beautiful");

        //ITEMS IN ROOM
        bedRoom.getItems().add(mattress);
        bedRoom.getItems().add(pillow);
        cellar.getItems().add(closet);
        cellar.getItems().add(lightBulb);
        washingRoom.getItems().add(washer);
        washingRoom.getItems().add(dryer);
        washingRoom.getItems().add(linenBasket);
        washingRoom.getItems().add(clothesRack);
        washingRoom.getItems().add(clothes);
        washingRoom.getItems().add(washingPowder);
        washingRoom.getItems().add(ironingBoard);
        washingRoom.getItems().add(iron);
        washingRoom.getItems().add(smallWindow);
        living.getItems().add(chest);
        living.getItems().add(letter);
        living.getItems().add(window);
        living.getItems().add(rock);
        hallWay.getItems().add(foot);

        //GATEWAYS
        Gateway bedRoomDoor = new Gateway("door", "A bedroom door", "Try open the door with whatever object you can find or create in the room", bedRoom, cellar, true);
        Gateway stairWay = new Gateway("stairway", "A stairway, it is probably going to bring you to a whole new level", "use the stairway", cellar, washingRoom, false, true);
        Gateway foldingDoor = new Gateway("folding-door", "A sixties folding-door separate us from the next room", "try walking through the door", washingRoom, living, false);
        Gateway door = new Gateway("Door", "There is a door on the left hand side of the room", "Try open the door with whatever object you can find or create in the room", living, hallWay, true);
        Gateway frontDoor = new Gateway("Hole", "There is a giant hole where the giants foot used to be, it might be your way out", "Walk out", hallWay, outDoors, false, true);

        //ADD GATEWAY TO ROOM
        bedRoom.getGateways().add(bedRoomDoor);
        cellar.getGateways().add(bedRoomDoor);
        cellar.getGateways().add(stairWay);
        washingRoom.getGateways().add(stairWay);
        washingRoom.getGateways().add(foldingDoor);
        living.getGateways().add(foldingDoor);
        living.getGateways().add(door);
        hallWay.getGateways().add(door);
        hallWay.getGateways().add(frontDoor);

        //ACTION RULES
        Item[] openBedRoomdoor = {creditCard};
        ActionRule openBedRoomdoorActionRule = new ActionRule(ActionType.UNLOCK, bedRoomDoor, Arrays.asList(openBedRoomdoor));
        Item[] hiddenStairWay = {stairWay};
        ActionRule moveClosetActionRule = new ActionRule(ActionType.MOVE, closet, Arrays.asList(hiddenStairWay));
        ActionRule openDryerActionRule = new ActionRule(ActionType.UNLOCK, dryer);
        Item[] hiddenItems = {letter};
        ActionRule moveChestActionRule = new ActionRule(ActionType.MOVE, chest, Arrays.asList(hiddenItems));
        Item[] rockTool = { rock };
        Item[] windowResult = { glass, brokenWindow };
        ActionRule throwRockActionRule = new ActionRule(ActionType.CREATE, window, Arrays.asList(rockTool), Arrays.asList(windowResult) );
        Item[] glassTool = { glass };
        Item[] pillowResult = { feather, cloth };
        ActionRule cutPillowActionRule = new ActionRule(ActionType.CREATE, pillow, Arrays.asList(glassTool), Arrays.asList(pillowResult) );
        Item[] opendoor = {key};
        ActionRule openGatewayActionRule = new ActionRule(ActionType.UNLOCK, door, Arrays.asList(opendoor));
        Item[] featherTool = {feather};
        Item[] frontDoorResult = {frontDoor};
        ActionRule moveFootActionRule = new ActionRule(ActionType.REMOVE, foot, Arrays.asList(featherTool), Arrays.asList(frontDoorResult));

        //ACTION RULES ON ITEM
        bedRoomDoor.addActionRules(openBedRoomdoorActionRule);
        closet.addActionRules(moveClosetActionRule);
        dryer.addActionRules(openDryerActionRule);
        chest.addActionRules(moveChestActionRule);
        window.addActionRules(throwRockActionRule);
        door.addActionRules(openGatewayActionRule);
        pillow.addActionRules(cutPillowActionRule);
        foot.addActionRules(moveFootActionRule);

        //CONSTRUCT GAME
        game.setCurrentRoom(bedRoom);

        game.getRooms().add(bedRoom);
        game.getRooms().add(cellar);
        game.getRooms().add(washingRoom);
        game.getRooms().add(living);
        game.getRooms().add(hallWay);

        player.getItems().add(creditCard);
        game.setPlayer(player);

        return game;
    }
}
