package be.joman.jomanquest.domain.action;

import be.joman.jomanquest.domain.Item;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Freddy on 15-7-2017.
 */
public class Action {

    private ActionType actionType;

    private List<String> synonyms;

    private Consumer<List<Item>> lambdaFunction;

    public Action(ActionType actionType, List<String> synonyms, Consumer<List<Item>> consumer) {
        this.actionType = actionType;
        this.synonyms = synonyms;
        this.lambdaFunction = consumer;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void execute(ActionRule actionRule){
        lambdaFunction.accept(actionRule.getDirectObjects());
    }
}
