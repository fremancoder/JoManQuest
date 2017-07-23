package be.joman.jomanquest.domain.action;

import be.joman.jomanquest.controller.ActionController;
import be.joman.jomanquest.domain.Game;
import be.joman.jomanquest.domain.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Freddy on 15-7-2017.
 */
public class ActionRule implements Serializable{

    private Item directObject;

    private ActionType actionType;

    private List<Item> indirectObjects = new ArrayList<>();

    private Item resultingObject;

    public ActionRule(ActionType actionType, Item directObject) {
        this.directObject = directObject;
        this.actionType = actionType;
    }

    public ActionRule(ActionType actionType, Item directObject, List<Item> indirectObjects) {
        this.directObject = directObject;
        this.actionType = actionType;
        this.indirectObjects = indirectObjects;
    }

    public ActionRule(ActionType actionType, Item directObject, List<Item> indirectObjects, Item resultingObject) {
        this.directObject = directObject;
        this.actionType = actionType;
        this.indirectObjects = indirectObjects;
        this.resultingObject = resultingObject;
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

    public Item getResultingObject() { return resultingObject; }

    public void execute(Game game) {
        ActionController.getInstance().getAction(actionType).execute(new ActionArguments(game, this));
    }

}
