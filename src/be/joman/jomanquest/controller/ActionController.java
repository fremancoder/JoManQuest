package be.joman.jomanquest.controller;

import be.joman.jomanquest.domain.Game;
import be.joman.jomanquest.domain.Gateway;
import be.joman.jomanquest.domain.Item;
import be.joman.jomanquest.domain.action.Action;
import be.joman.jomanquest.domain.action.ActionRule;
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

    Consumer <Item> inspectAction = (Item item)-> { item.inspect();};

    Consumer <Item> closeAction = (Item item) -> { if(!((Gateway)item).isLocked()) ((Gateway)item).lock();};

    Consumer <Item> openAction = (Item item) -> { if(((Gateway)item).isLocked()) ((Gateway)item).unLock();};

    Consumer <Item> moveAction = (Item item) -> { if(((Gateway)item).isLocked()) ((Gateway)item).unLock();};

    Consumer <Item> saveAction = (Item item) -> { ((Game)item).saveGame();};

    Consumer <Item> loadAction = (Item item) -> { ((Game)item).loadGame();};

    Consumer <Item> musicAction = (Item item) -> { ((Game)item).playMusic();};

    Consumer <Item> muteAction = (Item item) -> { ((Game)item).muteMusic();};

    Consumer <Item> quitAction = (Item item) -> { ((Game)item).quit();};

    public static ActionController getInstance() {
        return ourInstance;
    }

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
        actions.add(new Action(ActionType.OPEN, Arrays.asList(openSynonyms), openAction));

        String[] closeSynonyms = {"close", "shut", "lock"};
        actions.add(new Action(ActionType.CLOSE, Arrays.asList(closeSynonyms), closeAction));

        String[] musicSynonyms = {"music", "tune", "noise"};
        actions.add(new Action(ActionType.MUSIC, Arrays.asList(musicSynonyms), musicAction));

        String[] muteSynonyms = {"mute", "silence"};
        actions.add(new Action(ActionType.MUTE, Arrays.asList(muteSynonyms), muteAction));

        String[] loadSynonyms = {"load"};
        actions.add(new Action(ActionType.LOAD, Arrays.asList(loadSynonyms), loadAction));

        String[] saveSynonyms = {"save"};
        actions.add(new Action(ActionType.SAVE, Arrays.asList(saveSynonyms), saveAction));

        String[] quitSynonyms = {"quit"};
        actions.add(new Action(ActionType.QUIT, Arrays.asList(quitSynonyms), quitAction));
    }


    public ActionType findActionType(String synonym){
        for (Action action : actions) {
            if(synonym != null && action.getSynonyms().contains(synonym.toLowerCase())){
                return action.getActionType();
            }
        }
        return null;
    }

    public Action getAction(ActionType actionType){
        for (Action action : actions) {
            if(action.getActionType().equals(actionType)){
                return action;
            }
        }
        return null;
    }

}
