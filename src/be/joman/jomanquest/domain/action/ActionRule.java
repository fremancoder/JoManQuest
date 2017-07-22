package be.joman.jomanquest.domain.action;

import be.joman.jomanquest.domain.Item;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Freddy on 15-7-2017.
 */
public class ActionRule implements Serializable{

    private Item directObject;

    private ActionType actionType;

    private List<Item> indirectObjects;

    public ActionRule(ActionType actionType, Item directObject) {
        this.directObject = directObject;
        this.actionType = actionType;
    }

    public ActionRule(ActionType actionType, Item directObject, List<Item> indirectObjects) {
        this.directObject = directObject;
        this.actionType = actionType;
        this.indirectObjects = indirectObjects;
    }

    public ActionRule(ActionType actionType, Item directObject, Item resultingObject) {
        this.directObject = directObject;
        this.actionType = actionType;
    }

    public Item getDirectObject() {
        return directObject;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public List<Item> getIndirectObjects() {
        return indirectObjects;
    }

    public void execute(Action action) {
        action.execute(directObject);
    }

}
