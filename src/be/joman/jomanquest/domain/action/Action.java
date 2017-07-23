package be.joman.jomanquest.domain.action;

import be.joman.jomanquest.domain.Item;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Freddy on 15-7-2017.
 */
public class Action implements Serializable{

    private ActionType actionType;

    private List<String> synonyms;

    private Consumer<ActionArguments> action;

    public Action(final ActionType actionType, final List<String> synonyms, final Consumer<ActionArguments> action) {
        this.actionType = actionType;
        this.synonyms = synonyms;
        this.action = action;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void execute(ActionArguments actionArguments){
        action.accept(actionArguments);
    }
}
