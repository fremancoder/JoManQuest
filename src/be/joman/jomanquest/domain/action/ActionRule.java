package be.joman.jomanquest.domain.action;

import be.joman.jomanquest.domain.Item;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Freddy on 15-7-2017.
 */
public class ActionRule {

    private List<Item> directObjects;

    private ActionType actionType;

    private ActionResult result;

    private List<Item> indirectObjects;

    private Item resultingObject;

    public ActionRule(ActionType actionType, List<Item> directObjects, ActionResult result) {
        this.directObjects = directObjects;
        this.actionType = actionType;
        this.result = result;
    }

    public ActionRule(ActionType actionType, List<Item> directObjects, ActionResult result, List<Item> indirectObjects) {
        this.directObjects = directObjects;
        this.actionType = actionType;
        this.result = result;
        this.indirectObjects = indirectObjects;
    }

    public ActionRule(ActionType actionType, List<Item> directObjects, ActionResult result, Item resultingObject) {
        this.directObjects = directObjects;
        this.actionType = actionType;
        this.result = result;
        this.resultingObject = resultingObject;
    }

    public List<Item> getDirectObjects() {
        return directObjects;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public ActionResult getResult() {
        return result;
    }

    public List<Item> getIndirectObjects() {
        return indirectObjects;
    }

    public Item getResultingObject() {
        return resultingObject;
    }

    public void execute() {
        result.execute(this);
    }

    public void executeLamba(Consumer<List<Item>> c){
        c.accept(directObjects);
    }

}
