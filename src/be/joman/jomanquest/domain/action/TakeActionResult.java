package be.joman.jomanquest.domain.action;

import be.joman.jomanquest.domain.Item;

/**
 * Created by Freddy on 16-7-2017.
 */
public class TakeActionResult extends ActionResult{

    @Override
    public void execute(ActionRule actionRule) {
        for (Item item : actionRule.getDirectObjects()) {
            //TODO lookup
            item.inspect();
        }
    }

}
