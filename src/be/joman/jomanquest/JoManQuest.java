package be.joman.jomanquest;

import be.joman.jomanquest.domain.Gateway;
import be.joman.jomanquest.domain.action.ActionRule;
import be.joman.jomanquest.domain.action.ActionType;
import be.joman.jomanquest.domain.Item;
import be.joman.jomanquest.domain.Room;
import be.joman.jomanquest.domain.action.OpenActionResult;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class JoManQuest {

    public static void main(String[] args) {
        Consumer<List<Item>> inspectConsumer = (List<Item> itemList)->{ for (Item item : itemList) { item.inspect(); }};

        //First Room Setup
        Room firstRoom = new Room("First Romm","This is the first room and there are a number of nice items in here", "inspect the room and us everything you see, och yeah and try to get out of the room");
        Item chest = new Item("Chest", "A beautiful antique chest, seems to be locked", "Try to move the chest", false, false, false);
        Item key = new Item("Key", "There is a key in the middle of the room", "DUH HUH !!!!", false, false, false);
        firstRoom.getItems().add(chest);
        firstRoom.getItems().add(key);

        //Second room
        Room secondRoom = new Room("First Romm","This is the first room and there are a number of nice items in here", "inspect the room and us everything you see, och yeah and try to get out of the room");

        //Define gateways between the rooms
        Gateway gateway = new Gateway("Door", "There is a door on the lefthand side of the room", "Try openg the door with whatever object you can find or create in the room", firstRoom, secondRoom, true );
        Item[] openGatewayItems = {key};
        Item[] directObjects = {gateway};
        ActionRule openGatewayActionRule = new ActionRule(ActionType.OPEN,  Arrays.asList(directObjects), new OpenActionResult(), Arrays.asList(openGatewayItems));
        gateway.getActionRules().add(openGatewayActionRule);

        //Check if the action Rule is applied correctly
        ActionRule actionRule1 = firstRoom.findActionRule(ActionType.INSPECT);
        actionRule1.execute();

        ActionRule actionRule3 = gateway.findActionRule(ActionType.INSPECT);

        actionRule3.execute();
        actionRule3.executeLamba((List<Item> itemList)->{ for (Item item : itemList) { item.inspect(); }});
        actionRule3.executeLamba(inspectConsumer);
        actionRule3.executeLamba(inspectConsumer);
    }
}
