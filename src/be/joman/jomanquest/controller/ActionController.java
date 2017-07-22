package be.joman.jomanquest.controller;

import be.joman.jomanquest.domain.Game;
import be.joman.jomanquest.domain.Gateway;
import be.joman.jomanquest.domain.Item;
import be.joman.jomanquest.domain.action.Action;
import be.joman.jomanquest.domain.action.ActionArguments;
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

    Consumer <ActionArguments> inspectAction = (ActionArguments arg)-> { arg.getActionRule().getDirectObject().inspect();};

    Consumer <ActionArguments> tipAction = (ActionArguments arg)-> { arg.getActionRule().getDirectObject().info();};

    Consumer <ActionArguments> saveAction = (ActionArguments arg) -> { ((Game)arg.getActionRule().getDirectObject()).saveGame();};

    Consumer <ActionArguments> loadAction = (ActionArguments arg) -> { ((Game)arg.getActionRule().getDirectObject()).loadGame();};

    Consumer <ActionArguments> musicAction = (ActionArguments arg) -> { ((Game)arg.getActionRule().getDirectObject()).playMusic();};

    Consumer <ActionArguments> muteAction = (ActionArguments arg) -> { ((Game)arg.getActionRule().getDirectObject()).muteMusic();};

    Consumer <ActionArguments> quitAction = (ActionArguments arg) -> { ((Game)arg.getActionRule().getDirectObject()).quit();};

    Consumer <ActionArguments> closeAction = (ActionArguments arg) -> { if(!((Gateway)arg.getActionRule().getDirectObject()).isLocked()) ((Gateway)arg.getActionRule().getDirectObject()).lock();};

    Consumer <ActionArguments> openAction = (ActionArguments arg) -> { Item o = arg.getActionRule().getDirectObject(); if(((Gateway)o).isLocked()) ((Gateway)o).unLock();};

    Consumer <ActionArguments> takeAction = (ActionArguments arg) -> { Item o = arg.getActionRule().getDirectObject(); if(o.isCollectible()) {arg.getGame().getPlayer().getItems().add(o); arg.getGame().getCurrentRoom().removeItem(o);
        System.out.println("I just put that stuff in my backpack.");}};

    Consumer <ActionArguments> moveAction = (ActionArguments arg) -> { Item o = arg.getActionRule().getDirectObject(); if(o.isMovable()) {o.move(arg.getActionRule().getIndirectObjects()); } };

//    Consumer <ActionArguments> useAction = (ActionArguments arg) -> { };

    public static ActionController getInstance() {
        return ourInstance;
    }

    private ActionController() {
        actions = new ArrayList<>();

        String[] inspectSynonyms = {"inspect","examine","look","look at","audit","review","watch","scan","investigate","probe","assess","evaluate"};
        actions.add(new Action(ActionType.INSPECT, Arrays.asList(inspectSynonyms), inspectAction));

        String[] tipSynonyms = {"tip","info","hint","help"};
        actions.add(new Action(ActionType.TIP, Arrays.asList(tipSynonyms), tipAction));

        String[] takeSynonyms = {"take","acquire","get","obtain","attain","collect","gain","gather","fetch","grab","pick up","snag","secure"};
        actions.add(new Action(ActionType.TAKE, Arrays.asList(takeSynonyms), takeAction));

//        String[] useSynonyms = {"use","apply","break","hit","throw"};
//        actions.add(new Action(ActionType.USE, Arrays.asList(useSynonyms), useAction));

        String[] moveSynonyms = {"move","examine","look","look at","audit","review","watch","scan","investigate","probe","assess","evaluate"};
        actions.add(new Action(ActionType.MOVE, Arrays.asList(moveSynonyms), moveAction));

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


//        String[] talkSynonyms = {"talk", "chat", "babble", "prate", "say", "communicate", "converse", "parley"};
//        actions.add(new Action(ActionType.TALK, Arrays.asList(talkSynonyms), inspectAction));

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
