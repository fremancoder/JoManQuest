package be.joman.jomanquest.domain.action;

import java.util.List;

/**
 * Created by Freddy on 15-7-2017.
 */
public class Action {

    private ActionType actionType;

    private List<String> synonyms;

    public Action(ActionType actionType, List<String> synonyms) {
        this.actionType = actionType;
        this.synonyms = synonyms;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }
}
