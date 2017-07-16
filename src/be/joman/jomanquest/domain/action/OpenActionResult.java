package be.joman.jomanquest.domain.action;

import be.joman.jomanquest.domain.Gateway;
import be.joman.jomanquest.domain.Item;

/**
 * Created by Freddy on 16-7-2017.
 */
public class OpenActionResult extends ActionResult{

    @Override
    public void execute(ActionRule actionRule) {
        for (Item item : actionRule.getDirectObjects()) {
            Gateway gateway = (Gateway)item;
            if(gateway.isLocked()) gateway.unLock();
        }
    }

}
