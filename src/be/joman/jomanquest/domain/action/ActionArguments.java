package be.joman.jomanquest.domain.action;

import be.joman.jomanquest.domain.Game;
import be.joman.jomanquest.domain.Item;

/**
 * Created by Freddy on 22-7-2017.
 */
public class ActionArguments {

    private Game game;

    private ActionRule actionRule;

    public ActionArguments(Game game, ActionRule actionRule) {
        this.game = game;
        this.actionRule = actionRule;
    }

    public Game getGame() {
        return game;
    }

    public ActionRule getActionRule() {
        return actionRule;
    }
}
