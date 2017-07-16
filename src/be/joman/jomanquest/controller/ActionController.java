package be.joman.jomanquest.controller;

import be.joman.jomanquest.domain.action.Action;
import be.joman.jomanquest.domain.action.ActionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Freddy on 15-7-2017.
 */
public class ActionController {

    private static ActionController ourInstance = new ActionController();

    private List<Action> actions;

    private ActionController() {
        actions = new ArrayList<>();

        String[] takeSynonyms = {"take","acquire","get","obtain","attain","collect","gain","gather","fetch","grab","pick up","snag","secure"};
        actions.add(new Action(ActionType.TAKE, Arrays.asList(takeSynonyms)));

        String[] useSynonyms = {"use","apply","break","hit","throw"};
        actions.add(new Action(ActionType.USE, Arrays.asList(useSynonyms)));

        String[] inspectSynonyms = {"inspect","examine","look","look at","audit","review","watch","scan","investigate","probe","assess","evaluate"};
        actions.add(new Action(ActionType.INSPECT, Arrays.asList(inspectSynonyms)));

        String[] moveSynonyms = {"move","examine","look","look at","audit","review","watch","scan","investigate","probe","assess","evaluate"};
        actions.add(new Action(ActionType.MOVE, Arrays.asList(moveSynonyms)));

        String[] talkSynonyms = {"talk", "chat", "babble", "prate", "say", "communicate", "converse", "parley"};
        actions.add(new Action(ActionType.TALK, Arrays.asList(talkSynonyms)));

        String[] openSynonyms = {"open", "unlock"};
        actions.add(new Action(ActionType.OPEN, Arrays.asList(openSynonyms)));

        String[] closeSynonyms = {"close", "shut", "lock"};
        actions.add(new Action(ActionType.CLOSE, Arrays.asList(closeSynonyms)));
    }

    public static ActionController getInstance() {
        return ourInstance;
    }

    public ActionType findActionType(String synonym){
        for (Action action : actions) {
            if(synonym != null && action.getSynonyms().contains(synonym.toLowerCase())){
                return action.getActionType();
            }
        }
        return null;
    }

}
