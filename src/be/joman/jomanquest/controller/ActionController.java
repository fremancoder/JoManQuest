package be.joman.jomanquest.controller;

import be.joman.jomanquest.domain.Item;
import be.joman.jomanquest.domain.action.Action;
import be.joman.jomanquest.domain.action.ActionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Freddy on 15-7-2017.
 */
public class ActionController {

    private static ActionController ourInstance = new ActionController();

    private List<Action> actions;

    Consumer <List<Item>> inspectAction = (List<Item> itemList)-> { for (Item item : itemList) { item.inspect();}};

    private ActionController() {
        actions = new ArrayList<>();

        String[] takeSynonyms = {"take","acquire","get","obtain","attain","collect","gain","gather","fetch","grab","pick up","snag","secure"};
        actions.add(new Action(ActionType.TAKE, Arrays.asList(takeSynonyms), inspectAction));

        String[] useSynonyms = {"use","apply","break","hit","throw"};
        actions.add(new Action(ActionType.USE, Arrays.asList(useSynonyms), inspectAction));

        String[] inspectSynonyms = {"inspect","examine","look","look at","audit","review","watch","scan","investigate","probe","assess","evaluate"};
        actions.add(new Action(ActionType.INSPECT, Arrays.asList(inspectSynonyms), inspectAction));

        String[] moveSynonyms = {"move","examine","look","look at","audit","review","watch","scan","investigate","probe","assess","evaluate"};
        actions.add(new Action(ActionType.MOVE, Arrays.asList(moveSynonyms), inspectAction));

        String[] talkSynonyms = {"talk", "chat", "babble", "prate", "say", "communicate", "converse", "parley"};
        actions.add(new Action(ActionType.TALK, Arrays.asList(talkSynonyms), inspectAction));

        String[] openSynonyms = {"open", "unlock"};
        actions.add(new Action(ActionType.OPEN, Arrays.asList(openSynonyms), inspectAction));

        String[] closeSynonyms = {"close", "shut", "lock"};
        actions.add(new Action(ActionType.CLOSE, Arrays.asList(closeSynonyms), inspectAction));
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
