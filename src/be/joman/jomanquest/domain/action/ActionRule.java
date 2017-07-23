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

    private List<Item> resultingObjects;

    //Do we need to declare constructor arguments as final?
    public ActionRule(final ActionType actionType, final Item directObject) {
        this.directObject = directObject;
        this.actionType = actionType;
    }

    public ActionRule(final ActionType actionType, final Item directObject, final List<Item> indirectObjects) {
        this.directObject = directObject;
        this.actionType = actionType;
        this.indirectObjects = indirectObjects;
    }

    public ActionRule(final ActionType actionType, final Item directObject, final List<Item> indirectObjects, final List<Item> resultingObjects) {
        this.directObject = directObject;
        this.actionType = actionType;
        this.indirectObjects = indirectObjects;
        this.resultingObjects = resultingObjects;
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

    public List<Item> getResultingObjects() { return resultingObjects; }

    public void execute(Game game) {
        ActionController.getInstance().getAction(actionType).execute(new ActionArguments(game, this));
    }

}
