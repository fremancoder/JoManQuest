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

    private final static ActionController ourInstance = new ActionController();

    private List<Action> actions;

    Consumer <ActionArguments> inspectAction = (ActionArguments arg)-> { arg.getActionRule().getDirectObject().inspect();};

    Consumer <ActionArguments> tipAction = (ActionArguments arg)-> { arg.getActionRule().getDirectObject().info();};

    Consumer <ActionArguments> saveAction = (ActionArguments arg) -> { ((Game)arg.getActionRule().getDirectObject()).saveGame();};

    Consumer <ActionArguments> loadAction = (ActionArguments arg) -> { ((Game)arg.getActionRule().getDirectObject()).loadGame();};

    Consumer <ActionArguments> musicAction = (ActionArguments arg) -> { ((Game)arg.getActionRule().getDirectObject()).playMusic();};

    Consumer <ActionArguments> muteAction = (ActionArguments arg) -> { ((Game)arg.getActionRule().getDirectObject()).muteMusic();};

    Consumer <ActionArguments> quitAction = (ActionArguments arg) -> { ((Game)arg.getActionRule().getDirectObject()).quit();};

    Consumer <ActionArguments> openAction = (ActionArguments arg) -> {
        if(!arg.getActionRule().getDirectObject().isLocked()) {
            if(((Gateway)arg.getActionRule().getDirectObject()).getInitialRoom().equals(arg.getGame().getCurrentRoom())){
                arg.getGame().setCurrentRoom(((Gateway)arg.getActionRule().getDirectObject()).getSecondaryRoom());
            } else {
                arg.getGame().setCurrentRoom(((Gateway)arg.getActionRule().getDirectObject()).getInitialRoom());
            }
            arg.getGame().getCurrentRoom().inspect();
        } else {
            System.out.println("I'm afraid the door is locked, try to unlock it");
        }
    };

    Consumer <ActionArguments> lockAction = (ActionArguments arg) -> { if(!arg.getActionRule().getDirectObject().isLocked()) {arg.getActionRule().getDirectObject().lock();}};

    Consumer <ActionArguments> unlockAction = (ActionArguments arg) -> { Item o = arg.getActionRule().getDirectObject(); if(o.isLocked()) {o.unLock();}};

    Consumer <ActionArguments> takeAction = (ActionArguments arg) -> { Item o = arg.getActionRule().getDirectObject(); if(o.isCollectible()) {arg.getGame().getPlayer().getItems().add(o); arg.getGame().getCurrentRoom().removeItem(o);
        System.out.println("I just put that stuff in my backpack.");}};

    Consumer <ActionArguments> moveAction = (ActionArguments arg) -> { Item o = arg.getActionRule().getDirectObject(); if(o.isMovable()) {o.move(arg.getActionRule().getIndirectObjects()); } };

    Consumer <ActionArguments> createAction = (ActionArguments arg) -> {
        for (Item obj : arg.getActionRule().getIndirectObjects() ) {
            arg.getGame().getCurrentRoom().removeItem(obj);
        }
        arg.getActionRule().getResultingObject().unHide();
        System.out.println("WOW what just happened?");
    };

    public static ActionController getInstance() {
        return ourInstance;
    }

    private ActionController() {
        actions = new ArrayList<>();

        final String[] inspectSynonyms = {"inspect","examine","look","look at","audit","review","watch","scan","investigate","probe","assess","evaluate"};
        actions.add(new Action(ActionType.INSPECT, Arrays.asList(inspectSynonyms), inspectAction));

        final String[] tipSynonyms = {"tip","info","hint","help"};
        actions.add(new Action(ActionType.TIP, Arrays.asList(tipSynonyms), tipAction));

        final String[] takeSynonyms = {"take","acquire","get","obtain","attain","collect","gain","gather","fetch","grab","pick up","snag","secure"};
        actions.add(new Action(ActionType.TAKE, Arrays.asList(takeSynonyms), takeAction));

        String[] createSynonyms = {"create","make","build","use","apply","break","hit","throw"};
        actions.add(new Action(ActionType.CREATE, Arrays.asList(createSynonyms), createAction));

        final String[] moveSynonyms = {"move","examine","look","look at","audit","review","watch","scan","investigate","probe","assess","evaluate"};
        actions.add(new Action(ActionType.MOVE, Arrays.asList(moveSynonyms), moveAction));

        final String[] openSynonyms = {"open", "walk", "run", "go"};
        actions.add(new Action(ActionType.OPEN, Arrays.asList(openSynonyms), openAction));

        final String[] lockSynonyms = {"lock"};
        actions.add(new Action(ActionType.LOCK, Arrays.asList(lockSynonyms), lockAction));

        final String[] unlockSynonyms = {"unlock"};
        actions.add(new Action(ActionType.UNLOCK, Arrays.asList(unlockSynonyms), unlockAction));

        final String[] musicSynonyms = {"music", "tune", "noise"};
        actions.add(new Action(ActionType.MUSIC, Arrays.asList(musicSynonyms), musicAction));

        final String[] muteSynonyms = {"mute", "silence"};
        actions.add(new Action(ActionType.MUTE, Arrays.asList(muteSynonyms), muteAction));

        final String[] loadSynonyms = {"load"};
        actions.add(new Action(ActionType.LOAD, Arrays.asList(loadSynonyms), loadAction));

        final String[] saveSynonyms = {"save"};
        actions.add(new Action(ActionType.SAVE, Arrays.asList(saveSynonyms), saveAction));

        final String[] quitSynonyms = {"quit"};
        actions.add(new Action(ActionType.QUIT, Arrays.asList(quitSynonyms), quitAction));

    }

    public ActionType findActionType(final String synonym){
        for (final Action action : actions) {
            if(synonym != null && action.getSynonyms().contains(synonym)){
                return action.getActionType();
            }
        }
        return null;
    }

    public Action getAction(final ActionType actionType){
        for (final Action action : actions) {
            if(action.getActionType().equals(actionType)){
                return action;
            }
        }
        return null;
    }

    public List<ActionType> findActionTypes(final List<String>words){
        List<ActionType> actionTypes = new ArrayList<>();
        for (final String word : words) {
            final ActionType actionType = findActionType(word);
            if(actionType != null) actionTypes.add(actionType);
        }
        return actionTypes;
    }

}
